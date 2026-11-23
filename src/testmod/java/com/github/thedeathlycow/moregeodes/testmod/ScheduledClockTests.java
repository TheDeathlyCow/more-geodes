package com.github.thedeathlycow.moregeodes.testmod;
import com.github.thedeathlycow.moregeodes.blocks.*;
import com.google.gson.*;
import java.nio.file.*;
import java.util.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.*;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.*;

/** Checks the actual game scheduler rather than calling oscillator ticks manually. */
public final class ScheduledClockTests implements ModInitializer {
    private ServerLevel level;
    private int ticks;
    private int joinedTicks;
    private final Block[] blocks={ModBlocks.SMALL_QUARTZ_BUD,ModBlocks.MEDIUM_QUARTZ_BUD,ModBlocks.LARGE_QUARTZ_BUD,ModBlocks.QUARTZ_CLUSTER};
    private final int[] expected={20,10,5,1};
    private final boolean[] previous=new boolean[4];
    private final List<List<Integer>> transitions=List.of(new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
    private BlockPos pos(int i) {return new BlockPos(i*5,250,8);}
    public void onInitialize() {
        ServerTickEvents.END_SERVER_TICK.register(server->{
            if(!Boolean.getBoolean("geodes.tests.keepOpen")) return;
            if(level==null) {
                if(server.getPlayerList().getPlayerCount()==0 || ++joinedTicks<20) return;
                // Block ticks run only in ticking chunks. Wait until a player has
                // activated these chunks before testing the vanilla scheduler.
                level=server.overworld();
                for(int i=0;i<4;i++) {level.setBlock(pos(i).below(),Blocks.STONE.defaultBlockState(),2);level.setBlock(pos(i),blocks[i].defaultBlockState(),3);level.setBlock(pos(i).east(),Blocks.REDSTONE_BLOCK.defaultBlockState(),3);}
            }
            if(level==null || ticks>=90) return;ticks++;
            for(int i=0;i<4;i++) {
                boolean powered=level.getBlockState(pos(i)).getValue(QuartzClusterBlock.POWERED);
                if(powered!=previous[i]) {transitions.get(i).add(ticks);previous[i]=powered;}
                if(ticks==60) level.setBlock(pos(i).east(),Blocks.AIR.defaultBlockState(),3);
            }
            if(ticks==90) {
                JsonObject report=new JsonObject();boolean ok=true;
                for(int i=0;i<4;i++) {
                    List<Integer> changes=transitions.get(i);boolean pass=changes.size()>=2;
                    for(int n=1;n<changes.size();n++) if(changes.get(n)<=60) pass &= changes.get(n)-changes.get(n-1)==expected[i];
                    pass &= !level.getBlockState(pos(i)).getValue(QuartzClusterBlock.POWERED);ok &= pass;
                    JsonObject row=new JsonObject();row.addProperty("passed",pass);row.addProperty("half_period_ticks",expected[i]);row.add("transition_ticks",new Gson().toJsonTree(changes));report.add(blocks[i].getDescriptionId(),row);
                }
                report.addProperty("passed",ok);
                try {Files.writeString(Path.of("scheduled-clock-results.json"),new GsonBuilder().setPrettyPrinting().create().toJson(report));}catch(Exception e){throw new RuntimeException(e);}
                System.out.println("GEODES_SCHEDULED_CLOCKS "+(ok?"PASS":"FAIL"));
            }
        });
    }
}
