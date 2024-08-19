package com.thedeathlycow.moregeodes.registry

import com.thedeathlycow.moregeodes.MoreGeodes
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

object MGItems {

    val GYPSUM_SHARD: Item = Item(Item.Settings())
    val PYRITE_CHUNK: Item = Item(Item.Settings())

    fun initialize() {
        register("gypsum_shard", GYPSUM_SHARD)
        register("pyrite_chunk", PYRITE_CHUNK)

    }

    private fun register(name: String, item: Item) {
        Registry.register(Registries.ITEM, MoreGeodes.id(name), item)
    }

}