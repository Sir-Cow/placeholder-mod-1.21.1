package sircow.placeholder.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sircow.placeholder.Placeholder;

@Mixin(LivingEntity.class)
public abstract class ConduitEntityMixin {
    // change mob death type to player death type
    /*
    @Inject(method = "dropLoot", at = @At("HEAD"))
    private void onDropLoot(DamageSource damageSource, boolean causedByPlayer, CallbackInfo ci) {
        Placeholder.LOGGER.info("damage source: {}", damageSource.getName());
        if (damageSource.getName().equals("magic")){
            causedByPlayer = true;
            assert damageSource != null;
            Placeholder.LOGGER.info("damage source: {}", damageSource.getName());
        }
    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onDeath(DamageSource damageSource, CallbackInfo ci) {
        Placeholder.LOGGER.info("name is: {}", damageSource.getName());
        Placeholder.LOGGER.info("attacker is: {}", damageSource.getAttacker());
        Placeholder.LOGGER.info("source is: {}", damageSource.getSource());
        Placeholder.LOGGER.info("type is: {}", damageSource.getType());

        if (damageSource.getName().equals("magic")) {
            //assert player != null;
            //damageSource = player.getRecentDamageSource();
            Entity attacker = damageSource.getAttacker();
            if (attacker instanceof PlayerEntity) {
                DamageSource playerDamageSource = DamageSource.player((PlayerEntity) attacker);
            }
        }

        assert damageSource != null;
        Placeholder.LOGGER.info("new name is: {}", damageSource.getName());
        Placeholder.LOGGER.info("new attacker is: {}", damageSource.getAttacker());
        Placeholder.LOGGER.info("new source is: {}", damageSource.getSource());
        Placeholder.LOGGER.info("new type is: {}", damageSource.getType());
    */
}

// conduit damage is called magic