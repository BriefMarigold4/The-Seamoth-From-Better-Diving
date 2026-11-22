package meldexun.seamoth.client;

import meldexun.seamoth.Seamoth;
import meldexun.seamoth.client.renderer.entity.RenderSeamoth;
import meldexun.seamoth.entity.EntitySeamoth;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@EventBusSubscriber(modid = Seamoth.MOD_ID, value = Side.CLIENT)
public class EntityModels {

	private EntityModels() {

	}

	@SubscribeEvent
	public static void registerEntityRenders(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntitySeamoth.class, RenderSeamoth::new);
	}

}
