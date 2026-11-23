package com.github.thedeathlycow.moregeodes.state.property;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction;


public class ModProperties {

    public static final EnumProperty<Direction> ROTATION = EnumProperty.create("rotation", Direction.class, Direction.NORTH, Direction.EAST);
    public static final EnumProperty<Direction> POINTING = EnumProperty.create("pointing", Direction.class, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST, Direction.UP, Direction.DOWN);

}
