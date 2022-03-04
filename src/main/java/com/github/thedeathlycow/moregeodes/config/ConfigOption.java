package com.github.thedeathlycow.moregeodes.config;

import com.google.gson.JsonElement;

import java.util.Map;

public abstract class ConfigOption<T> {

    protected final T DEFAULT_VALUE;
    protected T value;

    public ConfigOption(T defaultValue) {
        this.DEFAULT_VALUE = defaultValue;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value != null ? this.value : this.DEFAULT_VALUE;
    }

    public abstract ConfigOption<T> deserialize(JsonElement json);

    public abstract Map<String, Object> serialize();
}
