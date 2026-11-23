package com.github.thedeathlycow.moregeodes.entity;

import com.github.thedeathlycow.moregeodes.sounds.GeodesSoundEvents;
import com.github.thedeathlycow.moregeodes.world.GeodesGameEvents;
import net.minecraft.network.syncher.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.*;
import net.minecraft.sounds.SoundSource;
public class EchoDisplay extends Display.BlockDisplay {
    // Retain the V1 metadata layout for mixed-version packet decoding. These
    // retired slots stay at their defaults and never dirty the tracker.
    private static final EntityDataAccessor<Integer> TTL=SynchedEntityData.defineId(EchoDisplay.class,EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> GLOW=SynchedEntityData.defineId(EchoDisplay.class,EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> PINGED=SynchedEntityData.defineId(EchoDisplay.class,EntityDataSerializers.BOOLEAN);
    private int timeToLive = 30;
    private int glowTime = Integer.MIN_VALUE;
    private boolean pinged;
    public EchoDisplay(EntityType<?> type,Level level) {super(type,level);setInvisible(true);setPermanentlyInvulnerable(true);}
    @Override protected void defineSynchedData(SynchedEntityData.Builder data) {super.defineSynchedData(data);data.define(TTL,30);data.define(GLOW,Integer.MIN_VALUE);data.define(PINGED,false);}
    public void setGlowDelay(int delay) {glowTime=timeToLive;timeToLive+=delay;}
    @Override public void tick() {
        super.tick();
        if(level().isClientSide()) return;
        int ttl=--timeToLive;
        if(ttl<=glowTime && !pinged) {
            pinged=true;setInvisible(false);setGlowingTag(true);
            level().gameEvent(this,GeodesGameEvents.CRYSTAL_RESONATE,blockPosition());
            level().playSound(null,blockPosition(),GeodesSoundEvents.BLOCK_ECHO_LOCATOR_RESONATE,SoundSource.BLOCKS,3f,1f);
        }
        if(ttl<=0) discard();
    }
    @Override protected void readAdditionalSaveData(ValueInput in) {super.readAdditionalSaveData(in);timeToLive=in.getIntOr("time_to_live",30);glowTime=in.getIntOr("glow_time",Integer.MIN_VALUE);pinged=in.getBooleanOr("is_pinged",false);}
    @Override protected void addAdditionalSaveData(ValueOutput out) {super.addAdditionalSaveData(out);out.putInt("time_to_live",timeToLive);out.putInt("glow_time",glowTime);out.putBoolean("is_pinged",pinged);}
}
