package sircow.placeholder.mixin;

import net.minecraft.block.entity.ConduitBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(ConduitBlockEntity.class)
public class ConduitMixin {
    // extend conduit radius
    @ModifyConstant(method = "attackHostileEntity", constant = @Constant(doubleValue = 8.0F))
    private static double modifyDoubleValue(double original) {
        return 16.0F;
    }
    // extend conduit radius
    @ModifyConstant(method = "getAttackZone", constant = @Constant(doubleValue = 8.0F))
    private static double modifyDoubleValueAgain(double original) {
        return 16.0F;
    }
}