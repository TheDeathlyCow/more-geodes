package com.github.thedeathlycow.moregeodes.item.tuning;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Colors;
import net.minecraft.util.math.BlockPos;

public class Tuning {

    public static final Tuning UNTUNED = new Tuning(
            Colors.BLACK,
            Text.translatable("geodes.tunings.untuned")
                    .setStyle(Style.EMPTY.withColor(TextColor.parse("gray"))),
            BlockPredicate.ANY
    );

    private static final String COLOR_KEY = "color";
    private static final String DESCRIPTION_KEY = "description";
    private static final String PREDICATE_KEY = "tuned_to";

    public static final Codec<Tuning> DATAPACK_CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.INT.fieldOf(COLOR_KEY).forGetter(Tuning::getColor),
                    CodecExtensions.TEXT_CODEC.fieldOf(DESCRIPTION_KEY).forGetter(Tuning::getDescription),
                    CodecExtensions.BLOCK_PREDICATE_CODEC.fieldOf(PREDICATE_KEY).forGetter(Tuning::getIsTunedToBlock)
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
    private final BlockPredicate isTunedToBlock;

    public Tuning(int color, Text description, BlockPredicate isTunedToBlock) {
        this.color = color;
        this.description = description;
        this.isTunedToBlock = isTunedToBlock;
    }

    private Tuning(int color, Text description) {
        this(color, description, BlockPredicate.ANY);
    }

    public boolean test(ServerWorld world, BlockPos pos) {
        if (this.isTunedToBlock == null || this.isTunedToBlock == BlockPredicate.ANY) {
            return false;
        }

        return this.isTunedToBlock.test(world, pos);
    }

    public Text getDescription() {
        return description;
    }

    public int getColor() {
        return this.color;
    }

    public BlockPredicate getIsTunedToBlock() {
        return isTunedToBlock;
    }

}
