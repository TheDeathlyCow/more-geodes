package com.github.thedeathlycow.moregeodes.item;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class GeodesItemGroup {


    public static final RegistryKey<ItemGroup> MORE_GEODES = RegistryKey.of(
            RegistryKeys.ITEM_GROUP,
            new Identifier(MoreGeodes.MODID, "main")
    );

    public static void register() {
        Registry.register(
                Registries.ITEM_GROUP,
                MORE_GEODES,
                FabricItemGroup.builder()
                        .icon(() -> new ItemStack(ModItems.EMERALD_CLUSTER))
                        .displayName(Text.translatable("itemGroup.geodes.more_geodes"))
                        .entries(((context, entries) -> {
                            // emerald
                            entries.add(new ItemStack(ModItems.EMERALD_CRYSTAL_BLOCK));
                            entries.add(new ItemStack(ModItems.BUDDING_EMERALD));
                            entries.add(new ItemStack(ModItems.EMERALD_CLUSTER));
                            entries.add(new ItemStack(ModItems.LARGE_EMERALD_BUD));
                            entries.add(new ItemStack(ModItems.MEDIUM_EMERALD_BUD));
                            entries.add(new ItemStack(ModItems.SMALL_EMERALD_BUD));

                            // quartz
                            entries.add(new ItemStack(ModItems.QUARTZ_CRYSTAL_BLOCK));
                            entries.add(new ItemStack(ModItems.BUDDING_QUARTZ));
                            entries.add(new ItemStack(ModItems.QUARTZ_CLUSTER));
                            entries.add(new ItemStack(ModItems.LARGE_QUARTZ_BUD));
                            entries.add(new ItemStack(ModItems.MEDIUM_QUARTZ_BUD));
                            entries.add(new ItemStack(ModItems.SMALL_QUARTZ_BUD));

                            // diamond
                            entries.add(new ItemStack(ModItems.DIAMOND_CRYSTAL_BLOCK));
                            entries.add(new ItemStack(ModItems.DIAMOND_CLUSTER));

                            // echo
                            entries.add(new ItemStack(ModItems.ECHO_BLOCK));
                            entries.add(new ItemStack(ModItems.BUDDING_ECHO_BLOCK));
                            entries.add(new ItemStack(ModItems.ECHO_CLUSTER));
                            entries.add(new ItemStack(ModItems.LARGE_ECHO_BUD));
                            entries.add(new ItemStack(ModItems.MEDIUM_ECHO_BUD));
                            entries.add(new ItemStack(ModItems.SMALL_ECHO_BUD));

                            // lapis
                            entries.add(new ItemStack(ModItems.LAPIS_CRYSTAL_BLOCK));
                            entries.add(new ItemStack(ModItems.BUDDING_LAPIS));
                            entries.add(new ItemStack(ModItems.LAPIS_CLUSTER));
                            entries.add(new ItemStack(ModItems.LARGE_LAPIS_BUD));
                            entries.add(new ItemStack(ModItems.MEDIUM_LAPIS_BUD));
                            entries.add(new ItemStack(ModItems.SMALL_LAPIS_BUD));

                            // gypsum
                            entries.add(new ItemStack(ModItems.GYPSUM_CRYSTAL_BLOCK));
                            entries.add(new ItemStack(ModItems.BUDDING_GYPSUM));
                            entries.add(new ItemStack(ModItems.GYPSUM_ROSE));
                            entries.add(new ItemStack(ModItems.LARGE_GYPSUM_BUD));
                            entries.add(new ItemStack(ModItems.MEDIUM_GYPSUM_BUD));
                            entries.add(new ItemStack(ModItems.SMALL_GYPSUM_BUD));
                            entries.add(new ItemStack(ModItems.GYPSUM_SHARD));

                            // pyrite and calcite
                            entries.add(new ItemStack(ModItems.PYRITE_CHUNK));
                            entries.add(new ItemStack(ModItems.PYRITE));
                            entries.add(new ItemStack(ModItems.PYRITE_STAIRS));
                            entries.add(new ItemStack(ModItems.PYRITE_SLAB));
                            entries.add(new ItemStack(ModItems.PYRITE_WALL));
                            entries.add(new ItemStack(Items.CALCITE));
                            entries.add(new ItemStack(ModItems.CALCITE_STAIRS));
                            entries.add(new ItemStack(ModItems.CALCITE_SLAB));
                            entries.add(new ItemStack(ModItems.CALCITE_WALL));

                            entries.add(new ItemStack(ModItems.GABRRO));

                            // locators
                            entries.add(new ItemStack(ModItems.CRYSTAL_LOCATOR));
                            entries.add(new ItemStack(ModItems.ECHO_LOCATOR));
                            entries.addAll(getTunedCrystalLocators());
                        }))
                        .build()
        );
    }

    private static List<ItemStack> getTunedCrystalLocators() {

        List<ItemStack> entries = new ArrayList<>(
                List.of(
                        makeTunedLocatorStack("geodes:amethyst"),
                        makeTunedLocatorStack("geodes:emerald"),
                        makeTunedLocatorStack("geodes:quartz"),
                        makeTunedLocatorStack("geodes:diamond"),
                        makeTunedLocatorStack("geodes:echo"),
                        makeTunedLocatorStack("geodes:lapis"),
                        makeTunedLocatorStack("geodes:gypsum")
                )
        );

        if (FabricLoader.getInstance().isModLoaded("spectrum")) {
            entries.add(makeTunedLocatorStack("geodes:spectrum/topaz"));
            entries.add(makeTunedLocatorStack("geodes:spectrum/citrine"));
        }

        return entries;
    }

    private static ItemStack makeTunedLocatorStack(String tuningId) {
        var stack = new ItemStack(ModItems.TUNED_CRYSTAL_LOCATOR);

        var nbt = new NbtCompound();
        nbt.putString(TunedCrystalLocator.TUNING_ID_NBT_KEY, tuningId);
        stack.setSubNbt(TunedCrystalLocator.TUNING_NBT_KEY, nbt);

        return stack;
    }

}
