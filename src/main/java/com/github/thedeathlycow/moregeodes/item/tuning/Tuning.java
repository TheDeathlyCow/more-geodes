package com.github.thedeathlycow.moregeodes.item.tuning;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;
import net.minecraft.advancements.predicates.LocationPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.server.level.ServerLevel;
public record Tuning(int color, Component description, Optional<LocationPredicate> tunedTo) {
    public static final Tuning UNTUNED = new Tuning(0xff444444, Component.translatable("geodes.tunings.untuned").withColor(0xaaaaaa), Optional.empty());
    public static final Codec<Tuning> DATAPACK_CODEC = RecordCodecBuilder.create(i -> i.group(
        Codec.INT.fieldOf("color").forGetter(Tuning::color),
        ComponentSerialization.CODEC.fieldOf("description").forGetter(Tuning::description),
        LocationPredicate.CODEC.optionalFieldOf("tuned_to").forGetter(Tuning::tunedTo)
    ).apply(i,Tuning::new));
    public static final Codec<Tuning> NETWORK_CODEC = RecordCodecBuilder.create(i -> i.group(
        Codec.INT.fieldOf("color").forGetter(Tuning::color),
        ComponentSerialization.CODEC.fieldOf("description").forGetter(Tuning::description)
    ).apply(i,(color,description) -> new Tuning(color,description,Optional.empty())));
    public boolean test(ServerLevel level, BlockPos pos) { return tunedTo.isPresent() && tunedTo.get().matches(level,pos.getX(),pos.getY(),pos.getZ()); }
    public Component getDescription() { return description; }
    public int getColor() { return color; }
}
