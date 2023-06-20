package com.github.thedeathlycow.moregeodes.mixin;

import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class Plugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {
        // do nothing
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, @NotNull String mixinClassName) {
        String thisPackageName = Plugin.class.getPackageName();

        if (!mixinClassName.startsWith(thisPackageName)) {
            return true;
        }

        int numPackages = thisPackageName.split("\\.").length;
        String[] splitMixinClassName = mixinClassName.split("\\.");

        boolean isCompatClass = splitMixinClassName.length > numPackages
                && splitMixinClassName[numPackages].equalsIgnoreCase("compat");

        if (isCompatClass) {
            return switch (splitMixinClassName[numPackages + 1]) {
                case "carpet" -> FabricLoader.getInstance().isModLoaded("carpet");
                default -> true;
            };
        }

        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
        // do nothing
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        // do nothing
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        // do nothing
    }
}
