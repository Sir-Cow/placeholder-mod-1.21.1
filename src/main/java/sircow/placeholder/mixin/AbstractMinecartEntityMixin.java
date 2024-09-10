package sircow.placeholder.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import sircow.placeholder.Placeholder;
import sircow.placeholder.block.ModBlocks;

@Mixin(AbstractMinecartEntity.class)
public abstract class AbstractMinecartEntityMixin extends Entity {
    @Unique
    private double maxSpeed = 8.0;

    public AbstractMinecartEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    // check for rail tag
    @Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private boolean poweredRailTagCheck(BlockState state, Block block) {
        return state.isIn(Placeholder.TAG_POWERED_RAILS);
    }

    // set the acceleration
    @Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;add(DDD)Lnet/minecraft/util/math/Vec3d;", ordinal = 5))
    private Vec3d acceleration(Vec3d vector, double x, double y, double z) {
        Vec3d newVector = vector.add(x, y, z);
        BlockState blockState = this.getWorld().getBlockState(this.getBlockPos());
        if (blockState.isOf(ModBlocks.INDUCTOR_RAIL)) {
            return newVector.multiply(80 / 8d);
        }
        return newVector;
    }

    // set the speed cap
    @Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(DD)D"))
    private double speedCap(double a, double b) {
        return Math.min(8.0, b);
    }

    // increase speed
    @Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/AbstractMinecartEntity;getMaxSpeed()D"))
    public double maxSpeedIncrease(AbstractMinecartEntity instance) {
        double speed = maxSpeed;
        BlockState blockState = this.getWorld().getBlockState(this.getBlockPos());
        if (blockState.isOf(ModBlocks.INDUCTOR_RAIL)) {
            speed = 40.0;
        }
        maxSpeed = speed;
        return speed / (this.isTouchingWater() ? 40.0 : 20.0);
    }
}
