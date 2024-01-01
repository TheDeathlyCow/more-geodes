package com.github.thedeathlycow.moregeodes.item.recipe;

import com.github.thedeathlycow.moregeodes.item.GeodesItemGroup;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;

public class TuningRecipe extends ShapelessRecipe {

    private final Identifier tuningId;

    public TuningRecipe(
            Identifier id,
            String group,
            CraftingRecipeCategory category,
            Identifier tuningId,
            DefaultedList<Ingredient> input
    ) {
        super(id, group, category, GeodesItemGroup.makeTunedLocatorStack(tuningId.toString()), input);
        this.tuningId = tuningId;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return GeodesRecipeSerializers.CRYSTAL_TUNING;
    }

    public static class Serializer implements RecipeSerializer<TuningRecipe> {

        @Override
        public TuningRecipe read(Identifier identifier, JsonObject jsonObject) {

            String group = JsonHelper.getString(jsonObject, "group", "");

            CraftingRecipeCategory category = CraftingRecipeCategory.CODEC.byId(
                    JsonHelper.getString(jsonObject, "category", null),
                    CraftingRecipeCategory.MISC
            );

            DefaultedList<Ingredient> ingredients = getIngredients(
                    JsonHelper.getArray(jsonObject, "ingredients")
            );

            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for tuning recipe");
            }
            if (ingredients.size() > 9) {
                throw new JsonParseException("Too many ingredients for tuning recipe");
            }

            if (!jsonObject.has("tuning")) {
                throw new JsonParseException("No tuning ID found for a tuning recipe");
            }

            Identifier tuning = new Identifier(JsonHelper.getString(jsonObject, "tuning"));

            return new TuningRecipe(
                    identifier,
                    group,
                    category,
                    tuning,
                    ingredients
            );
        }

        private static DefaultedList<Ingredient> getIngredients(JsonArray json) {
            DefaultedList<Ingredient> defaultedList = DefaultedList.of();
            for (int i = 0; i < json.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(json.get(i), false);
                if (ingredient.isEmpty()) continue;
                defaultedList.add(ingredient);
            }
            return defaultedList;
        }

        @Override
        public TuningRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {

            String group = packetByteBuf.readString();

            CraftingRecipeCategory category = packetByteBuf.readEnumConstant(CraftingRecipeCategory.class);

            int numIngredients = packetByteBuf.readVarInt();
            DefaultedList<Ingredient> ingredients = DefaultedList.ofSize(numIngredients, Ingredient.EMPTY);
            for (int i = 0; i < ingredients.size(); ++i) {
                ingredients.set(i, Ingredient.fromPacket(packetByteBuf));
            }

            Identifier tuning = new Identifier(packetByteBuf.readString());

            return new TuningRecipe(
                    identifier,
                    group,
                    category,
                    tuning,
                    ingredients
            );
        }

        @Override
        public void write(PacketByteBuf packetByteBuf, TuningRecipe tuningRecipe) {
            packetByteBuf.writeString(tuningRecipe.getGroup());
            packetByteBuf.writeEnumConstant(tuningRecipe.getCategory());
            packetByteBuf.writeVarInt(tuningRecipe.getIngredients().size());
            for (Ingredient ingredient : tuningRecipe.getIngredients()) {
                ingredient.write(packetByteBuf);
            }
            packetByteBuf.writeString(tuningRecipe.tuningId.toString());
        }

    }
}
