package com.github.thedeathlycow.moregeodes.blocks.entity;

import com.github.thedeathlycow.moregeodes.tag.GeodesGameEventTags;
import com.github.thedeathlycow.moregeodes.world.GeodesGameEvents;
import net.minecraft.block.SculkSensorBlock;
import net.minecraft.entity.Entity;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.event.PositionSource;
import net.minecraft.world.event.Vibrations;
import org.jetbrains.annotations.Nullable;

public class EchoLocatorCallback implements Vibrations.Callback {

    private final EchoLocatorBlockEntity owner;
    private final int range;
    private final PositionSource positionSource;

    public EchoLocatorCallback(EchoLocatorBlockEntity owner, int range, PositionSource positionSource) {
        this.owner = owner;
        this.range = range;
        this.positionSource = positionSource;
    }

    @Override
    public int getRange() {
        return this.range;
    }

    @Override
    public PositionSource getPositionSource() {
        return this.positionSource;
    }

    @Override
    public boolean accepts(ServerWorld world, BlockPos pos, GameEvent event, GameEvent.Emitter emitter) {
        return !this.owner.isRemoved()
                && event.equals(GeodesGameEvents.CRYSTAL_RESONATE)
                && owner.isPinging();
    }

    @Override
    public void accept(ServerWorld world, BlockPos pos, GameEvent event, @Nullable Entity sourceEntity, @Nullable Entity entity, float distance) {
        
    }

    @Override
    public TagKey<GameEvent> getTag() {
        return GeodesGameEventTags.ECHO_LOCATOR_CAN_LISTEN;
    }
}
