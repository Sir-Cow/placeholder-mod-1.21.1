package sircow.placeholder.block;

import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import sircow.placeholder.Placeholder;
import sircow.placeholder.block.custom.InductorRailBlock;
import sircow.placeholder.block.custom.NewCauldronBlock;
import sircow.placeholder.block.custom.NewLoomBlock;

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
            new InductorRailBlock(Oxidizable.OxidationLevel.UNAFFECTED,
                    AbstractBlock.Settings.copy(Blocks.RAIL)
                            .noCollision()
                            .strength(0.7F)
                            .mapColor(MapColor.ORANGE)
                            .sounds(BlockSoundGroup.COPPER)
            ));
    public static final Block EXPOSED_INDUCTOR_RAIL = registerBlock("exposed_inductor_rail",
            new InductorRailBlock(Oxidizable.OxidationLevel.EXPOSED,
                    AbstractBlock.Settings.copy(Blocks.RAIL)
                            .noCollision()
                            .strength(0.7F)
                            .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                            .sounds(BlockSoundGroup.COPPER)
            ));
    public static final Block WEATHERED_INDUCTOR_RAIL = registerBlock("weathered_inductor_rail",
            new InductorRailBlock(Oxidizable.OxidationLevel.WEATHERED,
                    AbstractBlock.Settings.copy(Blocks.RAIL)
                            .noCollision()
                            .strength(0.7F)
                            .mapColor(MapColor.DARK_AQUA)
                            .sounds(BlockSoundGroup.COPPER)
            ));
    public static final Block OXIDIZED_INDUCTOR_RAIL = registerBlock("oxidized_inductor_rail",
            new InductorRailBlock(Oxidizable.OxidationLevel.OXIDIZED,
                    AbstractBlock.Settings.copy(Blocks.RAIL)
                            .noCollision()
                            .strength(0.7F)
                            .mapColor(MapColor.TEAL)
                            .sounds(BlockSoundGroup.COPPER)
            ));
    public static final Block WAXED_INDUCTOR_RAIL = registerBlock("waxed_inductor_rail",
            new InductorRailBlock(Oxidizable.OxidationLevel.UNAFFECTED,
                    AbstractBlock.Settings.copy(Blocks.RAIL)
                            .noCollision()
                            .strength(0.7F)
                            .mapColor(MapColor.ORANGE)
                            .sounds(BlockSoundGroup.COPPER)
            ));
    public static final Block WAXED_EXPOSED_INDUCTOR_RAIL = registerBlock("waxed_exposed_inductor_rail",
            new InductorRailBlock(Oxidizable.OxidationLevel.EXPOSED,
                    AbstractBlock.Settings.copy(Blocks.RAIL)
                            .noCollision()
                            .strength(0.7F)
                            .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                            .sounds(BlockSoundGroup.COPPER)
            ));
    public static final Block WAXED_WEATHERED_INDUCTOR_RAIL = registerBlock("waxed_weathered_inductor_rail",
            new InductorRailBlock(Oxidizable.OxidationLevel.WEATHERED,
                    AbstractBlock.Settings.copy(Blocks.RAIL)
                            .noCollision()
                            .strength(0.7F)
                            .mapColor(MapColor.DARK_AQUA)
                            .sounds(BlockSoundGroup.COPPER)
            ));
    public static final Block WAXED_OXIDIZED_INDUCTOR_RAIL = registerBlock("waxed_oxidized_inductor_rail",
            new InductorRailBlock(Oxidizable.OxidationLevel.OXIDIZED,
                    AbstractBlock.Settings.copy(Blocks.RAIL)
                            .noCollision()
                            .strength(0.7F)
                            .mapColor(MapColor.TEAL)
                            .sounds(BlockSoundGroup.COPPER)
            ));

    public static final Block NEW_CAULDRON_BLOCK = registerBlock("new_cauldron",
            new NewCauldronBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.STONE_GRAY)
                            .requiresTool()
                            .strength(2.0F)
                            .nonOpaque()
            ));

    public static final Block NEW_LOOM_BLOCK = registerBlock("new_loom",
            new NewLoomBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.OAK_TAN)
                            .instrument(NoteBlockInstrument.BASS)
                            .strength(2.5F)
                            .sounds(BlockSoundGroup.WOOD)
                            .burnable()
            ));

    public static void initialize() {
        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.INDUCTOR_RAIL, ModBlocks.EXPOSED_INDUCTOR_RAIL);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.EXPOSED_INDUCTOR_RAIL, ModBlocks.WEATHERED_INDUCTOR_RAIL);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.WEATHERED_INDUCTOR_RAIL, ModBlocks.OXIDIZED_INDUCTOR_RAIL);
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.INDUCTOR_RAIL, ModBlocks.WAXED_INDUCTOR_RAIL);
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.EXPOSED_INDUCTOR_RAIL, ModBlocks.WAXED_EXPOSED_INDUCTOR_RAIL);
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.WEATHERED_INDUCTOR_RAIL, ModBlocks.WAXED_WEATHERED_INDUCTOR_RAIL);
        OxidizableBlocksRegistry.registerWaxableBlockPair(ModBlocks.OXIDIZED_INDUCTOR_RAIL, ModBlocks.WAXED_OXIDIZED_INDUCTOR_RAIL);
    }

    public static void registerModBlocks(){
        Placeholder.LOGGER.info("Registering Mod Blocks for " + Placeholder.MOD_ID);
    }
}
