package sircow.placeholder.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import sircow.placeholder.Placeholder;

public class ModItems {
    // items
    public static final Item RAW_HIDE = registerItem("raw_hide", new Item(new Item.Settings()));
    public static final Item PHANTOM_SINEW = registerItem("phantom_sinew", new Item(new Item.Settings()));
    public static final Item HOLLOW_TWINE = registerItem("hollow_twine", new Item(new Item.Settings()));
    public static final Item BLACK_CLOTH = registerItem("black_cloth", new Item(new Item.Settings()));
    public static final Item BLUE_CLOTH = registerItem("blue_cloth", new Item(new Item.Settings()));
    public static final Item BROWN_CLOTH = registerItem("brown_cloth", new Item(new Item.Settings()));
    public static final Item CYAN_CLOTH = registerItem("cyan_cloth", new Item(new Item.Settings()));
    public static final Item GRAY_CLOTH = registerItem("gray_cloth", new Item(new Item.Settings()));
    public static final Item GREEN_CLOTH = registerItem("green_cloth", new Item(new Item.Settings()));
    public static final Item LIGHT_BLUE_CLOTH = registerItem("light_blue_cloth", new Item(new Item.Settings()));
    public static final Item LIGHT_GRAY_CLOTH = registerItem("light_gray_cloth", new Item(new Item.Settings()));
    public static final Item LIME_CLOTH = registerItem("lime_cloth", new Item(new Item.Settings()));
    public static final Item MAGENTA_CLOTH = registerItem("magenta_cloth", new Item(new Item.Settings()));
    public static final Item ORANGE_CLOTH = registerItem("orange_cloth", new Item(new Item.Settings()));
    public static final Item PINK_CLOTH = registerItem("pink_cloth", new Item(new Item.Settings()));
    public static final Item PURPLE_CLOTH = registerItem("purple_cloth", new Item(new Item.Settings()));
    public static final Item RED_CLOTH = registerItem("red_cloth", new Item(new Item.Settings()));
    public static final Item WHITE_CLOTH = registerItem("white_cloth", new Item(new Item.Settings()));
    public static final Item YELLOW_CLOTH = registerItem("yellow_cloth", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Placeholder.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Placeholder.LOGGER.info("Registering Mod Items for " + Placeholder.MOD_ID);
    }
}
