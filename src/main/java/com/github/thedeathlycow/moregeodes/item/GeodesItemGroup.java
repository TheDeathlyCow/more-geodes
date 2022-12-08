package com.github.thedeathlycow.moregeodes.item;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class GeodesItemGroup {

    public static final ItemGroup MORE_GEODES = FabricItemGroupBuilder.create(new Identifier(MoreGeodes.MODID, "more_geodes"))
            .icon(() -> new ItemStack(ModItems.EMERALD_CLUSTER))
            .appendItems(stacks -> {
                // emerald
                stacks.add(new ItemStack(ModItems.EMERALD_GEODE));
                stacks.add(new ItemStack(ModItems.BUDDING_EMERALD));
                stacks.add(new ItemStack(ModItems.EMERALD_CLUSTER));
                stacks.add(new ItemStack(ModItems.LARGE_EMERALD_BUD));
                stacks.add(new ItemStack(ModItems.MEDIUM_EMERALD_BUD));
                stacks.add(new ItemStack(ModItems.SMALL_EMERALD_BUD));

                // quartz
                stacks.add(new ItemStack(ModItems.QUARTZ_GEODE));
                stacks.add(new ItemStack(ModItems.BUDDING_QUARTZ));
                stacks.add(new ItemStack(ModItems.QUARTZ_CLUSTER));
                stacks.add(new ItemStack(ModItems.LARGE_QUARTZ_BUD));
                stacks.add(new ItemStack(ModItems.MEDIUM_QUARTZ_BUD));
                stacks.add(new ItemStack(ModItems.SMALL_QUARTZ_BUD));

                // diamond
                stacks.add(new ItemStack(ModItems.DIAMOND_GEODE));
                stacks.add(new ItemStack(ModItems.DIAMOND_CLUSTER));

                // echo
                stacks.add(new ItemStack(ModItems.ECHO_BLOCK));
                stacks.add(new ItemStack(ModItems.BUDDING_ECHO_BLOCK));
                stacks.add(new ItemStack(ModItems.ECHO_CLUSTER));
                stacks.add(new ItemStack(ModItems.LARGE_ECHO_BUD));
                stacks.add(new ItemStack(ModItems.MEDIUM_ECHO_BUD));
                stacks.add(new ItemStack(ModItems.SMALL_ECHO_BUD));

                // lapis
                stacks.add(new ItemStack(ModItems.LAPIS_CRYSTAL_BLOCK));
                stacks.add(new ItemStack(ModItems.BUDDING_LAPIS));
                stacks.add(new ItemStack(ModItems.LAPIS_CLUSTER));
                stacks.add(new ItemStack(ModItems.LARGE_LAPIS_BUD));
                stacks.add(new ItemStack(ModItems.MEDIUM_LAPIS_BUD));
                stacks.add(new ItemStack(ModItems.SMALL_LAPIS_BUD));

                // gypsum
                stacks.add(new ItemStack(ModItems.GYPSUM_CRYSTAL_BLOCK));
                stacks.add(new ItemStack(ModItems.BUDDING_GYPSUM));
                stacks.add(new ItemStack(ModItems.GYPSUM_ROSE));
                stacks.add(new ItemStack(ModItems.LARGE_GYPSUM_BUD));
                stacks.add(new ItemStack(ModItems.MEDIUM_GYPSUM_BUD));
                stacks.add(new ItemStack(ModItems.SMALL_GYPSUM_BUD));

                // pyrite and calcite
                stacks.add(new ItemStack(ModItems.PYRITE_CHUNK));
                stacks.add(new ItemStack(ModItems.PYRITE));
                stacks.add(new ItemStack(ModItems.PYRITE_STAIRS));
                stacks.add(new ItemStack(ModItems.PYRITE_SLAB));
                stacks.add(new ItemStack(ModItems.PYRITE_WALL));
                stacks.add(new ItemStack(ModItems.CALCITE_STAIRS));
                stacks.add(new ItemStack(ModItems.CALCITE_SLAB));
                stacks.add(new ItemStack(ModItems.CALCITE_WALL));

                // echo locator
                stacks.add(new ItemStack(ModItems.ECHO_LOCATOR));
            })
            .build();

}
