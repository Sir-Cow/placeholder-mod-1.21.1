package sircow.placeholder.mixin;

import net.minecraft.item.PotionItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PotionItem.class)
public abstract class PotionItemMixin {
    // half the time of consuming a potion
    @ModifyConstant(method = "getMaxUseTime", constant = @Constant(intValue = 32))
    private int modifyIntValue(int original) { return 16; }
}
