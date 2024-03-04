package com.github.thedeathlycow.moregeodes.item.recipe;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.minecraft.recipe.RecipeSerializer;

public class GeodesRecipeSerializers {

    public static final RecipeSerializer<TuningRecipe> CRYSTAL_TUNING = new TuningRecipe.Serializer();

    public static void registerAll() {
        RecipeSerializer.register(
                String.format("%s:crystal_tuning", MoreGeodes.MODID),
                CRYSTAL_TUNING
        );
    }


    private GeodesRecipeSerializers() {
    }
}
