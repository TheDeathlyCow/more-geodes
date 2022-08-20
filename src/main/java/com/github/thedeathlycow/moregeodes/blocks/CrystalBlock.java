package com.github.thedeathlycow.moregeodes.blocks;

import com.github.thedeathlycow.moregeodes.sounds.CrystalBlockSoundGroup;
import net.minecraft.block.AmethystBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CrystalBlock extends AmethystBlock {

    private final CrystalBlockSoundGroup hitSoundGroup;

    public CrystalBlock(CrystalBlockSoundGroup hitSoundGroup, Settings settings) {
        super(settings);
        this.hitSoundGroup = hitSoundGroup;
    }

    @Override
    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        if (!world.isClient) {
            BlockPos blockPos = hit.getBlockPos();
            world.playSound(null, blockPos, hitSoundGroup.hitSound(), SoundCategory.BLOCKS, 1.0F, 0.5F + world.random.nextFloat());
            world.playSound(null, blockPos, hitSoundGroup.chimeSound(), SoundCategory.BLOCKS, 1.0F, 0.5F + world.random.nextFloat());
        }
    }
}
