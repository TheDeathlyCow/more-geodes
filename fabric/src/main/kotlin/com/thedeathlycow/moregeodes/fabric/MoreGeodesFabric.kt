package com.thedeathlycow.moregeodes.fabric

import com.thedeathlycow.moregeodes.fabriclike.MoreGeodesFabricLike
import net.fabricmc.api.ModInitializer

object MoreGeodesFabric : ModInitializer {

    override fun onInitialize() {
        MoreGeodesFabricLike.init()
    }

}