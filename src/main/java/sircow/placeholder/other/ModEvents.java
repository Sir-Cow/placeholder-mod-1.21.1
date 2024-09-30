package sircow.placeholder.other;

import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import sircow.placeholder.Placeholder;

public class ModEvents {
    public static void disableSleeping() {
        EntitySleepEvents.ALLOW_SLEEPING.register((PlayerEntity player, BlockPos blockPos) -> PlayerEntity.SleepFailureReason.OTHER_PROBLEM);
    }

    public static void registerModEvents() {
        Placeholder.LOGGER.info("Registering Mod Events for " + Placeholder.MOD_ID);
    }
}
