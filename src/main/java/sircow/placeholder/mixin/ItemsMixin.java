package sircow.placeholder.mixin;

import net.minecraft.block.Block;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

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
    @ModifyArg(method = "<clinit>", slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=beetroot_soup")),
            at = @At(value = "INVOKE", target = "net/minecraft/item/Item.<init>(Lnet/minecraft/item/Item$Settings;)V", ordinal = 0))
    private static Item.Settings modifyBeetrootSoup(Item.Settings vanilla)
    { return new Item.Settings().maxCount(16).food(FoodComponents.BEETROOT_SOUP); }
    @ModifyArg(method = "<clinit>", slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=mushroom_stew")),
            at = @At(value = "INVOKE", target = "net/minecraft/item/Item.<init>(Lnet/minecraft/item/Item$Settings;)V", ordinal = 0))
    private static Item.Settings modifyMushroomStew(Item.Settings vanilla)
    { return new Item.Settings().maxCount(16).food(FoodComponents.MUSHROOM_STEW); }
    @ModifyArg(method = "<clinit>", slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=rabbit_stew")),
            at = @At(value = "INVOKE", target = "net/minecraft/item/Item.<init>(Lnet/minecraft/item/Item$Settings;)V", ordinal = 0))
    private static Item.Settings modifyRabbitStew(Item.Settings vanilla)
    { return new Item.Settings().maxCount(16).food(FoodComponents.RABBIT_STEW); }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item$Settings;maxCount(I)Lnet/minecraft/item/Item$Settings;", ordinal = 0),
            slice = @Slice( from = @At(value = "NEW", target = "Lnet/minecraft/item/SuspiciousStewItem;")))
    private static int modifySuspiciousStewStackSize(int old) { return 16; }

    // make honeycomb edible
    @ModifyArg(method = "<clinit>", slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=honeycomb")),
            at = @At(value = "INVOKE", target = "net/minecraft/item/HoneycombItem.<init>(Lnet/minecraft/item/Item$Settings;)V", ordinal = 0))
    private static Item.Settings modifyHoneycomb (Item.Settings settings) {
        return settings.food(
                new FoodComponent.Builder().nutrition(6).saturationModifier(0.1F).snack().build());
    }
}

