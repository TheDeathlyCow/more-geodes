package com.github.thedeathlycow.moregeodes.state.property;

import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.Direction;

public class ModProperties {

    public static final DirectionProperty ROTATION = DirectionProperty.of("rotation", Direction.NORTH, Direction.EAST);
    public static final DirectionProperty POINTING = DirectionProperty.of("pointing", Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST, Direction.UP, Direction.DOWN);

}
