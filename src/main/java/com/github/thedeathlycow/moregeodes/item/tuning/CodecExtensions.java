package com.github.thedeathlycow.moregeodes.item.tuning;

import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.*;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.text.Text;

/**
 * @deprecated Please remove this if an actual codec ever appears in the vanilla game, this is horrendous
 */
@Deprecated
public class CodecExtensions {
    public static final Codec<LocationPredicate> LOCATION_PREDICATE_CODEC = new Codec<>() {
        @Override
        public <T> DataResult<Pair<LocationPredicate, T>> decode(DynamicOps<T> ops, T input) {
            try {
                JsonElement json = Dynamic.convert(ops, JsonOps.INSTANCE, input);
                LocationPredicate predicate = LocationPredicate.fromJson(json);
                return DataResult.success(Pair.of(predicate, ops.empty()));
            } catch (Exception e) {
                return DataResult.error(e::getMessage);
            }
        }

        @Override
        public <T> DataResult<T> encode(LocationPredicate input, DynamicOps<T> ops, T prefix) {
            try {
                return ops.mergeToPrimitive(prefix, Dynamic.convert(JsonOps.INSTANCE, ops, input.toJson()));
            } catch (Exception e) {
                return DataResult.error(e::getMessage);
            }
        }

        @Override
        public String toString() {
            return "BlockPredicate";
        }
    };

    public static final Codec<Text> TEXT_CODEC = new Codec<>() {
        @Override
        public <T> DataResult<Pair<Text, T>> decode(DynamicOps<T> ops, T input) {
            try {
                JsonElement json = Dynamic.convert(ops, JsonOps.INSTANCE, input);
                Text text = Text.Serializer.fromJson(json);
                return DataResult.success(Pair.of(text, ops.empty()));
            } catch (Exception e) {
                return DataResult.error(() -> e.getClass().getName() + ": " + e.getMessage());
            }
        }

        @Override
        public <T> DataResult<T> encode(Text input, DynamicOps<T> ops, T prefix) {
            try {
                return ops.mergeToPrimitive(prefix, Dynamic.convert(JsonOps.INSTANCE, ops, Text.Serializer.toJsonTree(input)));
            } catch (Exception e) {
                return DataResult.error(() -> e.getClass().getName() + ": " + e.getMessage());
            }
        }

        @Override
        public String toString() {
            return "Text";
        }
    };

    private CodecExtensions() {

    }
}
