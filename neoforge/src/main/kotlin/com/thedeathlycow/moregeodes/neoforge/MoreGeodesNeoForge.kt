package com.thedeathlycow.moregeodes.neoforge

import com.thedeathlycow.moregeodes.MoreGeodes
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.neoforge.forge.runForDist

@Mod(MoreGeodes.MOD_ID)
object MoreGeodesNeoForge {

    init {
        runForDist(
            clientTarget = {
                MOD_BUS.addListener(::onClientSetup)
            },
            serverTarget = {

            }
        )
        MOD_BUS.addListener(::onCommonSetup)
    }

    private fun onClientSetup(event: FMLClientSetupEvent) {

    }

    private fun onCommonSetup(event: FMLCommonSetupEvent) {
        MoreGeodes.init()
    }
}