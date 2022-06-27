package com.github.thedeathlycow.moregeodes.blocks.entity;

import com.github.thedeathlycow.moregeodes.blocks.EchoLocatorType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIntArray;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EchoLocatorBlockEntity extends BlockEntity {

    private static final Vec3i scanRadius = new Vec3i(30, 30, 30);
    private static final int MAX_PING_TIME = 20 * 20;

    private int pingTicks = 0;
    private final List<BlockPos> pinging = new ArrayList<>();
    private EchoLocatorType type = EchoLocatorType.EMPTY;

    public EchoLocatorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ECHO_LOCATOR, pos, state);
    }

    public static void tick(ServerWorld world, BlockPos origin, BlockState state, EchoLocatorBlockEntity blockEntity) {
        if (blockEntity.isPinging() && !world.isClient()) {
            blockEntity.pingTicks++;
            for (BlockPos pos : List.copyOf(blockEntity.pinging)) {
                BlockState atState = world.getBlockState(pos);
                if (!highlightBlock(blockEntity, world, Vec3d.ofCenter(origin), pos, atState)) {
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
    private static boolean highlightBlock(EchoLocatorBlockEntity blockEntity, ServerWorld world, Vec3d locatorPos, BlockPos pos, BlockState state) {
        if (state.isIn(blockEntity.type.canLocate())) {
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

    public boolean isPinging() {
        return this.pingTicks < MAX_PING_TIME;
    }

    public void setType(EchoLocatorType type) {
        this.type = type;
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.putInt("PingTicks", this.pingTicks);

        nbt.put("Type", this.type.toNbt());

        NbtList pinging = new NbtList();
        for (BlockPos pingingPos : this.pinging) {
            NbtIntArray pos = new NbtIntArray(new int[]{pingingPos.getX(), pingingPos.getY(), pingingPos.getZ()});
            pinging.add(pos);
        }
        nbt.put("Pinging", pinging);

        super.writeNbt(nbt);
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
    }
}
