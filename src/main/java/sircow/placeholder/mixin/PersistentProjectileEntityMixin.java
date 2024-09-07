package sircow.placeholder.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sircow.placeholder.Placeholder;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin {

    /*
    @Shadow
    public abstract double getDamage();
    @Shadow
    public abstract void setDamage(double damage);

    @Inject(method = "onEntityHit", at = @At("HEAD"))
    private void onEntityHit(EntityHitResult entityHitResult, CallbackInfo ci) {
        PersistentProjectileEntity projectile = (PersistentProjectileEntity) (Object) this;
        PlayerEntity player = (PlayerEntity) projectile.getOwner();
        double damage = getDamage();
        assert player != null;
        for (ItemStack itemStack : player.getHandItems()) {
            if (entityHitResult.getEntity() instanceof LivingEntity target) {
                Placeholder.LOGGER.info("before damage: {}", damage);
                if (EnchantmentHelper.getLevel(target.getWorld().getRegistryManager()
                        .getWrapperOrThrow(Enchantments.PIERCING.getRegistryRef())
                        .getOrThrow(Enchantments.PIERCING), itemStack) > 0
                ){
                    Placeholder.LOGGER.info("piercing present: {}", target.getHealth());

                    damage *= (1.0F + (target.getArmor() / 15.0F));
                    setDamage(damage);
                }

                //Placeholder.LOGGER.info("name is: {}", target.getName());

                Placeholder.LOGGER.info("health: {}", target.getHealth());

                Placeholder.LOGGER.info("after damage: {}", damage);
                //Placeholder.LOGGER.info("after health: {}", target.getHealth());
                Placeholder.LOGGER.info("armor: {}", target.getArmor());
            }
        }


    }

    */

}