package com.github.thedeathlycow.moregeodes.testmod;

import com.github.thedeathlycow.moregeodes.blocks.*;
import com.github.thedeathlycow.moregeodes.item.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.commands.Commands;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.GameType;

/** Only loaded from the separate test mod; never included in the installable mod. */
public final class ClientFixture implements ModInitializer {
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher,access,environment)->dispatcher.register(
            Commands.literal("geodes_test_finish").executes(context->{context.getSource().getServer().halt(false);return 1;})));
        ServerPlayConnectionEvents.JOIN.register((handler,sender,server)->{
            if(!Boolean.getBoolean("geodes.tests.keepOpen")) return;
            var level=server.overworld();
            for(BlockPos pos:BlockPos.betweenClosed(new BlockPos(-13,200,-13),new BlockPos(13,212,13)))
                level.setBlock(pos,pos.getY()==200?Blocks.SMOOTH_STONE.defaultBlockState():Blocks.AIR.defaultBlockState(),2);
            for(int i=0;i<ExpectedContent.BLOCKS.length;i++) {
                Block block=BuiltInRegistries.BLOCK.getValue(Identifier.fromNamespaceAndPath("geodes",ExpectedContent.BLOCKS[i]));
                BlockPos pos=new BlockPos(-9+(i%10)*2,201,-9+(i/10)*2);
                BlockState state=block.defaultBlockState();
                if(block instanceof LargeCrystalClusterBlock) LargeCrystalClusterBlock.placeAt(level,state,pos,2);else level.setBlock(pos,state,2);
            }
            var player=handler.player;
            player.setGameMode(GameType.CREATIVE);
            player.teleportTo(level,0,205,10,java.util.Set.of(),180f,25f,false);
            player.getAbilities().flying=true;player.onUpdateAbilities();
            player.setItemSlot(net.minecraft.world.entity.EquipmentSlot.MAINHAND,GeodesItemGroup.makeTunedLocatorStack("geodes:emerald"));
            System.out.println("GEODES_CLIENT_FIXTURE_READY "+player.getName().getString());
        });
    }
}
