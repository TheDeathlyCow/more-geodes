package com.github.thedeathlycow.moregeodes.blocks;

import com.github.thedeathlycow.moregeodes.sounds.CrystalBlockSoundGroup;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class CrystalBlock extends AmethystBlock {

    private final CrystalBlockSoundGroup hitSoundGroup;

    public CrystalBlock(CrystalBlockSoundGroup hitSoundGroup, Properties settings) {
        super(settings);
        this.hitSoundGroup = hitSoundGroup;
    }

    @Override
    public void onProjectileHit(Level world, BlockState state, BlockHitResult hit, Projectile projectile) {
        if (!world.isClientSide()) {
            BlockPos blockPos = hit.getBlockPos();
            world.playSound(null, blockPos, hitSoundGroup.hitSound(), SoundSource.BLOCKS, 1.0F, 0.5F + world.getRandom().nextFloat());
            world.playSound(null, blockPos, hitSoundGroup.chimeSound(), SoundSource.BLOCKS, 1.0F, 0.5F + world.getRandom().nextFloat());
        }
    }

    public CrystalBlockSoundGroup getHitSoundGroup() {
        return this.hitSoundGroup;
    }
}
