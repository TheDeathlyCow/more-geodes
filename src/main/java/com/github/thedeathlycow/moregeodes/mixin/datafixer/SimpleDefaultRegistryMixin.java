package com.github.thedeathlycow.moregeodes.mixin.datafixer;

import com.github.thedeathlycow.moregeodes.datafixers.GeodeBlockRenaming;
import net.minecraft.registry.SimpleDefaultedRegistry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(SimpleDefaultedRegistry.class)
public class SimpleDefaultRegistryMixin {

    @ModifyVariable(
            method = "get(Lnet/minecraft/util/Identifier;)Ljava/lang/Object;",
            at = @At("HEAD"),
            ordinal = 0,
            argsOnly = true
    )
    private Identifier fixOldIds(Identifier id) {
        if (id != null) {
            if (GeodeBlockRenaming.MAP.containsKey(id)) {
                return GeodeBlockRenaming.MAP.get(id);
            }
        }
        return id;
    }

}
