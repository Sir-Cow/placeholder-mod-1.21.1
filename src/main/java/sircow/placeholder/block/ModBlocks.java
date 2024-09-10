package sircow.placeholder.block;

import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import sircow.placeholder.Placeholder;
import sircow.placeholder.block.custom.InductorRailBlock;

public class ModBlocks {
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Placeholder.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block){
        Registry.register(Registries.ITEM, Identifier.of(Placeholder.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static final Block INDUCTOR_RAIL = registerBlock("inductor_rail",
            new InductorRailBlock(AbstractBlock.Settings.copy(Blocks.RAIL).noCollision().strength(0.7F).sounds(BlockSoundGroup.METAL)));


    public static void registerModBlocks(){
        Placeholder.LOGGER.info("Registering Mod Blocks for " + Placeholder.MOD_ID);
    }
}

