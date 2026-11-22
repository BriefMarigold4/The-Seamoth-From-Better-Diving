package meldexun.seamoth.client.gui.event;

import meldexun.seamoth.Seamoth;
import meldexun.seamoth.client.gui.GuiSeamoth;
import meldexun.seamoth.entity.EntitySeamoth;
import meldexun.seamoth.util.SeamothConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@EventBusSubscriber(modid = Seamoth.MOD_ID, value = Side.CLIENT)
public class GuiEventHandler {

	private GuiEventHandler() {

	}

	private static final GuiSeamoth GUI_SEAMOTH = new GuiSeamoth();

	@SubscribeEvent
	public static void onRenderGameOverlayEventPost(RenderGameOverlayEvent.Post event) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		SeamothConfig config = SeamothConfig.getInstance();

		if (event.getType() == ElementType.ALL) {
			if (player.getRidingEntity() instanceof EntitySeamoth && config.client.guiSeamothConfig.enabled) {
				GuiEventHandler.GUI_SEAMOTH.render();
			}
		}
	}
}
