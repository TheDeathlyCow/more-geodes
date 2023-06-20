package com.github.thedeathlycow.moregeodes.mixin.datafixer;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.datafixers.GeodeBlockRenaming;
import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.datafixers.DataFixerUpper;
import com.mojang.datafixers.schemas.Schema;
import net.minecraft.datafixer.Schemas;
import net.minecraft.datafixer.fix.BlockNameFix;
import net.minecraft.datafixer.fix.ItemNameFix;
import net.minecraft.datafixer.mapping.LegacyCoralBlockMapping;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

@Mixin(Schemas.class)
public abstract class SchemasMixin {

    @Shadow
    @Final
    private static BiFunction<Integer, Schema, Schema> EMPTY_IDENTIFIER_NORMALIZE;

    @Shadow
    private static UnaryOperator<String> replacing(Map<String, String> replacements) {
        return null;
    }

    @Inject(
            method = "build",
            at = @At("TAIL")
    )
    private static void addToBuilder(DataFixerBuilder builder, CallbackInfo ci) {
        Schema schema = builder.addSchema(3328, EMPTY_IDENTIFIER_NORMALIZE);
        builder.addFixer(
                BlockNameFix.create(
                        schema,
                        "[More Geodes] Rename geode blocks",
                        replacing(GeodeBlockRenaming.MAP)
                )
        );
        builder.addFixer(
                ItemNameFix.create(
                        schema,
                        "[More Geodes] Rename geode items",
                        replacing(GeodeBlockRenaming.MAP)
                )
        );
    }

}
