package com.github.thedeathlycow.moregeodes.config;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Config {

    private final File file;

    private final Map<String, ConfigOption<?>> options = new HashMap<>();

    public Config(String path) {
        this.file = new File(path);
        if (!this.file.exists()) {
            if (this.file.mkdirs()) {
                System.out.println("Successfully created config file!");
            } else {
                System.out.println("Unable to make config file!");
            }
        }
    }

    public void addConfigOption(String key, ConfigOption<?> option) {
        options.put(key, option);
    }

    public ConfigOption<?> removeConfigOption(String key) {
        return options.remove(key);
    }

    public void load() {

    }

    public void save() {

    }

    Map<String, ConfigOption<?>> getOptions() {
        return Collections.unmodifiableMap(options);
    }
}
