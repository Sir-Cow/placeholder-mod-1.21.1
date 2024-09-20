package sircow.placeholder.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import sircow.placeholder.Placeholder;
import sircow.placeholder.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup PLACEHOLDER_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Placeholder.MOD_ID, "placeholder"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.placeholder.placeholder_items"))
                    .icon(() -> new ItemStack(ModBlocks.INDUCTOR_RAIL)).entries((displayContext, entries) -> {
                        entries.add(ModItems.RAW_HIDE);
                        entries.add(ModBlocks.INDUCTOR_RAIL);
                        entries.add(ModBlocks.EXPOSED_INDUCTOR_RAIL);
                        entries.add(ModBlocks.WEATHERED_INDUCTOR_RAIL);
                        entries.add(ModBlocks.OXIDIZED_INDUCTOR_RAIL);
                        entries.add(ModBlocks.WAXED_INDUCTOR_RAIL);
                        entries.add(ModBlocks.WAXED_EXPOSED_INDUCTOR_RAIL);
                        entries.add(ModBlocks.WAXED_WEATHERED_INDUCTOR_RAIL);
                        entries.add(ModBlocks.WAXED_OXIDIZED_INDUCTOR_RAIL);
                    }).build());

    public static void registerItemGroups() {
        Placeholder.LOGGER.info("Registering Mod Item Groups for " + Placeholder.MOD_ID);
    }
}
