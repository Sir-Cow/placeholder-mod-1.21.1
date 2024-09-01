package sircow.placeholder.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import sircow.placeholder.Placeholder;

public class ModItems {
    // test items
    public static final Item TEST = registerItem("test", new Item(new Item.Settings()));
    // public static final Item TEST_SHIELD = registerItem("test_shield", new ShieldItem(new ShieldItem.Settings().maxDamage(2500)));

    // currently crashes on use, do not use until fix
    // public static final Item TEST_SHIELD = Registry.register(Registries.ITEM, Identifier.of(PlaceholderMod.MOD_ID, "test_shield"), new FabricShieldItem(new Item.Settings().maxDamage(336), 100, 9, Items.OAK_PLANKS, Items.SPRUCE_PLANKS));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {

    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Placeholder.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Placeholder.LOGGER.info("Registering Mod Items for " + Placeholder.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
