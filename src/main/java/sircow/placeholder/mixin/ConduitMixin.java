package sircow.placeholder.mixin;

import net.minecraft.block.entity.ConduitBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ConduitBlockEntity.class)
public class ConduitMixin {
    // extend conduit radius
    @ModifyConstant(method = "attackHostileEntity", constant = @Constant(doubleValue = 8.0F))
    private static double modifyDoubleValue(double original) {
        return 16.0F;
    }
    // extend conduit radius
    @ModifyConstant(method = "getAttackZone", constant = @Constant(doubleValue = 8.0F))
    private static double modifyDoubleValueAgain(double original) {
        return 16.0F;
    }
    // damage speed
    @ModifyConstant(method = "clientTick", constant = @Constant(longValue = 40L))
    private static long modifyLongValue(long original) {
        return 1L;
    }
    @ModifyConstant(method = "serverTick", constant = @Constant(longValue = 40L))
    private static long modifyLongValueAgain(long original) {
        return 1L;
    }
    // remove in rain or water to grant effect
    @Inject(method = "givePlayersEffects", at = @At("HEAD"), cancellable = true)
    private static void givePlayersEffects(World world, BlockPos pos, List<BlockPos> activatingBlocks, CallbackInfo ci) {
        int i = activatingBlocks.size();
        int j = i / 7 * 16;
        int k = pos.getX();
        int l = pos.getY();
        int m = pos.getZ();
        Box box = new Box((double) k, (double) l, (double) m, (double) (k + 1), (double) (l + 1), (double) (m + 1))
                .expand((double) j)
                .stretch(0.0, (double) world.getHeight(), 0.0);
        List<PlayerEntity> list = world.getNonSpectatingEntities(PlayerEntity.class, box);
        if (!list.isEmpty()) {
            for (PlayerEntity playerEntity : list) {
                if (pos.isWithinDistance(playerEntity.getBlockPos(), (double) j)) {
                    playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 260, 0, true, true));
                }
            }
        }
        ci.cancel(); // Cancel the original method call
    }
}