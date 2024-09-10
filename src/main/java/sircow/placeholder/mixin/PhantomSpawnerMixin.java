package sircow.placeholder.mixin;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.spawner.PhantomSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PhantomSpawner.class)
public class PhantomSpawnerMixin {
    // prevent phantoms from spawning
    @Inject(method = "spawn", at = @At("HEAD"), cancellable = true)
    private void noPhantomSpawn(ServerWorld world, boolean spawnMonsters, boolean spawnAnimals, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(0);
    }
}
