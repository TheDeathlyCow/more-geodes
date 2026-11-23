package com.github.thedeathlycow.moregeodes.entity;

import com.github.thedeathlycow.moregeodes.sounds.GeodesSoundEvents;
import com.github.thedeathlycow.moregeodes.world.GeodesGameEvents;
import net.minecraft.network.syncher.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.*;
import net.minecraft.sounds.SoundSource;
public class EchoDisplay extends Display.BlockDisplay {
    private static final EntityDataAccessor<Integer> TTL=SynchedEntityData.defineId(EchoDisplay.class,EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> GLOW=SynchedEntityData.defineId(EchoDisplay.class,EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> PINGED=SynchedEntityData.defineId(EchoDisplay.class,EntityDataSerializers.BOOLEAN);
    public EchoDisplay(EntityType<?> type,Level level) {super(type,level);setInvisible(true);setPermanentlyInvulnerable(true);}
    @Override protected void defineSynchedData(SynchedEntityData.Builder data) {super.defineSynchedData(data);data.define(TTL,30);data.define(GLOW,Integer.MIN_VALUE);data.define(PINGED,false);}
    public void setGlowDelay(int delay) {entityData.set(GLOW,entityData.get(TTL));entityData.set(TTL,entityData.get(TTL)+delay);}
    @Override public void tick() {
        super.tick();
        if(level().isClientSide()) return;
        int ttl=entityData.get(TTL)-1;entityData.set(TTL,ttl);
        if(ttl<=entityData.get(GLOW) && !entityData.get(PINGED)) {
            entityData.set(PINGED,true);setInvisible(false);setGlowingTag(true);
            level().gameEvent(this,GeodesGameEvents.CRYSTAL_RESONATE,blockPosition());
            level().playSound(null,blockPosition(),GeodesSoundEvents.BLOCK_ECHO_LOCATOR_RESONATE,SoundSource.BLOCKS,3f,1f);
        }
        if(ttl<=0) discard();
    }
    @Override protected void readAdditionalSaveData(ValueInput in) {super.readAdditionalSaveData(in);entityData.set(TTL,in.getIntOr("time_to_live",30));entityData.set(GLOW,in.getIntOr("glow_time",Integer.MIN_VALUE));entityData.set(PINGED,in.getBooleanOr("is_pinged",false));}
    @Override protected void addAdditionalSaveData(ValueOutput out) {super.addAdditionalSaveData(out);out.putInt("time_to_live",entityData.get(TTL));out.putInt("glow_time",entityData.get(GLOW));out.putBoolean("is_pinged",entityData.get(PINGED));}
}
