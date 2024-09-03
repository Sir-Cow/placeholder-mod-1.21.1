package sircow.placeholder;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sircow.placeholder.item.ModItemGroups;
import sircow.placeholder.item.ModItems;

public class Placeholder implements ModInitializer {
	public static final String MOD_ID = "placeholder";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		//ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
	}
}