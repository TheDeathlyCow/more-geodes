package com.github.thedeathlycow.moregeodes.item;

import com.github.thedeathlycow.moregeodes.entity.*;
import com.github.thedeathlycow.moregeodes.item.tuning.Tuning;
import com.github.thedeathlycow.moregeodes.sounds.GeodesSoundEvents;
import java.util.function.Consumer;
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
    private final int range,coolDown;
    public CrystalLocatorItem(Properties properties,int range,int cooldown) { super(properties);this.range=range;this.coolDown=cooldown; }
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
    protected void setCooldown(ItemCooldowns cooldowns,int ticks) {
        cooldowns.addCooldown(ModItems.CRYSTAL_LOCATOR.getDefaultInstance(),ticks);
        cooldowns.addCooldown(ModItems.TUNED_CRYSTAL_LOCATOR.getDefaultInstance(),ticks);
    }
    public int activate(ServerLevel level,BlockPos origin,ItemStack stack) {
        int count=0;
        // Preserve the original squared-distance predicate and propagation delay.
        // Restrict iteration to its bounding box, rather than visiting 97^3 candidates.
        int extent=(int)Math.floor(range/Math.sqrt(3));
        for(BlockPos pos:BlockPos.betweenClosed(origin.offset(-extent,-extent,-extent),origin.offset(extent,extent,extent))) {
            double distance=origin.distSqr(pos)*3;
            if(distance<=range*range && isPingableCrystal(stack,level,pos)) {
                EchoDisplay display=GeodesEntityTypes.ECHO_DISPLAY.create(level,EntitySpawnReason.TRIGGERED);
                if(display!=null) {
                    display.setBlockState(level.getBlockState(pos));
                    display.setPos(pos.getX(),pos.getY(),pos.getZ());
                    display.setGlowDelay(Mth.ceil(Math.sqrt(distance)));
                    level.addFreshEntity(display);count++;
                }
            }
        }
        return count;
    }
}
