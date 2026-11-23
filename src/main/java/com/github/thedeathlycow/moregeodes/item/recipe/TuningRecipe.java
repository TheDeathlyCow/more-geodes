package com.github.thedeathlycow.moregeodes.item.recipe;

import com.github.thedeathlycow.moregeodes.item.GeodesItemGroup;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.*;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
public class TuningRecipe extends NormalCraftingRecipe {
    public static final MapCodec<TuningRecipe> CODEC=RecordCodecBuilder.mapCodec(i->i.group(
        Recipe.CommonInfo.MAP_CODEC.forGetter(r->r.commonInfo),
        CraftingRecipe.CraftingBookInfo.MAP_CODEC.forGetter(r->r.bookInfo),
        Identifier.CODEC.fieldOf("tuning").forGetter(r->r.tuning),
        Ingredient.CODEC.listOf(1,9).fieldOf("ingredients").forGetter(r->r.inputs)
    ).apply(i,TuningRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf,TuningRecipe> STREAM_CODEC=StreamCodec.composite(
        Recipe.CommonInfo.STREAM_CODEC,r->r.commonInfo,CraftingRecipe.CraftingBookInfo.STREAM_CODEC,r->r.bookInfo,
        Identifier.STREAM_CODEC,r->r.tuning,Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),r->r.inputs,TuningRecipe::new);
    private final ShapelessRecipe delegate;
    private final Identifier tuning;
    private final List<Ingredient> inputs;
    public TuningRecipe(Recipe.CommonInfo common,CraftingRecipe.CraftingBookInfo book,Identifier tuning,List<Ingredient> inputs) {
        super(common,book);
        delegate=new ShapelessRecipe(common,book,new ItemStackTemplate(com.github.thedeathlycow.moregeodes.item.ModItems.TUNED_CRYSTAL_LOCATOR, net.minecraft.core.component.DataComponentPatch.builder().set(com.github.thedeathlycow.moregeodes.item.ModComponents.TUNING,tuning).build()),inputs);
        this.tuning=tuning;this.inputs=List.copyOf(inputs);
    }
    @Override protected PlacementInfo createPlacementInfo() {return delegate.placementInfo();}
    @Override public boolean matches(CraftingInput input,net.minecraft.world.level.Level level) {return delegate.matches(input,level);}
    @Override public net.minecraft.world.item.ItemStack assemble(CraftingInput input) {return delegate.assemble(input);}
    @Override public List<net.minecraft.world.item.crafting.display.RecipeDisplay> display() {return delegate.display();}
    @Override public RecipeSerializer<TuningRecipe> getSerializer() {return GeodesRecipeSerializers.CRYSTAL_TUNING;}
}
