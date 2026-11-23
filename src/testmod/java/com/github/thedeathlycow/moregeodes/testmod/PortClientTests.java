package com.github.thedeathlycow.moregeodes.testmod;

import com.github.thedeathlycow.moregeodes.item.*;
import com.github.thedeathlycow.moregeodes.item.tuning.*;
import com.github.thedeathlycow.moregeodes.client.ItemColorsSetup;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.*;
import java.nio.file.*;

public final class PortClientTests implements ClientModInitializer {
    private boolean connecting;
    private int ticks,connectedTicks;
    private volatile boolean saved;
    private void screenshot(Minecraft mc,String name) {
        Screenshot.grab(mc.gameDirectory,name,mc.gameRenderer.mainRenderTarget(),1,message->{System.out.println("GEODES_SCREENSHOT "+name+" "+message.getString());saved=true;});
    }
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(mc->{
            try {
                ticks++;
                if(!connecting && ticks>40 && mc.gui.overlay()==null && mc.gui.screen() instanceof TitleScreen) {
                    connecting=true;
                    ConnectScreen.startConnecting(mc.gui.screen(),mc,ServerAddress.parseString("127.0.0.1:25569"),new ServerData("More Geodes QA","127.0.0.1:25569",ServerData.Type.OTHER),false,null);
                }
                if(mc.level==null||mc.player==null) {
                    if(ticks>2400) throw new AssertionError("Client could not join QA server");
                    return;
                }
                connectedTicks++;
                if(connectedTicks==80) {
                    int count=0;
                    for(String name:ExpectedContent.ITEMS) {
                        ItemStack stack=new ItemStack(BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath("geodes",name)));
                        ItemStackRenderState state=new ItemStackRenderState();
                        mc.getItemModelResolver().updateForTopItem(state,stack,ItemDisplayContext.GUI,mc.level,mc.player,1);
                        if(state.isEmpty()) throw new AssertionError("Empty item model: "+name);
                        count++;
                    }
                    var stack=GeodesItemGroup.makeTunedLocatorStack("geodes:emerald");
                    Tuning tuning=TunedCrystalLocatorItem.getTuning(mc.level.registryAccess(),stack);
                    if(tuning==null) throw new AssertionError("Tuning registry did not synchronize");
                    int color=new ItemColorsSetup.TuningTint().calculate(stack,mc.level,mc.player);
                    if(color!=(0xff000000|tuning.getColor())) throw new AssertionError("Wrong client tuning tint");
                    Files.writeString(Path.of("client-test-results.txt"),"PASS: joined Fabric 26.3 dedicated server; resolved "+count+" item models; synchronized tuning registry; emerald tint="+Integer.toHexString(color)+"\n");
                    mc.gui.setScreen(null);System.out.println("GEODES_CLIENT_MODELS_PASS "+count);
                }
                if(connectedTicks==100) {saved=false;screenshot(mc,"geodes-content.png");}
                if(connectedTicks==110) mc.gameMode.useItem(mc.player,net.minecraft.world.InteractionHand.MAIN_HAND);
                if(connectedTicks==155) {saved=false;screenshot(mc,"geodes-locator-wave.png");}
                if(connectedTicks==200) {
                    var screen=new CreativeModeInventoryScreen(mc.player,mc.player.connection.enabledFeatures(),false);
                    mc.gui.setScreen(screen);
                }
                if(connectedTicks==205 && mc.gui.screen() instanceof CreativeModeInventoryScreen screen) {
                    var tabs=(net.fabricmc.fabric.api.client.creativetab.v1.FabricCreativeModeInventoryScreen)screen;
                    var tab=BuiltInRegistries.CREATIVE_MODE_TAB.getValue(Identifier.fromNamespaceAndPath("geodes","main"));
                    tabs.switchToPage(tabs.getPage(tab));
                    tabs.setSelectedTab(tab);
                    if(tabs.getSelectedTab()!=tab) throw new AssertionError("More Geodes creative tab is unavailable");
                    Files.writeString(Path.of("client-test-results.txt"),"PASS: More Geodes creative tab selected with "+tab.getDisplayItems().size()+" entries\n",StandardOpenOption.APPEND);
                }
                if(connectedTicks==225) {saved=false;screenshot(mc,"geodes-inventory.png");}
                if(connectedTicks>=260 && saved) {mc.player.connection.sendCommand("geodes_test_finish");mc.stop();}
            } catch(Throwable error) {
                error.printStackTrace();
                try {Files.writeString(Path.of("client-test-failure.txt"),error.toString());}catch(Exception ignored){}
                mc.stop();
            }
        });
    }
}
