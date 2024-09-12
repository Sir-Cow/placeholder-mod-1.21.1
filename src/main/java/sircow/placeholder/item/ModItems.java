package sircow.placeholder.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import sircow.placeholder.Placeholder;
import sircow.placeholder.block.ModBlocks;

public class ModItems {
    // items
    public static final Item INDUCTOR_RAIL = registerItem("inductor_rail", new BlockItem(ModBlocks.INDUCTOR_RAIL, new Item.Settings()));
    public static final Item EXPOSED_INDUCTOR_RAIL = registerItem("exposed_inductor_rail", new BlockItem(ModBlocks.EXPOSED_INDUCTOR_RAIL, new Item.Settings()));

    /*
    public static final Item TEST_SHIELD = registerItem("test_shield", new ShieldItem(new ShieldItem.Settings().maxDamage(2500)));
    // currently crashes on use, do not use until fix
    public static final Item TEST_SHIELD = Registry.register(Registries.ITEM, Identifier.of(Placeholder.MOD_ID, "test_shield"), new FabricShieldItem(new Item.Settings().maxDamage(336), 100, 9, Items.OAK_PLANKS, Items.SPRUCE_PLANKS));
    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
    }
    */

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Placeholder.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Placeholder.LOGGER.info("Registering Mod Items for " + Placeholder.MOD_ID);
    }
}