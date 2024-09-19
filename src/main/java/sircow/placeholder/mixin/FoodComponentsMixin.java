package sircow.placeholder.mixin;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.FoodComponents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodComponents.class)
public class FoodComponentsMixin {
    /*
    @Mutable
    @Final
    @Shadow
    public static FoodComponent BREAD;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void adjustSweetBerries(CallbackInfo ci) {
        BREAD = new FoodComponent.Builder().nutrition(10).saturationModifier(0.6F).build();
    }
    */
}
