package com.github.thedeathlycow.moregeodes.item;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class GeodesItemGroup {


    public static final ItemGroup MORE_GEODES = FabricItemGroup.builder(new Identifier(MoreGeodes.MODID, "more_geodes"))
            .icon(() -> new ItemStack(ModItems.EMERALD_CLUSTER))
            .entries(((context, entries) -> {
                // emerald
                entries.add(new ItemStack(ModItems.EMERALD_GEODE));
                entries.add(new ItemStack(ModItems.BUDDING_EMERALD));
                entries.add(new ItemStack(ModItems.EMERALD_CLUSTER));
                entries.add(new ItemStack(ModItems.LARGE_EMERALD_BUD));
                entries.add(new ItemStack(ModItems.MEDIUM_EMERALD_BUD));
                entries.add(new ItemStack(ModItems.SMALL_EMERALD_BUD));

                // quartz
                entries.add(new ItemStack(ModItems.QUARTZ_GEODE));
                entries.add(new ItemStack(ModItems.BUDDING_QUARTZ));
                entries.add(new ItemStack(ModItems.QUARTZ_CLUSTER));
                entries.add(new ItemStack(ModItems.LARGE_QUARTZ_BUD));
                entries.add(new ItemStack(ModItems.MEDIUM_QUARTZ_BUD));
                entries.add(new ItemStack(ModItems.SMALL_QUARTZ_BUD));

                // diamond
                entries.add(new ItemStack(ModItems.DIAMOND_GEODE));
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

                // echo locator
                entries.add(new ItemStack(ModItems.ECHO_LOCATOR));
            }))
            .build();

    public static void check() {
        // do nothing, just make sure the class isnt stripped
    }
}
