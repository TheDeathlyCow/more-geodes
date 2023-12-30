package com.github.thedeathlycow.moregeodes.item.tuning;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class TuningRegistry implements SimpleSynchronousResourceReloadListener {

    private static TuningRegistry instance = null;

    private static final Identifier ID = new Identifier(MoreGeodes.MODID, "tunings");

    private final Map<Identifier, Tuning> tunings = new HashMap<>();

    public static TuningRegistry getInstance() {
        if (instance == null) {
            instance = new TuningRegistry();
        }
        return instance;
    }

    @Override
    public Identifier getFabricId() {
        return ID;
    }

    @Nullable
    public Tuning getTuning(Identifier id) {
        if (!this.tunings.containsKey(id)) {
            return null;
        }

        return this.tunings.get(id);
    }

    @Override
    public void reload(ResourceManager manager) {

        Map<Identifier, Tuning> newTunings = new HashMap<>();

        var resources = manager.findResources(
                "geodes_tunings",
                path -> path.getPath().endsWith(".json")
        );

        for (Map.Entry<Identifier, Resource> entry : resources.entrySet()) {

            try (BufferedReader reader = entry.getValue().getReader()) {
                Tuning tuning = Tuning.Serializer.GSON.fromJson(reader, Tuning.class);
                newTunings.put(entry.getKey(), tuning);
            } catch (Exception e) {
                MoreGeodes.LOGGER.error("Error occurred while loading tuning ID " + entry.getKey().toString(), e);
            }

        }

        this.tunings.clear();
        this.tunings.putAll(newTunings);

        MoreGeodes.LOGGER.info("Loaded {} crystal tunings", newTunings.size());
    }

    private TuningRegistry() {

    }
}
