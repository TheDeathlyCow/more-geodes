package com.github.thedeathlycow.moregeodes.item.tuning;

import com.google.gson.*;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.item.ItemPredicate;

import java.lang.reflect.Type;

public class Tuning {

    public static final Tuning NO_TUNING = new Tuning(0, ItemPredicate.ANY);

    private static final String COLOR_KEY = "color";
    private static final String PREDICATE_KEY = "tuned_to";

    private final int color;
    private final ItemPredicate isTunedToItem;

    public Tuning(int color, ItemPredicate isTunedToItem) {
        this.color = color;
        this.isTunedToItem = isTunedToItem;
    }

    public boolean test(ItemStack item) {
        return this.isTunedToItem.test(item);
    }

    public int getColor() {
        return this.color;
    }

    public static Tuning fromJson(JsonElement jsonElement) {
        JsonObject json = jsonElement.getAsJsonObject();

        int color = json.get(COLOR_KEY).getAsInt();
        ItemPredicate isTunedToItem = ItemPredicate.fromJson(json.get(PREDICATE_KEY));

        return new Tuning(color, isTunedToItem);
    }

    public JsonElement toJson() {

        if (this == NO_TUNING) {
            return JsonNull.INSTANCE;
        }

        var json = new JsonObject();

        json.addProperty(COLOR_KEY, this.color);
        json.add(PREDICATE_KEY, this.isTunedToItem.toJson());

        return json;
    }

    public static class Serializer implements JsonDeserializer<Tuning> {

        public static final Gson GSON = new GsonBuilder()
                .registerTypeAdapter(Tuning.class, new Tuning.Serializer())
                .create();

        @Override
        public Tuning deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return Tuning.fromJson(json);
        }
    }

}
