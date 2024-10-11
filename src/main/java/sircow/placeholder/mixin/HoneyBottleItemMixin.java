package sircow.placeholder.mixin;

import net.minecraft.item.HoneyBottleItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(HoneyBottleItem.class)
public abstract class HoneyBottleItemMixin {
    // modify the time of consuming a honey bottle
    @ModifyConstant(method = "getMaxUseTime", constant = @Constant(intValue = 40))
    private int modifyIntValue(int original) { return 16; }
}
