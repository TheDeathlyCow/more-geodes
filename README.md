# More Geodes

Curse Forge mod page: https://www.curseforge.com/minecraft/mc-mods/emerald-geodes

The curse forge mod page above and this repository are the only official download pages for this mod, do not trust any version of this mod from elsewhere! 

Adds some extra geodes to the Minecraft Caves & Cliffs Update. 

## Emerald Geodes
Emerald geodes behave very similarly to amethyst geodes; they spawn as sphere-like objects underground encased in calcite and smooth basalt casings underground and can grow emerald crystal buds. When these buds are harvested, they of course drop emeralds. Provides an automate-able alternative to emerald farming than just trading endlessly. 

## Quartz Geodes
Quartz Geodes have many similarities to amethyst geodes and emerald geodes. Quartz buds and clusters grow on budding quartz, but budding quartz cannot be harvested. Quartz clusters will drop quartz when broken, but will drop an item form of itself when broken with silk touch, etc. However, quartz geodes spawn exclusively in The Nether, and with a middle layer of tuff instead of calcite. 

### Quartz Buds and Clusters
Quartz buds and clusters behave as an oscillator when powered by an external redstone source. That is, when a bud or cluster receives power, it will periodically turn on and off itself, emitting redstone power on an infinitely-repeating basis. The frequency at which it pulses is determined by its size. 

Buds pulse with the following frequencies:

| Block             | Frequency (Hz) |
| ----------------- | -------------- |
| Small Quartz Bud  | 0.5 Hz         |
| Medium Quartz Bud | 1 Hz           |
| Large Quartz Bud  | 2 Hz           |
| Quartz Cluster    | 10 Hz          |


# Datapack Configuration

This mod uses the namespace `geodes`. If you wish to make changes to the features in this mod using a datapack, all files must be put under that namespace. The emerald and quartz geodes use very similar values to the amethyst geode, and it can be used as an example for how to configure them. Slicedlime provides an example worldgen datapack that can be used as a reference [here](https://github.com/slicedlime/examples/blob/master/vanilla_worldgen.zip). 

## Configured features
You can make changes to the emerald and quartz geode configured features by replacing their files in the directories `geodes/worldgen/configured_feature/emerald_geode.json`or `geodes/worldgen/configured_feature/quartz_geode.json`. 

## Placed features
Similar to the configured features, you can edit the emerald and quartz geode placed features by replacing their files in the directories `geodes/worldgen/placed_feature/emerald_geode.json`or `geodes/worldgen/placed_feature/quartz_geode.json`. 

## Biome placement
Rather than editing or overriding specific biomes in order to add emerald and quartz geode feature, it would instead be better to edit the biome tags `geodes:has_emerald_geode` and `geodes:has_quartz_geode`. These tags exist in the directory `geodes/tags/worldgen/biome/`, and the default tags for them can be found [here](https://github.com/TheDeathlyCow/more-geodes/tree/main/src/main/resources/data/geodes/tags/worldgen/biome).

**Important note**: These changes can ONLY take affect after a server or world restart. A simple reload is not sufficient! 
