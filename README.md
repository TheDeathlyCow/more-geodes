# More Geodes

Curse Forge mod page: https://www.curseforge.com/minecraft/mc-mods/emerald-geodes

Modrinth mod page: https://modrinth.com/mod/more-geodes

The CurseForge and Modrinth mod pages as well as this repository are the only official download pages for this mod, do not trust any version of this mod from elsewhere! 

# New Geodes

## Emerald Geodes
Emerald geodes behave very similarly to amethyst geodes; they spawn as sphere-like objects underground in Mountain-type biomes encased in calcite and smooth basalt casings underground and can grow emerald crystal buds. When these buds are harvested, they of course drop emeralds. Provides an automate-able alternative to emerald farming than just trading endlessly. 

## Quartz Geodes
Quartz Geodes generate in The Nether and have many similarities to amethyst geodes and emerald geodes. Quartz buds and clusters grow on budding quartz, but budding quartz cannot be harvested. Quartz clusters will drop quartz when broken, but will drop an item form of itself when broken with silk touch, etc. However, quartz geodes spawn exclusively in The Nether, and with a middle layer of tuff instead of calcite. 

### Quartz Oscillators
Quartz buds and clusters behave as an oscillator when powered by an external redstone source. That is, when a bud or cluster receives power, it will periodically turn on and off itself, emitting redstone power on an infinitely-repeating basis. The frequency at which it pulses is determined by its size. 

Buds pulse with the following frequencies:

| Block             | Frequency (Hz) |
| ----------------- | -------------- |
| Small Quartz Bud  | 0.5 Hz         |
| Medium Quartz Bud | 1 Hz           |
| Large Quartz Bud  | 2 Hz           |
| Quartz Cluster    | 10 Hz          |

## Diamond Geodes

Diamond geodes generate in all biomes of the Overworld filled with water. Diamond geodes have an outer layer of smooth basalt and a middle layer of 75% deepslate coal ore and 25% smooth basalt. They also only have a single cluster block do not include a budding block to grow new diamonds. It is best to thing of these geodes as 'goody boxes' of diamonds and coal to be a small boost to your deep mining.

## Echo Geodes

Echo geodes generate exclusively in the Deep Dark. The budding echo block provides a renewable source of Echo Clusters, which can be harvested to obtain Echo Shards. This mod also adds a new use for echo geodes in crafting the Echo Locator.

## Lapis Lazuli Geodes

Lapis Lazuli Geodes are a blue geode that generate in deserts and have a middle layer of [Pyrite](#Pyrite). While you can harvest Medium and Large Lapis Lazuli Buds and Lapis Lazuli Clusters to get Lapis Lazuli, you can also harvest Small Lapis Lazuli Buds to get Pyrite Chunks, which can be used to craft Pyrite

## Gypsum Patches

Gypsum Patches are a new type of feature that grow Gypum Roses and can be found in Deserts and Badlands. While not technically a Geode, they are a quite similar idea with crystal blocks, budding blocks, and growable crystals. There are still a number of notable differences though. Firstly, Gypsum Patches generate on the surface rather than underground. The budding blocks also only grow crystals upwards, rather than to any side (though the crystals can still be placed in any direction). Finally, Gypsum Roses can grow to be 2 blocks tall. 

When harvested Gypsum Roses drop Gypsum Shards, which can be composted or used to craft sand. More features are planned.

# Echo Locators

The Echo Locator is an item that can be used to help find geodes. These are sort of like tuning forks that, when used, will cause any nearby crystal clusters or buds to resonate for 30 seconds. The nearest crystal will also emit a sculk vibration particle back to the Echo Locator to provide a visual guide for anyone who cannot hear well or use directional audio. They are crafted using 4 echo shards and 2 bones. 

# Pyrite

Pyrite, commonly known as Fool's Gold, is the middle layer of Lapis Lazuli Geodes. It is a decorative block with a stair, slab, and wall variants. It also has some similar properties to gold, such as being able to be bartered with Piglins. Though once they figure out your ruse they will be quite angry indeed, and after seeing Pyrite once they will not forget it (though its shine will still be mildly distracting to them).

# Calcite Blocks

This mod also adds a stair, slab, and wall variant of Calcite. They use the vanilla Calcite texture, so resource packs that retexture calcite should work automatically.

# Datapack Integration

This mod uses the namespace `geodes`. If you wish to make changes to the features in this mod using a datapack, all files must be put under that namespace. The emerald and quartz geodes use very similar values to the amethyst geode, and it can be used as an example for how to configure them. Slicedlime provides an example worldgen datapack that can be used as a reference [here](https://github.com/slicedlime/examples/blob/master/vanilla_worldgen.zip), or alternatively you can use Misode's datapack generators to generate these for you, which can be found [here](https://misode.github.io/). As of 1.19.3, the amethyst geode definition is included in the jar of the game (unzip the jar file with your favourite zip-file unarchiver, jars are literally just zips with a different name).

## Datapack Addons

In the [releases tab](https://github.com/TheDeathlyCow/more-geodes/releases/) you can find downloads for two datapack addons for this mod. They're each pretty self explanatory, and act as ideas for how one might decide to try and make a datapack configuration for this mod. 

## Configured features
You can make changes to the geode configured features by replacing their files in the directories `geodes/worldgen/configured_feature/emerald_geode.json`, `geodes/worldgen/configured_feature/quartz_geode.json`, etc. 

## Placed features
Similar to the configured features, you can edit the geode placed features by replacing their files in the directories `geodes/worldgen/placed_feature/emerald_geode.json`, `geodes/worldgen/placed_feature/quartz_geode.json`, etc. 

## Biome placement
Rather than editing or overriding specific biomes in order to add or remove different geode features, it would instead be better to edit the biome tags `geodes:has_emerald_geode`, `geodes:has_quartz_geode`, etc. These tags exist in the directory `geodes/tags/worldgen/biome/`, and the default tags for them can be found [here](https://github.com/TheDeathlyCow/more-geodes/tree/main/src/main/resources/data/geodes/tags/worldgen/biome).

**Important note**: These changes can ONLY take affect after a server or world restart. A simple reload is not sufficient! 

# Configuration [Fabric Version Only]

The generation of each geode can be independently disabled via a config. The config should be placed in your standard config directory, under the file name `geodes.properties`. Any changes to the config require a game or server restart.

The default config is as follows:
```properties
generate_emerald_geodes=true
generate_quartz_geodes=true
generate_diamond_geodes=true
generate_echo_geodes=true
generate_lapis_geodes=true
generate_gypsum_patches=true
```

Note that this only disables the biome modifications, so the command `/place feature` should still work for disabled geodes, and it *shouldn't* break any pre-existing worlds that had these enabled.

Each of these values are booleans and are parsed using [Boolean#parseBoolean](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Boolean.html#parseBoolean(java.lang.String)). Therefore, entering anything other than a case insentive version of "true" will be interpreted as false. 

## Carpet Integration

If you have the [Carpet](https://modrinth.com/mod/carpet) mod installed, turning on the setting `moveableAmethyst` will allow you to pick up Budding blocks with silk touch and push them with pistons.

## AE2 Integration

If you have the [Applied Energistics 2](https://modrinth.com/mod/ae2) mod installed, you'll be able to use the `generate_certus_geodes` setting to find certus quartz geodes in your world. Also, all of this here mod's budding blocks will be able to generate clusters more efficiently using AE2's `Crystal Growth Accelerator`.

# Translations

If you'd like to create translations for More Geodes, the project is on [Crowdin](https://crowdin.com/project/more-geodes). You can also provide translations via issue or pull request if you so wish. Thanks for your contribution!
