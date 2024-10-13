package sircow.placeholder.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net.minecraft.screen.BrewingStandScreenHandler$FuelSlot")
public class BrewingStandScreenHandlerMixin {
    // change inserting blaze powder into brewing stands to nether wart
    @ModifyReturnValue(method = "matches", at = @At("RETURN"))
    private static boolean matches(boolean original, ItemStack stack) {
        return stack.isOf(Items.NETHER_WART);
    }
}