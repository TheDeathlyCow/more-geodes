package com.thedeathlycow.moregeodes

import com.thedeathlycow.moregeodes.registry.MGItems
import net.minecraft.util.Identifier

object MoreGeodes {

    const val MOD_ID: String = "geodes"

    fun init() {
        MGItems.initialize()
    }

    fun id(name: String): Identifier {
        return Identifier.of(MOD_ID, name)
    }

}