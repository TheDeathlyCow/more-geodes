package com.github.thedeathlycow.moregeodes.item;

import com.github.thedeathlycow.moregeodes.entity.*;
import com.github.thedeathlycow.moregeodes.item.tuning.Tuning;
import com.github.thedeathlycow.moregeodes.sounds.GeodesSoundEvents;
import java.util.function.Consumer;
import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
public class CrystalLocatorItem extends Item {
    public static final int CRYSTAL_LOCATOR_RANGE=48, CRYSTAL_LOCATOR_COOL_DOWN=20;
    private static final SearchVolume CRYSTAL_SEARCH = new SearchVolume(CRYSTAL_LOCATOR_RANGE);
    private static final SearchVolume ECHO_SEARCH = new SearchVolume(6);
    private final SearchVolume search;
    private final int coolDown;
    public CrystalLocatorItem(Properties properties,int range,int cooldown) {
        super(properties);
        this.search = range == CRYSTAL_LOCATOR_RANGE ? CRYSTAL_SEARCH : range == 6 ? ECHO_SEARCH : new SearchVolume(range);
        this.coolDown = cooldown;
    }
    @Override public InteractionResult use(Level level,Player player,InteractionHand hand) {
        ItemStack stack=player.getItemInHand(hand);
        if(level instanceof ServerLevel server) {
            Identifier legacy=TunedCrystalLocatorItem.tuningId(stack);
            if(legacy!=null && !stack.has(ModComponents.TUNING)) stack.set(ModComponents.TUNING,legacy);
            int count=activate(server,player.blockPosition(),stack);
            if(count>0) stack.hurtAndBreak(1,player,hand==InteractionHand.MAIN_HAND?EquipmentSlot.MAINHAND:EquipmentSlot.OFFHAND);
            setCooldown(player.getCooldowns(),coolDown);
            player.playSound(GeodesSoundEvents.BLOCK_ECHO_LOCATOR_USE,1f,1f);
        }
        return InteractionResult.SUCCESS;
    }
    @Override public void appendHoverText(ItemStack stack,TooltipContext context,TooltipDisplay display,Consumer<Component> output,TooltipFlag flag) {
        output.accept(Tuning.UNTUNED.getDescription());
    }
    public boolean isTuned(ItemStack stack,@Nullable Level level) { return false; }
    protected boolean isPingableCrystal(ItemStack stack,ServerLevel level,BlockPos pos) { return false; }
    /** Resolve activation-wide state once, while retaining the existing subclass hook. */
    protected @Nullable Predicate<BlockPos> prepareMatcher(ItemStack stack, ServerLevel level) {
        return getClass() == CrystalLocatorItem.class ? null : pos -> isPingableCrystal(stack, level, pos);
    }
    protected void setCooldown(ItemCooldowns cooldowns,int ticks) {
        cooldowns.addCooldown(ModItems.CRYSTAL_LOCATOR.getDefaultInstance(),ticks);
        cooldowns.addCooldown(ModItems.TUNED_CRYSTAL_LOCATOR.getDefaultInstance(),ticks);
    }
    public int activate(ServerLevel level,BlockPos origin,ItemStack stack) {
        Predicate<BlockPos> matcher = prepareMatcher(stack, level);
        if (matcher == null) return 0;
        int count = 0;
        int row = 0;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        // Match BlockPos.betweenClosed's X, then Y, then Z order. Only offsets
        // satisfying the original 3 * squared-distance predicate are visited.
        for (int dz = -search.extent; dz <= search.extent; dz++) {
            for (int dy = -search.extent; dy <= search.extent; dy++) {
                int maxX = search.rowMaxX[row++];
                int yzSquared = dy * dy + dz * dz;
                for (int dx = -maxX; dx <= maxX; dx++) {
                    pos.set(origin.getX() + dx, origin.getY() + dy, origin.getZ() + dz);
                    if (!matcher.test(pos)) continue;
                    EchoDisplay display = GeodesEntityTypes.ECHO_DISPLAY.create(level, EntitySpawnReason.TRIGGERED);
                    if (display != null) {
                        display.setBlockState(level.getBlockState(pos));
                        display.setPos(pos.getX(), pos.getY(), pos.getZ());
                        display.setGlowDelay(search.delays[yzSquared + dx * dx]);
                        level.addFreshEntity(display);
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /** Small immutable geometry tables, shared by the standard locator items. */
    private static final class SearchVolume {
        final int extent;
        final int[] rowMaxX;
        final int[] delays;

        SearchVolume(int range) {
            int maxSquared = range * range / 3;
            extent = (int) Math.sqrt(maxSquared);
            int width = extent * 2 + 1;
            rowMaxX = new int[width * width];
            int row = 0;
            for (int dz = -extent; dz <= extent; dz++) {
                for (int dy = -extent; dy <= extent; dy++) {
                    int remaining = maxSquared - dy * dy - dz * dz;
                    rowMaxX[row++] = remaining < 0 ? -1 : (int) Math.sqrt(remaining);
                }
            }
            delays = new int[maxSquared + 1];
            for (int squared = 0; squared <= maxSquared; squared++) {
                delays[squared] = Mth.ceil(Math.sqrt(3.0 * squared));
            }
        }
    }
}
