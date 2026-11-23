package com.github.thedeathlycow.moregeodes.item.recipe;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeSerializer;
public final class GeodesRecipeSerializers {
    public static final RecipeSerializer<TuningRecipe> CRYSTAL_TUNING=Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,
        Identifier.fromNamespaceAndPath("geodes","crystal_tuning"),new RecipeSerializer<>(TuningRecipe.CODEC,TuningRecipe.STREAM_CODEC));
    public static void registerAll() {}
}
