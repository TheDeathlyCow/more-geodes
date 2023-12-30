package com.github.thedeathlycow.moregeodes.item.tuning;

import com.google.gson.*;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.lang.reflect.Type;

public class Tuning {

    public static final Tuning NO_TUNING = new Tuning(0, Text.literal(""), BlockPredicate.ANY);

    private static final String COLOR_KEY = "color";
    private static final String DESCRIPTION_KEY = "description";
    private static final String PREDICATE_KEY = "tuned_to";

    private final int color;
    private final Text description;
    private final BlockPredicate isTunedToBlock;

    public Tuning(int color, Text description, BlockPredicate isTunedToBlock) {
        this.color = color;
        this.description = description;
        this.isTunedToBlock = isTunedToBlock;
    }

    public boolean test(ServerWorld world, BlockPos pos) {
        return this.isTunedToBlock.test(world, pos);
    }

    public Text getDescription() {
        return description;
    }

    public int getColor() {
        return this.color;
    }

    public static Tuning fromJson(JsonElement jsonElement) {
        JsonObject json = jsonElement.getAsJsonObject();

        int color = json.get(COLOR_KEY).getAsInt();
        Text description = Text.Serializer.fromJson(json.get(DESCRIPTION_KEY));
        BlockPredicate isTunedToBlock = BlockPredicate.fromJson(json.get(PREDICATE_KEY));

        return new Tuning(color, description, isTunedToBlock);
    }

    public JsonElement toJson() {

        if (this == NO_TUNING) {
            return JsonNull.INSTANCE;
        }

        var json = new JsonObject();

        json.addProperty(COLOR_KEY, this.color);
        json.add(DESCRIPTION_KEY, Text.Serializer.toJsonTree(this.description));
        json.add(PREDICATE_KEY, this.isTunedToBlock.toJson());

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
