package com.github.thedeathlycow.moregeodes.testmod;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.blocks.*;
import com.github.thedeathlycow.moregeodes.entity.*;
import com.github.thedeathlycow.moregeodes.item.*;
import com.github.thedeathlycow.moregeodes.item.tuning.*;
import com.google.gson.*;
import com.mojang.serialization.Dynamic;
import java.nio.file.*;
import java.util.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.SharedConstants;
import net.minecraft.core.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.*;
import net.minecraft.nbt.*;
import net.minecraft.resources.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.datafix.DataFixers;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.piglin.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Compostable;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.chunk.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.AABB;

/** Isolated integration tests, executed inside a real 26.3 dedicated server. */
public final class PortRuntimeTests implements ModInitializer {
    private final JsonArray results=new JsonArray();
    private final JsonObject metrics=new JsonObject();
    private int failures;
    private MinecraftServer server;
    private ServerLevel level;
    private final BlockPos bench=new BlockPos(8,220,8);
    @FunctionalInterface private interface Checked {void run() throws Exception;}
    private static Identifier id(String path) {return Identifier.fromNamespaceAndPath("geodes",path);}
    private static void require(boolean ok,String message) {if(!ok) throw new AssertionError(message);}
    private void check(String name,Checked action) {
        long start=System.nanoTime();JsonObject result=new JsonObject();result.addProperty("test",name);
        try {action.run();result.addProperty("passed",true);System.out.println("GEODES_TEST_PASS "+name);}
        catch(Throwable error) {failures++;result.addProperty("passed",false);result.addProperty("error",error.toString());error.printStackTrace();System.out.println("GEODES_TEST_FAIL "+name+": "+error);}
        result.addProperty("milliseconds",(System.nanoTime()-start)/1_000_000);results.add(result);
    }
    @Override public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTED.register(s->{server=s;level=s.overworld();run();});
    }
    private void run() {
        System.out.println("GEODES_RUNTIME_STARTED");
        check("original block and item registry inventory",this::inventory);
        check("every original loot table, recipe and advancement loads",this::data);
        check("biome injection and generation configuration",this::biomes);
        check("all block states survive codec round trips",this::states);
        check("saved blocks and inventories survive server restart",this::persistence);
        check("all seven core tuning recipes craft and synchronize",this::recipes);
        check("1.20.1 locator item upgrades through vanilla DataFixer",this::legacyLocator);
        check("crystals grow through all original stages",this::growth);
        check("gypsum grows upward and places two block halves",this::gypsum);
        check("gypsum vegetation patch generates its ground and crystals",this::gypsumPatch);
        check("all quartz oscillator stages toggle and stop",this::quartz);
        check("Silk Touch, Fortune, slabs and gypsum drops",this::drops);
        check("piglins remember and reject fools gold",this::piglins);
        check("locator predicate, wave delay and entity lifetime",this::locator);
        check("gypsum composting preserves one guaranteed layer",this::compost);
        check("optional Carpet piston behavior",this::carpet);
        if(MoreGeodes.CONFIG.generateDiamondGeodes()) {
            check("vanilla geode algorithm places every core variant",this::features);
            check("new Overworld and Nether chunks naturally contain geodes",this::natural);
        }
        JsonObject report=new JsonObject();report.addProperty("minecraft",SharedConstants.getCurrentVersion().id());report.addProperty("failures",failures);
        report.addProperty("carpet",FabricLoader.getInstance().isModLoaded("carpet"));report.add("tests",results);report.add("metrics",metrics);
        try {Files.writeString(Path.of("test-results.json"),new GsonBuilder().setPrettyPrinting().create().toJson(report));}catch(Exception e){throw new RuntimeException(e);}
        System.out.println("GEODES_TEST_COMPLETE failures="+failures);
        if(!Boolean.getBoolean("geodes.tests.keepOpen")) server.halt(false);
    }
    private void persistence() throws Exception {
        Path marker=Path.of("persistence-written.txt");boolean existing=Files.exists(marker);
        for(int barrel=0;barrel<3;barrel++) {
            BlockPos pos=new BlockPos(barrel*4,180,128);
            if(!existing) level.setBlock(pos,Blocks.BARREL.defaultBlockState(),3);
            require(level.getBlockState(pos).is(Blocks.BARREL),"Saved container lost");
            var container=(net.minecraft.world.Container)level.getBlockEntity(pos);
            for(int slot=0;slot<27;slot++) {
                int index=barrel*27+slot;if(index>=63) break;
                ItemStack expected=index<56?new ItemStack(BuiltInRegistries.ITEM.getValue(id(ExpectedContent.ITEMS[index]))):GeodesItemGroup.makeTunedLocatorStack("geodes:"+List.of("amethyst","emerald","quartz","diamond","echo","lapis","gypsum").get(index-56));
                if(!existing) container.setItem(slot,expected);
                else require(ItemStack.isSameItemSameComponents(container.getItem(slot),expected),"Saved inventory entry changed: "+index);
            }
            container.setChanged();
        }
        BlockPos p=new BlockPos(16,180,128);
        BlockState state=ModBlocks.EMERALD_CLUSTER.defaultBlockState().setValue(CrystalClusterBlock.WATERLOGGED,true);
        if(!existing) {level.setBlock(p.below(),Blocks.STONE.defaultBlockState(),2);level.setBlock(p,state,2);level.setBlock(p.east(2),ModBlocks.BUDDING_ECHO_BLOCK.defaultBlockState(),2);}
        require(level.getBlockState(p)==state,"Saved facing or waterlogging changed");
        require(level.getBlockState(p.east(2)).is(ModBlocks.BUDDING_ECHO_BLOCK),"Saved budding block lost");
        Files.writeString(marker,"63 inventory entries, waterlogged cluster, budding echo block");
        metrics.addProperty("save_fixture_phase",existing?"verified after restart":"created for next restart");
    }
    private void inventory() {
        for(String name:ExpectedContent.BLOCKS) require(BuiltInRegistries.BLOCK.getValue(id(name))!=Blocks.AIR,"Missing block "+name);
        for(String name:ExpectedContent.ITEMS) require(BuiltInRegistries.ITEM.getValue(id(name))!=Items.AIR,"Missing item "+name);
        long blocks=BuiltInRegistries.BLOCK.keySet().stream().filter(x->x.getNamespace().equals("geodes")).count();
        long items=BuiltInRegistries.ITEM.keySet().stream().filter(x->x.getNamespace().equals("geodes")).count();
        require(blocks==50 && items==56,"Inventory changed: "+blocks+" blocks, "+items+" items");
        metrics.addProperty("registered_blocks",blocks);metrics.addProperty("registered_items",items);
    }
    private void data() {
        var lookup=server.reloadableRegistries().lookup();
        for(String name:ExpectedContent.LOOT) lookup.lookupOrThrow(Registries.LOOT_TABLE).getOrThrow(ResourceKey.create(Registries.LOOT_TABLE,id(name)));
        for(String name:ExpectedContent.RECIPES) {
            if(name.startsWith("crushing/")||name.startsWith("tuning/spectrum/")) continue;
            require(server.getRecipeManager().byKey(ResourceKey.create(Registries.RECIPE,id(name))).isPresent(),"Missing recipe "+name);
        }
        for(String name:ExpectedContent.ADVANCEMENTS) {
            if(name.contains("/spectrum/")) continue;
            lookup.lookupOrThrow(Registries.ADVANCEMENT).getOrThrow(ResourceKey.create(Registries.ADVANCEMENT,id(name)));
        }
        require(level.registryAccess().lookupOrThrow(Registries.FEATURE).get(ResourceKey.create(Registries.FEATURE,id("certus_geode"))).isEmpty(),"Absent AE2 feature loaded");
        require(level.registryAccess().lookupOrThrow(Registries.FEATURE).get(ResourceKey.create(Registries.FEATURE,id("bismuth_geode"))).isEmpty(),"Absent Spectrum feature loaded");
    }
    private void biomes() {
        var registry=level.registryAccess().lookupOrThrow(Registries.BIOME);
        boolean enabled=MoreGeodes.CONFIG.generateEmeraldGeodes();
        for(var holder:registry.listElements().toList()) {
            var features=holder.value().getGenerationSettings().features().stream().flatMap(HolderSet::stream).map(h->h.unwrapKey().orElseThrow().identifier()).filter(i->i.getNamespace().equals("geodes")).toList();
            if(!enabled) require(features.isEmpty(),"Disabled generation injected into "+holder.key());
        }
        if(enabled) {
            for(String biome:List.of("desert","deep_dark","plains","jagged_peaks")) {
                var b=registry.getOrThrow(ResourceKey.create(Registries.BIOME,Identifier.withDefaultNamespace(biome))).value();
                require(b.getGenerationSettings().features().stream().flatMap(HolderSet::stream).anyMatch(h->h.unwrapKey().orElseThrow().identifier().getNamespace().equals("geodes")),"No geodes in "+biome);
            }
        }
        metrics.addProperty("generation_enabled",enabled);
    }
    private void states() {
        var ops=RegistryOps.create(NbtOps.INSTANCE,level.registryAccess());int count=0;
        for(String name:ExpectedContent.BLOCKS) for(BlockState state:BuiltInRegistries.BLOCK.getValue(id(name)).getStateDefinition().getPossibleStates()) {
            Tag encoded=BlockState.CODEC.encodeStart(ops,state).getOrThrow();
            require(BlockState.CODEC.parse(ops,encoded).getOrThrow()==state,"Changed state "+state);count++;
        }
        metrics.addProperty("block_states_round_tripped",count);
    }
    private void recipes() {
        Item[] ingredients={Items.AMETHYST_SHARD,Items.EMERALD,Items.QUARTZ,Items.DIAMOND,Items.ECHO_SHARD,Items.LAPIS_LAZULI,ModItems.GYPSUM_SHARD};
        String[] names={"amethyst","emerald","quartz","diamond","echo","lapis","gypsum"};
        var ops=RegistryOps.create(NbtOps.INSTANCE,server.registryAccess());
        for(int i=0;i<names.length;i++) {
            var recipe=server.getRecipeManager().byKey(ResourceKey.create(Registries.RECIPE,id("tuning/"+names[i]+"_tuning"))).orElseThrow().value();
            CraftingInput input=CraftingInput.of(2,1,List.of(new ItemStack(ModItems.CRYSTAL_LOCATOR),new ItemStack(ingredients[i])));
            require(((CraftingRecipe)recipe).matches(input,level),"Tuning recipe does not match "+names[i]);
            ItemStack output=((CraftingRecipe)recipe).assemble(input);
            require(output.is(ModItems.TUNED_CRYSTAL_LOCATOR)&&id(names[i]).equals(TunedCrystalLocatorItem.tuningId(output)),"Wrong tuned output "+names[i]);
            ItemStack copy=ItemStack.CODEC.parse(ops,ItemStack.CODEC.encodeStart(ops,output).getOrThrow()).getOrThrow();
            require(id(names[i]).equals(TunedCrystalLocatorItem.tuningId(copy)),"Tuning not persistent");
            var buf=new net.minecraft.network.RegistryFriendlyByteBuf(io.netty.buffer.Unpooled.buffer(),server.registryAccess());
            try {
                var tuningRecipe=(com.github.thedeathlycow.moregeodes.item.recipe.TuningRecipe)recipe;
                com.github.thedeathlycow.moregeodes.item.recipe.TuningRecipe.STREAM_CODEC.encode(buf,tuningRecipe);
                var decoded=com.github.thedeathlycow.moregeodes.item.recipe.TuningRecipe.STREAM_CODEC.decode(buf);
                require(id(names[i]).equals(TunedCrystalLocatorItem.tuningId(decoded.assemble(input))),"Network tuning changed");
            } finally {buf.release();}
        }
    }
    private void legacyLocator() {
        CompoundTag old=new CompoundTag();old.putString("id","geodes:tuned_crystal_locator");old.putByte("Count",(byte)1);
        CompoundTag tag=new CompoundTag(),tuning=new CompoundTag();tuning.putString("id","geodes:emerald");tag.put("tuning",tuning);tag.putInt("Damage",2);old.put("tag",tag);
        var fixed=DataFixers.getDataFixer().update(References.ITEM_STACK,new Dynamic<>(NbtOps.INSTANCE,old),3465,SharedConstants.getCurrentVersion().dataVersion().version());
        ItemStack stack=ItemStack.CODEC.parse(RegistryOps.create(NbtOps.INSTANCE,server.registryAccess()),fixed.getValue()).getOrThrow();
        require(id("emerald").equals(TunedCrystalLocatorItem.tuningId(stack)),"Legacy tuning lost: "+fixed);
        require(stack.getDamageValue()==2,"Legacy damage lost");
        require(TunedCrystalLocatorItem.getTuning(server.registryAccess(),stack)!=null,"Legacy tuning cannot resolve");
    }
    private void clear(BlockPos p,int radius) {
        for(BlockPos b:BlockPos.betweenClosed(p.offset(-radius,-radius,-radius),p.offset(radius,radius,radius))) level.setBlock(b,Blocks.AIR.defaultBlockState(),2);
    }
    private void growth() {
        int offset=0;
        for(GeodeBuddingBlock block:List.of(ModBlocks.BUDDING_EMERALD,ModBlocks.BUDDING_QUARTZ,ModBlocks.BUDDING_ECHO_BLOCK,ModBlocks.BUDDING_LAPIS)) {
            BlockPos p=bench.offset(offset,0,0);offset+=8;clear(p,2);level.setBlock(p,block.defaultBlockState(),2);
            for(Direction direction:Direction.values()) level.setBlock(p.relative(direction),Fluids.WATER.defaultFluidState().createLegacyBlock(),2);
            RandomSource random=RandomSource.create(33);
            for(int i=0;i<1500;i++) block.randomTick(block.defaultBlockState(),level,p,random);
            for(Direction direction:Direction.values()) {
                BlockState state=level.getBlockState(p.relative(direction));
                require(state.is(block.getClusters().getLast()),"Growth stopped: "+state);
                require(state.getValue(CrystalClusterBlock.FACING)==direction,"Growth facing changed");
                require(state.getValue(CrystalClusterBlock.WATERLOGGED),"Growth lost waterlogging");
            }
        }
    }
    private void gypsum() {
        BlockPos p=bench.offset(40,0,0);clear(p,3);level.setBlock(p,ModBlocks.BUDDING_GYPSUM.defaultBlockState(),2);
        for(int i=0;i<200;i++) ((GeodeBuddingBlock)ModBlocks.BUDDING_GYPSUM).randomTick(ModBlocks.BUDDING_GYPSUM.defaultBlockState(),level,p,RandomSource.create(i));
        require(level.getBlockState(p.above()).is(ModBlocks.GYPSUM_ROSE),"Gypsum rose did not grow");
        require(level.getBlockState(p.above(2)).getValue(LargeCrystalClusterBlock.HALF)==DoubleBlockHalf.UPPER,"Missing upper half");
        require(level.getBlockState(p.east()).isAir(),"Gypsum grew sideways");
        BlockPos at=p.east(4);level.setBlock(at.below(),Blocks.STONE.defaultBlockState(),2);level.setBlock(at,Blocks.AIR.defaultBlockState(),2);level.setBlock(at.above(),Blocks.AIR.defaultBlockState(),2);
        var feature=new SimpleBlockFeature(BlockStateProvider.of(ModBlocks.GYPSUM_ROSE));
        require(feature.place(level,level.getChunkSource().getGenerator(),RandomSource.create(1),at),"Simple block feature failed");
        require(level.getBlockState(at.above()).is(ModBlocks.GYPSUM_ROSE),"Worldgen did not place upper half");
        level.setBlock(at.above(),Blocks.AIR.defaultBlockState(),3);
        require(level.getBlockState(at).isAir(),"Breaking upper half left lower half");
        level.setBlock(at.above(),Blocks.STONE.defaultBlockState(),2);
        require(!feature.place(level,level.getChunkSource().getGenerator(),RandomSource.create(1),at),"Blocked tall feature reported success");
        require(level.getBlockState(at).isAir(),"Blocked tall feature placed an orphan base");
    }
    private void quartz() {
        BlockPos p=bench.offset(0,0,12);clear(p,2);level.setBlock(p.below(),Blocks.STONE.defaultBlockState(),2);
        for(Block block:List.of(ModBlocks.SMALL_QUARTZ_BUD,ModBlocks.MEDIUM_QUARTZ_BUD,ModBlocks.LARGE_QUARTZ_BUD,ModBlocks.QUARTZ_CLUSTER)) {
            var quartz=(QuartzClusterBlock)block;level.setBlock(p,block.defaultBlockState(),2);level.setBlock(p.east(),Blocks.REDSTONE_BLOCK.defaultBlockState(),3);
            quartz.tick(level.getBlockState(p),level,p,RandomSource.create(1));require(level.getBlockState(p).getValue(QuartzClusterBlock.POWERED),"Oscillator did not power");
            require(level.getBlockState(p).getSignal(level,p,Direction.NORTH)==15,"Oscillator signal changed");
            quartz.tick(level.getBlockState(p),level,p,RandomSource.create(1));require(!level.getBlockState(p).getValue(QuartzClusterBlock.POWERED),"Oscillator did not alternate");
            level.setBlock(p.east(),Blocks.AIR.defaultBlockState(),3);quartz.tick(level.getBlockState(p),level,p,RandomSource.create(1));require(!level.getBlockState(p).getValue(QuartzClusterBlock.POWERED),"Oscillator did not stop");
        }
    }
    private void gypsumPatch() {
        BlockPos center=new BlockPos(1024,165,512);
        for(BlockPos p:BlockPos.betweenClosed(center.offset(-12,-4,-12),center.offset(12,5,12)))
            level.setBlock(p,p.getY()<center.getY()?Blocks.SAND.defaultBlockState():Blocks.AIR.defaultBlockState(),2);
        Feature feature=level.registryAccess().lookupOrThrow(Registries.FEATURE).getOrThrow(ResourceKey.create(Registries.FEATURE,id("gypsum_patch"))).value();
        for(int i=0;i<40;i++) feature.place(level,level.getChunkSource().getGenerator(),RandomSource.create(i),center.offset(i%9-4,0,i/9-2));
        int ground=0,crystals=0;
        for(BlockPos p:BlockPos.betweenClosed(center.offset(-12,-4,-12),center.offset(12,5,12))) {
            BlockState state=level.getBlockState(p);
            if(state.is(ModBlocks.GYPSUM_CRYSTAL_BLOCK)||state.is(ModBlocks.BUDDING_GYPSUM)) ground++;
            if(state.is(ModBlocks.GYPSUM_ROSE)||state.is(ModBlocks.SMALL_GYPSUM_BUD)||state.is(ModBlocks.MEDIUM_GYPSUM_BUD)||state.is(ModBlocks.LARGE_GYPSUM_BUD)) crystals++;
        }
        require(ground>0 && crystals>0,"Gypsum patch did not place ground and vegetation");
        metrics.addProperty("gypsum_patch_fixture_ground",ground);metrics.addProperty("gypsum_patch_fixture_crystals",crystals);
    }
    private int dropCount(BlockState state,BlockPos p,ItemStack tool,Item item) {return Block.getDrops(state,level,p,null,null,tool).stream().filter(s->s.is(item)).mapToInt(ItemStack::getCount).sum();}
    private void drops() {
        BlockPos p=bench.offset(0,0,20);level.setBlock(p.below(),Blocks.STONE.defaultBlockState(),2);
        ItemStack pick=new ItemStack(Items.DIAMOND_PICKAXE),silk=pick.copy(),fortune=pick.copy();
        var enchantments=level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        silk.enchant(enchantments.getOrThrow(Enchantments.SILK_TOUCH),1);fortune.enchant(enchantments.getOrThrow(Enchantments.FORTUNE),3);
        var state=ModBlocks.EMERALD_CLUSTER.defaultBlockState();level.setBlock(p,state,2);
        require(dropCount(state,p,pick,Items.EMERALD)==4,"Emerald base drop changed");
        require(dropCount(state,p,silk,ModItems.EMERALD_CLUSTER)==1,"Silk Touch failed");
        int total=0;for(int i=0;i<100;i++) total+=dropCount(state,p,fortune,Items.EMERALD);require(total>400,"Fortune ineffective");
        var slab=ModBlocks.CALCITE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE,SlabType.DOUBLE);level.setBlock(p,slab,2);
        require(dropCount(slab,p,pick,ModItems.CALCITE_SLAB)==2,"Double slab drop changed");
        LargeCrystalClusterBlock.placeAt(level,ModBlocks.GYPSUM_ROSE.defaultBlockState(),p,2);
        require(dropCount(level.getBlockState(p),p,pick,ModItems.GYPSUM_SHARD)==4,"Gypsum base drops failed");
        require(dropCount(level.getBlockState(p.above()),p.above(),pick,ModItems.GYPSUM_SHARD)==4,"Intact upper-half harvest changed");
        BlockState upper=level.getBlockState(p.above());level.setBlock(p,Blocks.AIR.defaultBlockState(),2);
        require(dropCount(upper,p.above(),pick,ModItems.GYPSUM_SHARD)==0,"Orphan half duplicates loot");
    }
    private void piglins() throws Exception {
        ServerLevel nether=server.getLevel(Level.NETHER);Piglin piglin=EntityTypes.PIGLIN.create(nether,EntitySpawnReason.TRIGGERED);
        require(piglin!=null,"Piglin creation failed");
        piglin.setItemInHand(InteractionHand.OFF_HAND,new ItemStack(ModItems.PYRITE_CHUNK));
        var consume=PiglinAi.class.getDeclaredMethod("stopHoldingOffHandItem",ServerLevel.class,Piglin.class,boolean.class);consume.setAccessible(true);consume.invoke(null,nether,piglin,true);
        require(piglin.getBrain().getMemory(GeodesMemoryModules.REMEMBERS_FOOLS_GOLD).orElse(false),"Piglin memory was not registered or set");
        require(piglin.getOffhandItem().isEmpty(),"Pyrite was not consumed");
        var output=net.minecraft.world.level.storage.TagValueOutput.createWithContext(net.minecraft.util.ProblemReporter.DISCARDING,server.registryAccess());
        piglin.saveWithoutId(output);
        Piglin reloaded=EntityTypes.PIGLIN.create(nether,EntitySpawnReason.LOAD);
        reloaded.load(net.minecraft.world.level.storage.TagValueInput.create(net.minecraft.util.ProblemReporter.DISCARDING,server.registryAccess(),output.buildResult()));
        require(reloaded.getBrain().getMemory(GeodesMemoryModules.REMEMBERS_FOOLS_GOLD).orElse(false),"Saved piglin forgot fools gold");
        var wants=PiglinAi.class.getDeclaredMethod("wantsToPickup",Piglin.class,ItemStack.class);wants.setAccessible(true);
        require(!(Boolean)wants.invoke(null,piglin,new ItemStack(ModItems.PYRITE_CHUNK)),"Piglin accepted fools gold twice");
    }
    private void locator() {
        BlockPos p=bench.offset(0,0,40);clear(p,8);level.setBlock(p.offset(3,0,0),ModBlocks.EMERALD_CLUSTER.defaultBlockState(),2);
        level.setBlock(p.offset(4,0,0),ModBlocks.QUARTZ_CLUSTER.defaultBlockState(),2);
        ItemStack locator=GeodesItemGroup.makeTunedLocatorStack("geodes:emerald");
        long start=System.nanoTime();int count=((CrystalLocatorItem)ModItems.TUNED_CRYSTAL_LOCATOR).activate(level,p,locator);
        metrics.addProperty("locator_activation_ms",(System.nanoTime()-start)/1_000_000.0);
        require(count==1,"Locator matched wrong blocks: "+count);
        var displays=level.getEntitiesOfClass(EchoDisplay.class,new AABB(p).inflate(9));require(displays.size()==1,"Wrong display count");
        EchoDisplay display=displays.getFirst();require(display.isInvisible(),"Display visible before wave");
        for(int i=0;i<6;i++) display.tick();require(!display.isInvisible()&&display.isCurrentlyGlowing(),"Wave did not reveal outline");
        for(int i=0;i<30;i++) display.tick();require(display.isRemoved(),"Display leaked after its lifetime");
    }
    private void compost() {
        Compostable compost=ModItems.GYPSUM_SHARD.getDefaultInstance().get(DataComponents.COMPOSTABLE);
        require(compost!=null&&compost.layers() instanceof net.minecraft.world.level.storage.loot.providers.number.ints.ResolvableInt.Constant value&&value.value()==1,"Composting changed");
    }
    private void carpet() throws Exception {
        if(!FabricLoader.getInstance().isModLoaded("carpet")) {require(ModBlocks.BUDDING_EMERALD.defaultBlockState().getPistonPushReaction()==net.minecraft.world.level.material.PushReaction.POPPED,"Budding block behavior changed");return;}
        var field=Class.forName("carpet.CarpetSettings").getField("movableAmethyst");boolean old=field.getBoolean(null);
        try {
            field.setBoolean(null,true);
            require(ModBlocks.BUDDING_EMERALD.defaultBlockState().getPistonPushReaction()==net.minecraft.world.level.material.PushReaction.PUSH_PULL,"Carpet rule ineffective");
            BlockPos p=new BlockPos(80,220,80);level.setBlock(p,ModBlocks.BUDDING_EMERALD.defaultBlockState(),2);
            var fake=new net.minecraft.server.level.ServerPlayer(server,level,new com.mojang.authlib.GameProfile(UUID.randomUUID(),"GeodesTest"),net.minecraft.server.level.ClientInformation.createDefault()) {
                @Override public void awardStat(net.minecraft.stats.Stat<?> stat,int amount) {}
            };
            ItemStack pick=new ItemStack(Items.DIAMOND_PICKAXE);
            pick.enchant(level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH),1);
            int before=level.getEntitiesOfClass(net.minecraft.world.entity.item.ItemEntity.class,new AABB(p).inflate(2)).size();
            ModBlocks.BUDDING_EMERALD.playerDestroy(level,fake,p,ModBlocks.BUDDING_EMERALD.defaultBlockState(),null,pick);
            int after=level.getEntitiesOfClass(net.minecraft.world.entity.item.ItemEntity.class,new AABB(p).inflate(2)).size();
            require(after==before+1,"Carpet Silk Touch budding drop failed");
            field.setBoolean(null,false);
            require(ModBlocks.BUDDING_EMERALD.defaultBlockState().getPistonPushReaction()==net.minecraft.world.level.material.PushReaction.POPPED,"Carpet off behavior changed");
        }finally{field.setBoolean(null,old);}
    }
    private void features() {
        String[] names={"emerald","quartz","diamond","echo","lapis"};Block[] blocks={ModBlocks.EMERALD_CRYSTAL_BLOCK,ModBlocks.QUARTZ_CRYSTAL_BLOCK,ModBlocks.DIAMOND_CRYSTAL_BLOCK,ModBlocks.ECHO_BLOCK,ModBlocks.LAPIS_CRYSTAL_BLOCK};
        for(int i=0;i<names.length;i++) {
            ServerLevel target=i==1?server.getLevel(Level.NETHER):level;BlockPos p=new BlockPos(512+i*64,i==2?-30:90,512);
            BlockState filler=(i==1?Blocks.NETHERRACK:i==2?Blocks.DEEPSLATE:Blocks.STONE).defaultBlockState();
            for(BlockPos at:BlockPos.betweenClosed(p.offset(-20,-20,-20),p.offset(20,20,20))) target.setBlock(at,filler,2);
            Feature feature=target.registryAccess().lookupOrThrow(Registries.FEATURE).getOrThrow(ResourceKey.create(Registries.FEATURE,id(names[i]+"_geode"))).value();
            require(feature.place(target,target.getChunkSource().getGenerator(),RandomSource.create(1234),p),"Geode placement failed "+names[i]);
            int crystals=0;for(BlockPos at:BlockPos.betweenClosed(p.offset(-20,-20,-20),p.offset(20,20,20))) if(target.getBlockState(at).is(blocks[i])) crystals++;
            require(crystals>0,"No crystal shell for "+names[i]);metrics.addProperty(names[i]+"_fixture_crystal_blocks",crystals);
        }
    }
    private void natural() {
        int total=0;
        for(ServerLevel target:List.of(level,server.getLevel(Level.NETHER))) {
            long start=System.nanoTime();Map<String,Integer> counts=new TreeMap<>();
            int width=target==level?32:16;
            for(int x=200;x<200+width;x++) for(int z=200;z<216;z++) {
                LevelChunk chunk=target.getChunk(x,z);
                for(LevelChunkSection section:chunk.getSections()) section.getStates().count((state,count)->{
                    Identifier key=BuiltInRegistries.BLOCK.getKey(state.getBlock());if(key.getNamespace().equals("geodes")) counts.merge(key.toString(),count,Integer::sum);
                });
            }
            int found=counts.values().stream().mapToInt(Integer::intValue).sum();total+=found;require(found>0,"No natural geodes in "+target.dimension());
            metrics.add(target.dimension().identifier().getPath()+"_natural_blocks",new Gson().toJsonTree(counts));
            metrics.addProperty(target.dimension().identifier().getPath()+"_sample_chunks",width*16);
            metrics.addProperty(target.dimension().identifier().getPath()+"_sample_chunks_ms",(System.nanoTime()-start)/1_000_000);
        }
        require(total>0,"No mod content in 768 naturally generated chunks");
    }
}

