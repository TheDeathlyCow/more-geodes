package com.github.thedeathlycow.moregeodes.config;

import com.mojang.logging.LogUtils;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;

public final class GeodesConfig {

    private static final Logger LOGGER = LogUtils.getLogger();

    private static final String generateEmeraldGeodes = "generate_emerald_geodes";
    private static final String generateQuartzGeodes = "generate_quartz_geodes";
    private static final String generateDiamondGeodes = "generate_diamond_geodes";
    private static final String generateEchoGeodes = "generate_echo_geodes";

    private Properties properties;
    private final Properties defaultProperties = new Properties();

    public GeodesConfig() {
    }

    public boolean generateEmeraldGeodes() {
        return Boolean.getBoolean(properties.getProperty(generateEmeraldGeodes));
    }

    public boolean generateQuartzGeodes() {
        return Boolean.getBoolean(properties.getProperty(generateQuartzGeodes));
    }

    public boolean generateDiamondGeodes() {
        return Boolean.getBoolean(properties.getProperty(generateDiamondGeodes));
    }

    public boolean generateEchoGeodes() {
        return Boolean.getBoolean(properties.getProperty(generateEchoGeodes));
    }

    public void loadConfig() {
        this.loadDefaultConfig();
        this.properties = new Properties(this.defaultProperties);
        try (FileReader reader = new FileReader(this.getConfigFile())) {
            this.properties.load(reader);
        } catch (IOException e) {
            LOGGER.error("Could not load More Geodes config, using default config instead. Failed with exception: " + e);
        }
    }

    private void loadDefaultConfig() {
        defaultProperties.setProperty(generateEmeraldGeodes, "true");
        defaultProperties.setProperty(generateQuartzGeodes, "true");
        defaultProperties.setProperty(generateDiamondGeodes, "true");
        defaultProperties.setProperty(generateEchoGeodes, "true");
    }

    private File getConfigFile() {
        Path config = FabricLoader.getInstance()
                .getConfigDir()
                .resolve("geodes.properties");
        File file = config.toFile();
        assert file.isFile();
        return file;
    }

}
