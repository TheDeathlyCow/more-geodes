package com.github.thedeathlycow.moregeodes.config;

import com.google.gson.*;

import java.lang.reflect.Type;

public class ConfigTypeAdapter implements JsonDeserializer<Config>, JsonSerializer<Config> {
    @Override
    public Config deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        return null;
    }

    @Override
    public JsonElement serialize(Config config, Type type, JsonSerializationContext jsonSerializationContext) {
        return null;
    }
}
