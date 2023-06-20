package com.github.thedeathlycow.moregeodes.entity;

import com.github.thedeathlycow.moregeodes.blocks.entity.EchoLocatorBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class EchoDisplay extends DisplayEntity.BlockDisplayEntity {

    private int timeToLive = EchoLocatorBlockEntity.TICKS_PER_PING / 2;

    private static final String TTL_NBT_KEY = "TimeToLive";

    public EchoDisplay(EntityType<?> entityType, World world) {
        super(entityType, world);
    }


    @Override
    public void tick() {
        super.tick();

        timeToLive--;

        if (timeToLive <= 0) {
            this.discard();
        }

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        timeToLive = nbt.getInt(TTL_NBT_KEY);
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt(TTL_NBT_KEY, timeToLive);
    }

}
