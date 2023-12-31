package com.github.thedeathlycow.moregeodes.entity;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.sounds.GeodesSoundEvents;
import com.github.thedeathlycow.moregeodes.world.GeodesGameEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EchoDisplay extends DisplayEntity.BlockDisplayEntity {

    private static final String TTL_NBT_KEY = "time_to_live";
    private static final String GLOW_TIME_NBT_KEY = "glow_time";
    private static final String IS_PINGED_NBT_KEY = "is_pinged";

    private static final int BASE_TTL = 30;
    private static final int BASE_GLOW_TIME = Integer.MIN_VALUE;
    private static final TrackedData<Integer> TIME_TO_LIVE = DataTracker.registerData(
            EchoDisplay.class,
            TrackedDataHandlerRegistry.INTEGER
    );

    private static final TrackedData<Integer> GLOW_TIME = DataTracker.registerData(
            EchoDisplay.class,
            TrackedDataHandlerRegistry.INTEGER
    );

    private static final TrackedData<Boolean> IS_PINGED = DataTracker.registerData(
            EchoDisplay.class,
            TrackedDataHandlerRegistry.BOOLEAN
    );

    public EchoDisplay(EntityType<?> entityType, World world) {
        super(entityType, world);
        this.setInvisible(true);
        this.setInvulnerable(true);
    }

    public void setGlowDelay(int delay) {
        int ttl = this.getTimeToLive();
        this.setGlowTime(ttl);
        this.setTimeToLive(ttl + delay);
    }

    @Override
    public void tick() {
        super.tick();

        int timeToLive = this.getTimeToLive();
        timeToLive--;
        this.setTimeToLive(timeToLive);

        if (timeToLive <= this.getGlowTime() && !this.isPinged()) {
            this.ping();
        }

        if (timeToLive <= 0) {
            this.discard();
        }

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setTimeToLive(nbt.getInt(TTL_NBT_KEY));
        this.setGlowTime(nbt.getInt(GLOW_TIME_NBT_KEY));
        this.setPinged(nbt.getBoolean(GLOW_TIME_NBT_KEY));
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt(TTL_NBT_KEY, this.getTimeToLive());
        nbt.putInt(GLOW_TIME_NBT_KEY, this.getGlowTime());
        nbt.putBoolean(IS_PINGED_NBT_KEY, this.isPinged());
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(TIME_TO_LIVE, BASE_TTL);
        this.dataTracker.startTracking(GLOW_TIME, BASE_GLOW_TIME);
        this.dataTracker.startTracking(IS_PINGED, false);
    }

    private void ping() {
        this.setInvisible(false);
        this.setPinged(true);
        this.setGlowing(true);
        World world = this.getWorld();
        BlockPos pos = this.getBlockPos();
        world.emitGameEvent(this, GeodesGameEvents.CRYSTAL_RESONATE, pos);
        world.playSound(
                null,
                pos,
                GeodesSoundEvents.BLOCK_ECHO_LOCATOR_RESONATE, SoundCategory.BLOCKS,
                1.0f, 1.0f
        );
    }

    private int getGlowTime() {
        return this.dataTracker.get(GLOW_TIME);
    }

    private void setGlowTime(int value) {
        this.dataTracker.set(GLOW_TIME, value);
    }

    private int getTimeToLive() {
        return this.dataTracker.get(TIME_TO_LIVE);
    }

    private void setTimeToLive(int value) {
        this.dataTracker.set(TIME_TO_LIVE, value);
    }

    private boolean isPinged() {
        return this.dataTracker.get(IS_PINGED);
    }

    private void setPinged(boolean value) {
        this.dataTracker.set(IS_PINGED, value);
    }
}
