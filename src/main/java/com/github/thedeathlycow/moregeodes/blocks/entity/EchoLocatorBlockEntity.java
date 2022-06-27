package com.github.thedeathlycow.moregeodes.blocks.entity;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.blocks.EchoLocatorType;
import com.github.thedeathlycow.moregeodes.tag.GeodesGameEventTags;
import com.github.thedeathlycow.moregeodes.world.GeodesGameEvents;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.tag.GameEventTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.event.BlockPositionSource;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.event.listener.GameEventListener;
import net.minecraft.world.event.listener.VibrationListener;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EchoLocatorBlockEntity extends BlockEntity implements VibrationListener.Callback {

    private static final Vec3i scanRadius = new Vec3i(30, 30, 30);
    private static final int MAX_PING_TIME = 20 * 20;
    private static final int TICKS_PER_PING = 20;

    private int pingTicks = 0;
    private final List<BlockPos> pinging = new ArrayList<>();
    private EchoLocatorType type = EchoLocatorType.EMPTY;
    private VibrationListener vibrationListener;

    public EchoLocatorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ECHO_LOCATOR, pos, state);
        this.vibrationListener = new VibrationListener(new BlockPositionSource(this.pos), 8, this, null, 0.0F, 0);
    }

    public static void tick(ServerWorld world, BlockPos origin, BlockState state, EchoLocatorBlockEntity blockEntity) {
        if (blockEntity.isPinging() && !world.isClient()) {
            blockEntity.pingTicks++;
            blockEntity.vibrationListener.tick(world);
            if (blockEntity.pingTicks % TICKS_PER_PING != 0) {
                return;
            }
            for (BlockPos pos : List.copyOf(blockEntity.pinging)) {
                BlockState atState = world.getBlockState(pos);
                if (!highlightBlock(blockEntity, world, pos, atState)) {
                    blockEntity.pinging.remove(pos);
                }
            }
        }
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, EchoLocatorBlockEntity blockEntity) {
    }


    public static void serverTick(World world, BlockPos pos, BlockState state, EchoLocatorBlockEntity blockEntity) {
        tick((ServerWorld) world, pos, state, blockEntity);
    }

    /**
     * Highlights the block at the given location.
     *
     * @return Returns whether the state was highlighted
     */
    private static boolean highlightBlock(EchoLocatorBlockEntity blockEntity, ServerWorld world, BlockPos pos, BlockState state) {
        if (state.isIn(blockEntity.type.canLocate())) {
            world.emitGameEvent(null, GeodesGameEvents.CRYSTAL_RESONATE, pos);
            world.playSound(null, pos, blockEntity.type.resonateSound(), SoundCategory.BLOCKS, 2.5f, 1.0f);
            return true;
        } else {
            return false;
        }
    }

    public void activate(World world, BlockPos origin) {
        BlockPos from = origin.subtract(scanRadius);
        BlockPos to = origin.add(scanRadius);
        this.pingTicks = 0;
        this.pinging.clear();
        for (BlockPos pos : BlockPos.iterate(from, to)) {
            BlockState state = world.getBlockState(pos);
            if (state.isIn(this.type.canLocate())) {
                this.pinging.add(pos.toImmutable());
            }
        }
    }

    @Override
    public TagKey<GameEvent> getTag() {
        return GeodesGameEventTags.ECHO_LOCATOR_CAN_LISTEN;
    }

    @Override
    public boolean accepts(ServerWorld world, GameEventListener listener, BlockPos pos, GameEvent event, GameEvent.Emitter emitter) {
        return !this.isRemoved()
                && event.equals(GeodesGameEvents.CRYSTAL_RESONATE)
                && this.isPinging();
    }

    @Override
    public void accept(ServerWorld world, GameEventListener listener, BlockPos pos, GameEvent event, @Nullable Entity entity, @Nullable Entity sourceEntity, float distance) {

    }

    public VibrationListener getVibrationListener() {
        return this.vibrationListener;
    }

    public boolean isPinging() {
        return this.pingTicks < MAX_PING_TIME;
    }

    public void setType(EchoLocatorType type) {
        this.type = type;
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("PingTicks", this.pingTicks);

        nbt.put("Type", this.type.toNbt());

        NbtList pinging = new NbtList();
        for (BlockPos pingingPos : this.pinging) {
            NbtIntArray pos = new NbtIntArray(new int[]{pingingPos.getX(), pingingPos.getY(), pingingPos.getZ()});
            pinging.add(pos);
        }
        nbt.put("Pinging", pinging);

        DataResult<NbtElement> listener = VibrationListener.createCodec(this).encodeStart(NbtOps.INSTANCE, this.vibrationListener);
        listener.resultOrPartial(MoreGeodes.LOGGER::error).ifPresent((nbtElement) -> {
            nbt.put("listener", nbtElement);
        });
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.pingTicks = nbt.getInt("PingTicks");

        this.pinging.clear();
        NbtList nbtList = nbt.getList("Pinging", NbtList.COMPOUND_TYPE);
        for (int i = 0; i < nbtList.size(); i++) {
            NbtList pos = nbtList.getList(i);
            this.pinging.add(new BlockPos(pos.getInt(0), pos.getInt(1), pos.getInt(2)));
        }

        if (nbt.contains("Type")) {
            this.type = EchoLocatorType.fromNbt(nbt.getCompound("Type"));
        } else {
            this.type = EchoLocatorType.EMPTY;
        }

        if (nbt.contains("listener", NbtElement.COMPOUND_TYPE)) {
            DataResult<VibrationListener> listener = VibrationListener.createCodec(this)
                    .parse(new Dynamic<>(NbtOps.INSTANCE, nbt.getCompound("listener")));

            listener.resultOrPartial(MoreGeodes.LOGGER::error).ifPresent((ls) -> {
                this.vibrationListener = ls;
            });
        }
    }
}
