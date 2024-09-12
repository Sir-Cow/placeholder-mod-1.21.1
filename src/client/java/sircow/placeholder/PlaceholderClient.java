package sircow.placeholder;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import sircow.placeholder.block.ModBlocks;

public class PlaceholderClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.INDUCTOR_RAIL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXPOSED_INDUCTOR_RAIL, RenderLayer.getCutout());
	}
}