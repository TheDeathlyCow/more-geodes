package com.github.thedeathlycow.moregeodes.mixin.datafixer;

import com.github.thedeathlycow.moregeodes.datafixers.GeodeBlockRenaming;
import net.minecraft.registry.SimpleDefaultedRegistry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * This is a datafixer-like mixin that hacks ID transformations to work without using DFU
 *
 * Credit: <a href="https://github.com/Noaaan/MythicMetals/blob/34959d8caf773d0c54c81281b6e57b19662579b3/src/main/java/nourl/mythicmetals/mixin/DefaultedRegistryMixin.java">Mythic Metals</a>
 */
@Mixin(SimpleDefaultedRegistry.class)
public class SimpleDefaultRegistryMixin {

    @ModifyVariable(
            method = "get(Lnet/minecraft/util/Identifier;)Ljava/lang/Object;",
            at = @At("HEAD"),
            ordinal = 0,
            argsOnly = true
    )
    private Identifier fixOldIds(Identifier id) {
        if (id != null && GeodeBlockRenaming.MAP.containsKey(id)) {
            return GeodeBlockRenaming.MAP.get(id);
        }
        return id;
    }

}
