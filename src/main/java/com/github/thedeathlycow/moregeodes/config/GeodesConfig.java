package com.github.thedeathlycow.moregeodes.config;

import com.mojang.logging.LogUtils;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;

import java.io.*;
import java.nio.file.Path;
import java.util.Properties;

public final class GeodesConfig {

    private static final Logger LOGGER = LogUtils.getLogger();

    private static final String generateEmeraldGeodes = "generate_emerald_geodes";
    private static final String generateQuartzGeodes = "generate_quartz_geodes";
    private static final String generateDiamondGeodes = "generate_diamond_geodes";
    private static final String generateEchoGeodes = "generate_echo_geodes";
    private static final String generateLapisGeodes = "generate_lapis_geodes";
    private static final String generateGypsumPatches = "generate_gypsum_patches";
    private static final String generateCertusGeodes = "generate_certus_geodes";

    private Properties properties;
    private final Properties defaultProperties = new Properties();

    public GeodesConfig() {
    }

    public boolean generateEmeraldGeodes() {
        return Boolean.parseBoolean(properties.getProperty(generateEmeraldGeodes));
    }

    public boolean generateQuartzGeodes() {
        return Boolean.parseBoolean(properties.getProperty(generateQuartzGeodes));
    }

    public boolean generateDiamondGeodes() {
        return Boolean.parseBoolean(properties.getProperty(generateDiamondGeodes));
    }

    public boolean generateEchoGeodes() {
        return Boolean.parseBoolean(properties.getProperty(generateEchoGeodes));
    }

    public boolean generateLapisGeodes() {
        return Boolean.parseBoolean(properties.getProperty(generateLapisGeodes));
    }

    public boolean generateGypsumPatches() {
        return Boolean.parseBoolean(properties.getProperty(generateGypsumPatches));
    }

    public boolean generateCertusGeodes() {
        return Boolean.parseBoolean(properties.getProperty(generateCertusGeodes));
    }

    public void loadConfig() {
        this.loadDefaultConfig();
        this.properties = new Properties(this.defaultProperties);
        try (FileReader reader = new FileReader(this.getConfigFile())) {
            this.properties.load(reader);
        } catch (FileNotFoundException ignored) {
            this.generateDefaultConfig();
        } catch (IOException e) {
            LOGGER.error("Could not load More Geodes config, using default config instead. Failed with exception: " + e);
            return;
        }
        LOGGER.info("Loaded More Geodes config!");
    }

    private void generateDefaultConfig() {

        try (FileWriter writer = new FileWriter(this.getConfigFile())) {
            this.defaultProperties.store(writer, "Default config file for More Geodes. See the readme on GitHub for more information: https://github.com/TheDeathlyCow/more-geodes/blob/main/README.md");
        } catch (IOException e) {
            LOGGER.error("Error creating default More Geodes config: " + e);
            return;
        }

        LOGGER.info("Generated default config for More Geodes");

    }

    private void loadDefaultConfig() {
        defaultProperties.setProperty(generateEmeraldGeodes, "true");
        defaultProperties.setProperty(generateQuartzGeodes, "true");
        defaultProperties.setProperty(generateDiamondGeodes, "true");
        defaultProperties.setProperty(generateEchoGeodes, "true");
        defaultProperties.setProperty(generateLapisGeodes, "true");
        defaultProperties.setProperty(generateGypsumPatches, "true");
        defaultProperties.setProperty(generateCertusGeodes, "true");
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
