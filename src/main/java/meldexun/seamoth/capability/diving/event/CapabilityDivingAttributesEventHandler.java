package meldexun.seamoth.capability.diving.event;

import meldexun.seamoth.Seamoth;
import meldexun.seamoth.capability.diving.CapabilityDivingAttributesProvider;
import meldexun.seamoth.capability.diving.ICapabilityDivingAttributes;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

@EventBusSubscriber(modid = Seamoth.MOD_ID)
public class CapabilityDivingAttributesEventHandler {

	private CapabilityDivingAttributesEventHandler() {

	}

	@SubscribeEvent(priority = EventPriority.LOW)
	public static void onPlayerTickEvent(TickEvent.PlayerTickEvent event) {
		ICapabilityDivingAttributes idiving = event.player.getCapability(CapabilityDivingAttributesProvider.DIVING_ATTRIBUTES, null);

		if (event.phase == Phase.END && (!event.player.world.isRemote || !(event.player instanceof EntityOtherPlayerMP))) {
			idiving.tick();
		}

		idiving.updateSize();
	}

}
