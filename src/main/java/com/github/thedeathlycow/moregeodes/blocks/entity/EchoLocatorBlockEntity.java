package com.github.thedeathlycow.moregeodes.blocks.entity;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.blocks.EchoLocatorType;
import com.github.thedeathlycow.moregeodes.tag.GeodesGameEventTags;
import com.github.thedeathlycow.moregeodes.tag.GeodesPoiTypeTags;
import com.github.thedeathlycow.moregeodes.world.GeodesGameEvents;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.event.BlockPositionSource;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.event.listener.GameEventListener;
import net.minecraft.world.event.listener.VibrationListener;
import net.minecraft.world.poi.PointOfInterest;
import net.minecraft.world.poi.PointOfInterestStorage;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class EchoLocatorBlockEntity extends BlockEntity implements VibrationListener.Callback {
    public static final int SCAN_RADIUS = 30;
    private static final Vec3i SCAN_BOX = new Vec3i(SCAN_RADIUS, SCAN_RADIUS, SCAN_RADIUS);
    private static final int MAX_PING_TIME = 20 * 20;
    private static final int TICKS_PER_PING = 20;

    private int pingTicks = 0;
    private final List<BlockPos> pinging = new ArrayList<>();
    private EchoLocatorType type = EchoLocatorType.CRYSTAL;
    private VibrationListener vibrationListener;

    public EchoLocatorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ECHO_LOCATOR, pos, state);
        this.vibrationListener = new VibrationListener(new BlockPositionSource(this.pos), SCAN_RADIUS, this, null, 0.0F, 0);
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
        if (state.isIn(blockEntity.type.validBlocks())) {
            world.emitGameEvent(null, GeodesGameEvents.CRYSTAL_RESONATE, pos);
            world.playSound(null, pos, blockEntity.type.resonateSound(), SoundCategory.BLOCKS, 2.5f, 1.0f);
            return true;
        } else {
            return false;
        }
    }

    public void activate(World world, BlockPos origin) {
        if (world instanceof ServerWorld serverWorld) {
            this.pingTicks = 0;
            this.pinging.clear();
            Stream<PointOfInterest> positions = serverWorld.getPointOfInterestStorage().getInCircle(
                    (poiType) -> this.type.pointsOfInterest().contains(poiType.value()),
                    origin, SCAN_RADIUS, PointOfInterestStorage.OccupationStatus.ANY
            );

            positions.forEach(
                    (poi) -> {
                        this.pinging.add(poi.getPos());
                    }
            );
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
            this.type = EchoLocatorType.CRYSTAL;
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
