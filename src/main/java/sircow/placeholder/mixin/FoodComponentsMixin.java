package sircow.placeholder.mixin;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodComponents.class)
public class FoodComponentsMixin {
    @Mutable @Final @Shadow public static FoodComponent APPLE;
    @Mutable @Final @Shadow public static FoodComponent BAKED_POTATO;
    @Mutable @Final @Shadow public static FoodComponent BEETROOT;
    @Mutable @Final @Shadow public static FoodComponent BEETROOT_SOUP;
    @Mutable @Final @Shadow public static FoodComponent BREAD;
    @Mutable @Final @Shadow public static FoodComponent CARROT;
    @Mutable @Final @Shadow public static FoodComponent CHORUS_FRUIT;
    @Mutable @Final @Shadow public static FoodComponent COOKED_CHICKEN;
    @Mutable @Final @Shadow public static FoodComponent COOKED_COD;
    @Mutable @Final @Shadow public static FoodComponent COOKED_MUTTON;
    @Mutable @Final @Shadow public static FoodComponent COOKED_PORKCHOP;
    @Mutable @Final @Shadow public static FoodComponent COOKED_RABBIT;
    @Mutable @Final @Shadow public static FoodComponent COOKED_SALMON;
    @Mutable @Final @Shadow public static FoodComponent COOKIE;
    @Mutable @Final @Shadow public static FoodComponent DRIED_KELP;
    @Mutable @Final @Shadow public static FoodComponent ENCHANTED_GOLDEN_APPLE;
    @Mutable @Final @Shadow public static FoodComponent GOLDEN_APPLE;
    @Mutable @Final @Shadow public static FoodComponent GLOW_BERRIES;
    @Mutable @Final @Shadow public static FoodComponent GOLDEN_CARROT;
    @Mutable @Final @Shadow public static FoodComponent HONEY_BOTTLE;
    @Mutable @Final @Shadow public static FoodComponent MELON_SLICE;
    @Mutable @Final @Shadow public static FoodComponent MUSHROOM_STEW;
    @Mutable @Final @Shadow public static FoodComponent POISONOUS_POTATO;
    @Mutable @Final @Shadow public static FoodComponent POTATO;
    @Mutable @Final @Shadow public static FoodComponent PUFFERFISH;
    @Mutable @Final @Shadow public static FoodComponent PUMPKIN_PIE;
    @Mutable @Final @Shadow public static FoodComponent RABBIT_STEW;
    @Mutable @Final @Shadow public static FoodComponent BEEF;
    @Mutable @Final @Shadow public static FoodComponent CHICKEN;
    @Mutable @Final @Shadow public static FoodComponent COD;
    @Mutable @Final @Shadow public static FoodComponent MUTTON;
    @Mutable @Final @Shadow public static FoodComponent PORKCHOP;
    @Mutable @Final @Shadow public static FoodComponent RABBIT;
    @Mutable @Final @Shadow public static FoodComponent SALMON;
    @Mutable @Final @Shadow public static FoodComponent ROTTEN_FLESH;
    @Mutable @Final @Shadow public static FoodComponent SPIDER_EYE;
    @Mutable @Final @Shadow public static FoodComponent COOKED_BEEF;
    @Mutable @Final @Shadow public static FoodComponent SUSPICIOUS_STEW;
    @Mutable @Final @Shadow public static FoodComponent SWEET_BERRIES;
    @Mutable @Final @Shadow public static FoodComponent TROPICAL_FISH;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void modifyFood(CallbackInfo ci) {
        APPLE = new FoodComponent.Builder().nutrition(4).saturationModifier(1.0F).snack().build();
        BAKED_POTATO = new FoodComponent.Builder().nutrition(5).saturationModifier(1.2F).build();
        BEETROOT = new FoodComponent.Builder().nutrition(2).saturationModifier(1.0F).snack().build();
        BEETROOT_SOUP = new FoodComponent.Builder().nutrition(8).saturationModifier(1.5F).usingConvertsTo(Items.BOWL).build();
        BREAD = new FoodComponent.Builder().nutrition(4).saturationModifier(0.8F).build();
        CARROT = new FoodComponent.Builder().nutrition(3).saturationModifier(1.0F).snack().build();
        CHORUS_FRUIT = new FoodComponent.Builder().nutrition(2).saturationModifier(1.0F).snack().alwaysEdible().build();
        COOKED_CHICKEN = new FoodComponent.Builder().nutrition(6).saturationModifier(1.2F).build();
        COOKED_COD = new FoodComponent.Builder().nutrition(5).saturationModifier(1.2F).build();
        COOKED_MUTTON = new FoodComponent.Builder().nutrition(6).saturationModifier(1.2F).build();
        COOKED_PORKCHOP = new FoodComponent.Builder().nutrition(6).saturationModifier(1.4F).build();
        COOKED_RABBIT = new FoodComponent.Builder().nutrition(5).saturationModifier(1.2F).build();
        COOKED_SALMON = new FoodComponent.Builder().nutrition(6).saturationModifier(1.2F).build();
        COOKIE = new FoodComponent.Builder().nutrition(2).saturationModifier(0.2F).snack().build();
        DRIED_KELP = new FoodComponent.Builder().nutrition(1).saturationModifier(1.2F).build();
        ENCHANTED_GOLDEN_APPLE = new FoodComponent.Builder()
                .nutrition(8)
                .saturationModifier(1.2F)
                .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 400, 1), 1.0F)
                .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 0), 1.0F)
                .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 0), 1.0F)
                .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 3), 1.0F)
                .alwaysEdible()
                .build();
        GOLDEN_APPLE = new FoodComponent.Builder()
                .nutrition(4)
                .saturationModifier(1.2F)
                .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F)
                .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 0), 1.0F)
                .alwaysEdible()
                .build();
        GLOW_BERRIES = new FoodComponent.Builder().nutrition(2).saturationModifier(1.2F).snack().build();
        GOLDEN_CARROT = new FoodComponent.Builder().nutrition(6).saturationModifier(2.4F).build();
        HONEY_BOTTLE = new FoodComponent.Builder()
                .nutrition(6)
                .saturationModifier(0.2F)
                .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 0, 0), 1.0F)
                .snack()
                .build();
        MELON_SLICE = new FoodComponent.Builder().nutrition(2).saturationModifier(1.0F).snack().build();
        MUSHROOM_STEW = new FoodComponent.Builder().nutrition(8).saturationModifier(1.5F).usingConvertsTo(Items.BOWL).build();
        POISONOUS_POTATO = new FoodComponent.Builder()
                .nutrition(2)
                .saturationModifier(0.0F)
                .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0), 0.6F)
                .snack()
                .build();
        POTATO = new FoodComponent.Builder().nutrition(2).saturationModifier(1.0F).snack().build();
        PUFFERFISH = new FoodComponent.Builder()
                .nutrition(1).saturationModifier(0.2F)
                .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 1200, 1), 1.0F)
                .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 300, 2), 1.0F)
                .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 300, 0), 1.0F)
                .build();
        PUMPKIN_PIE = new FoodComponent.Builder().nutrition(8).saturationModifier(1.2F).build();
        RABBIT_STEW = new FoodComponent.Builder().nutrition(10).saturationModifier(1.2F).usingConvertsTo(Items.BOWL).build();
        BEEF = new FoodComponent.Builder()
                .nutrition(2)
                .saturationModifier(0.6F)
                .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 300, 0), 0.3F)
                .build();
        CHICKEN = new FoodComponent.Builder()
                .nutrition(2)
                .saturationModifier(0.6F)
                .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600, 0), 0.5F)
                .build();
        COD = new FoodComponent.Builder()
                .nutrition(2).saturationModifier(0.6F)
                .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 300, 0), 0.3F)
                .build();
        MUTTON = new FoodComponent.Builder()
                .nutrition(2).saturationModifier(0.6F)
                .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 300, 0), 0.3F)
                .build();
        PORKCHOP = new FoodComponent.Builder()
                .nutrition(2)
                .saturationModifier(0.6F)
                .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 300, 0), 0.3F)
                .build();
        RABBIT = new FoodComponent.Builder()
                .nutrition(2)
                .saturationModifier(0.6F)
                .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 300, 0), 0.3F)
                .build();
        SALMON = new FoodComponent.Builder()
                .nutrition(2)
                .saturationModifier(0.6F)
                .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 300, 0), 0.3F)
                .build();
        ROTTEN_FLESH = new FoodComponent.Builder()
                .nutrition(4)
                .saturationModifier(0.0F)
                .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600, 0), 1.0F)
                .build();
        SPIDER_EYE = new FoodComponent.Builder()
                .nutrition(2)
                .saturationModifier(0.0F)
                .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0), 1.0F)
                .snack()
                .build();
        COOKED_BEEF = new FoodComponent.Builder().nutrition(6).saturationModifier(1.2F).build();
        SUSPICIOUS_STEW = new FoodComponent.Builder().nutrition(8).saturationModifier(1.6F).usingConvertsTo(Items.BOWL).alwaysEdible().build();
        SWEET_BERRIES = new FoodComponent.Builder().nutrition(2).saturationModifier(1.2F).snack().build();
        TROPICAL_FISH = new FoodComponent.Builder()
                .nutrition(2)
                .saturationModifier(0.6F)
                .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600, 0), 1.0F)
                .build();
    }
}
