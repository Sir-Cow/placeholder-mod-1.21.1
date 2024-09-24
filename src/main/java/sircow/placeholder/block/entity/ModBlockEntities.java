package sircow.placeholder.block.entity;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import sircow.placeholder.Placeholder;
import sircow.placeholder.block.ModBlocks;

public class ModBlockEntities {
    public static final BlockEntityType<NewCauldronBlockEntity> NEW_CAULDRON_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Placeholder.MOD_ID, "new_cauldron_entity"),
                    BlockEntityType.Builder.create(NewCauldronBlockEntity::new,
                            ModBlocks.NEW_CAULDRON_BLOCK).build());

    public static void registerBlockEntities() {
        Placeholder.LOGGER.info("Registering Block Entities for " + Placeholder.MOD_ID);
    }
}
