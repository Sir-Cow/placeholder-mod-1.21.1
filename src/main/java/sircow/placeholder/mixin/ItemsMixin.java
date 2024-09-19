package sircow.placeholder.mixin;

import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.item.Items.register;

@Mixin(Items.class)
public abstract class ItemsMixin {
    // modify stack size of potions
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item$Settings;maxCount(I)Lnet/minecraft/item/Item$Settings;", ordinal = 0),
            slice = @Slice( from = @At(value = "NEW", target = "Lnet/minecraft/item/PotionItem;")))
    private static int modifyPotionStackSize(int old) { return 16; }
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item$Settings;maxCount(I)Lnet/minecraft/item/Item$Settings;", ordinal = 0),
            slice = @Slice( from = @At(value = "NEW", target = "Lnet/minecraft/item/SplashPotionItem;")))
    private static int modifySplashPotionStackSize(int old) { return 16; }
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item$Settings;maxCount(I)Lnet/minecraft/item/Item$Settings;", ordinal = 0),
            slice = @Slice( from = @At(value = "NEW", target = "Lnet/minecraft/item/LingeringPotionItem;")))
    private static int modifyLingeringPotionStackSize(int old) { return 16; }

    // modify stew/soup stack sizes
    @Mutable @Final @Shadow public static Item BEETROOT_SOUP;
    @Mutable @Final @Shadow public static Item MUSHROOM_STEW;
    @Mutable @Final @Shadow public static Item RABBIT_STEW;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void modifyStewStackSize(CallbackInfo ci) {
        BEETROOT_SOUP = register("beetroot_soup", new Item(new Item.Settings().maxCount(16).food(FoodComponents.BEETROOT_SOUP)));
        MUSHROOM_STEW = register("mushroom_stew", new Item(new Item.Settings().maxCount(16).food(FoodComponents.MUSHROOM_STEW)));
        RABBIT_STEW = register("rabbit_stew", new Item(new Item.Settings().maxCount(16).food(FoodComponents.RABBIT_STEW)));
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item$Settings;maxCount(I)Lnet/minecraft/item/Item$Settings;", ordinal = 0),
            slice = @Slice( from = @At(value = "NEW", target = "Lnet/minecraft/item/SuspiciousStewItem;")))
    private static int modifySuspiciousStewStackSize(int old) { return 16; }
}