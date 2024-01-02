package com.github.thedeathlycow.moregeodes.item.tuning;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;

public class Tuning {

    public static final Tuning UNTUNED = new Tuning(
            ColorHelper.Argb.getArgb(0xff, 0x44, 0x44, 0x44),
            Text.translatable("geodes.tunings.untuned")
                    .setStyle(Style.EMPTY.withColor(TextColor.parse("gray"))),
            LocationPredicate.ANY
    );

    private static final String COLOR_KEY = "color";
    private static final String DESCRIPTION_KEY = "description";
    private static final String PREDICATE_KEY = "tuned_to";

    public static final Codec<Tuning> DATAPACK_CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.INT.fieldOf(COLOR_KEY).forGetter(Tuning::getColor),
                    CodecExtensions.TEXT_CODEC.fieldOf(DESCRIPTION_KEY).forGetter(Tuning::getDescription),
                    CodecExtensions.LOCATION_PREDICATE_CODEC.fieldOf(PREDICATE_KEY).forGetter(Tuning::getIsTunedToBlock)
            ).apply(instance, Tuning::new)
    );

    public static final Codec<Tuning> NETWORK_CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.INT.fieldOf(COLOR_KEY).forGetter(Tuning::getColor),
                    CodecExtensions.TEXT_CODEC.fieldOf(DESCRIPTION_KEY).forGetter(Tuning::getDescription)
            ).apply(instance, Tuning::new)
    );

    private final int color;
    private final Text description;
    private final LocationPredicate isTunedToBlock;

    public Tuning(int color, Text description, LocationPredicate isTunedToBlock) {
        this.color = color;
        this.description = description;
        this.isTunedToBlock = isTunedToBlock;
    }

    private Tuning(int color, Text description) {
        this(color, description, LocationPredicate.ANY);
    }

    public boolean test(ServerWorld world, BlockPos pos) {
        if (this.isTunedToBlock == null || this.isTunedToBlock == LocationPredicate.ANY) {
            return false;
        }

        return this.isTunedToBlock.test(world, pos.getX(), pos.getY(), pos.getZ());
    }

    public Text getDescription() {
        return description;
    }

    public int getColor() {
        return this.color;
    }

    public LocationPredicate getIsTunedToBlock() {
        return isTunedToBlock;
    }

}
