package com.github.thedeathlycow.moregeodes.testmod;

import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.test.GameTest;
import net.minecraft.test.TestContext;
import net.minecraft.util.math.BlockPos;

public class LargeCrystalBreakTests implements FabricGameTest {

    @GameTest(templateName = "geodes-test:large_crystal_break_tests.top.piston")
    public void large_crystal_breaks_base_when_broke_by_piston(TestContext context) {
        context.pushButton(1, 2, 0);
        context.expectBlockAtEnd(Blocks.AIR, 0, 2, 1);
    }
}
