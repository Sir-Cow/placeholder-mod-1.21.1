package sircow.placeholder.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.RailShape;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;

public class InductorRailBlock extends AbstractRailBlock {
    public static final MapCodec<InductorRailBlock> CODEC = createCodec(InductorRailBlock::new);
    public static final EnumProperty<RailShape> SHAPE = Properties.STRAIGHT_RAIL_SHAPE;
    public static final BooleanProperty POWERED = Properties.POWERED;

    @Override
    public MapCodec<InductorRailBlock> getCodec() {
        return CODEC;
    }

    @Override
    public Property<RailShape> getShapeProperty() {
        return SHAPE;
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        switch (rotation) {
            case CLOCKWISE_180:
                switch (state.get(SHAPE)) {
                    case ASCENDING_EAST:
                        return state.with(SHAPE, RailShape.ASCENDING_WEST);
                    case ASCENDING_WEST:
                        return state.with(SHAPE, RailShape.ASCENDING_EAST);
                    case ASCENDING_NORTH:
                        return state.with(SHAPE, RailShape.ASCENDING_SOUTH);
                    case ASCENDING_SOUTH:
                        return state.with(SHAPE, RailShape.ASCENDING_NORTH);
                    case SOUTH_EAST:
                        return state.with(SHAPE, RailShape.NORTH_WEST);
                    case SOUTH_WEST:
                        return state.with(SHAPE, RailShape.NORTH_EAST);
                    case NORTH_WEST:
                        return state.with(SHAPE, RailShape.SOUTH_EAST);
                    case NORTH_EAST:
                        return state.with(SHAPE, RailShape.SOUTH_WEST);
                }
            case COUNTERCLOCKWISE_90:
                return switch (state.get(SHAPE)) {
                    case NORTH_SOUTH -> state.with(SHAPE, RailShape.EAST_WEST);
                    case EAST_WEST -> state.with(SHAPE, RailShape.NORTH_SOUTH);
                    case ASCENDING_EAST -> state.with(SHAPE, RailShape.ASCENDING_NORTH);
                    case ASCENDING_WEST -> state.with(SHAPE, RailShape.ASCENDING_SOUTH);
                    case ASCENDING_NORTH -> state.with(SHAPE, RailShape.ASCENDING_WEST);
                    case ASCENDING_SOUTH -> state.with(SHAPE, RailShape.ASCENDING_EAST);
                    case SOUTH_EAST -> state.with(SHAPE, RailShape.NORTH_EAST);
                    case SOUTH_WEST -> state.with(SHAPE, RailShape.SOUTH_EAST);
                    case NORTH_WEST -> state.with(SHAPE, RailShape.SOUTH_WEST);
                    case NORTH_EAST -> state.with(SHAPE, RailShape.NORTH_WEST);
                };
            case CLOCKWISE_90:
                return switch (state.get(SHAPE)) {
                    case NORTH_SOUTH -> state.with(SHAPE, RailShape.EAST_WEST);
                    case EAST_WEST -> state.with(SHAPE, RailShape.NORTH_SOUTH);
                    case ASCENDING_EAST -> state.with(SHAPE, RailShape.ASCENDING_SOUTH);
                    case ASCENDING_WEST -> state.with(SHAPE, RailShape.ASCENDING_NORTH);
                    case ASCENDING_NORTH -> state.with(SHAPE, RailShape.ASCENDING_EAST);
                    case ASCENDING_SOUTH -> state.with(SHAPE, RailShape.ASCENDING_WEST);
                    case SOUTH_EAST -> state.with(SHAPE, RailShape.SOUTH_WEST);
                    case SOUTH_WEST -> state.with(SHAPE, RailShape.NORTH_WEST);
                    case NORTH_WEST -> state.with(SHAPE, RailShape.NORTH_EAST);
                    case NORTH_EAST -> state.with(SHAPE, RailShape.SOUTH_EAST);
                };
            default:
                return state;
        }
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        RailShape railShape = state.get(SHAPE);
        switch (mirror) {
            case LEFT_RIGHT:
                return switch (railShape) {
                    case ASCENDING_NORTH -> state.with(SHAPE, RailShape.ASCENDING_SOUTH);
                    case ASCENDING_SOUTH -> state.with(SHAPE, RailShape.ASCENDING_NORTH);
                    case SOUTH_EAST -> state.with(SHAPE, RailShape.NORTH_EAST);
                    case SOUTH_WEST -> state.with(SHAPE, RailShape.NORTH_WEST);
                    case NORTH_WEST -> state.with(SHAPE, RailShape.SOUTH_WEST);
                    case NORTH_EAST -> state.with(SHAPE, RailShape.SOUTH_EAST);
                    default -> super.mirror(state, mirror);
                };
            case FRONT_BACK:
                switch (railShape) {
                    case ASCENDING_EAST:
                        return state.with(SHAPE, RailShape.ASCENDING_WEST);
                    case ASCENDING_WEST:
                        return state.with(SHAPE, RailShape.ASCENDING_EAST);
                    case ASCENDING_NORTH:
                    case ASCENDING_SOUTH:
                    default:
                        break;
                    case SOUTH_EAST:
                        return state.with(SHAPE, RailShape.SOUTH_WEST);
                    case SOUTH_WEST:
                        return state.with(SHAPE, RailShape.SOUTH_EAST);
                    case NORTH_WEST:
                        return state.with(SHAPE, RailShape.NORTH_EAST);
                    case NORTH_EAST:
                        return state.with(SHAPE, RailShape.NORTH_WEST);
                }
        }

        return super.mirror(state, mirror);
    }

    public InductorRailBlock(AbstractBlock.Settings settings) {
        super(false, settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(POWERED, Boolean.TRUE).with(SHAPE, RailShape.NORTH_SOUTH).with(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SHAPE, POWERED, WATERLOGGED);
    }
}
