package meldexun.seamoth.capability;

import meldexun.seamoth.Seamoth;
import meldexun.seamoth.capability.diving.CapabilityDivingAttributesProvider;
import meldexun.seamoth.capability.energy.CapabilityEnergyStorageProvider;
import meldexun.seamoth.capability.inventory.CapabilityItemHandlerProvider;
import meldexun.seamoth.capability.item.energy.CapabilityEnergyStorageItemProvider;
import meldexun.seamoth.capability.item.inventory.CapabilityItemHandlerItemProvider;
import meldexun.seamoth.entity.EntitySeamoth;
import meldexun.seamoth.item.ItemEnergyStorage;
import meldexun.seamoth.item.ItemSeamoth;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Seamoth.MOD_ID)
public class AttachCapabilitiesEventHandler {

	private AttachCapabilitiesEventHandler() {

	}

	@SubscribeEvent
	public static void onAttachCapabilitiesEntityEvent(AttachCapabilitiesEvent<Entity> event) {
		Entity entity = event.getObject();
		if (entity instanceof EntityPlayer) {
			event.addCapability(CapabilityDivingAttributesProvider.LOCATION_DIVING_ATTRIBUTES, CapabilityDivingAttributesProvider.createProvider((EntityPlayer) entity));
		} else if (entity instanceof EntitySeamoth) {
			event.addCapability(CapabilityItemHandlerProvider.LOCATION_ITEM_STACK_HANDLER, CapabilityItemHandlerProvider.createProvider(5));
		}
	}

	@SubscribeEvent
	public static void onAttachCapabilitiesItemStackEvent(AttachCapabilitiesEvent<ItemStack> event) {
		ItemStack stack = event.getObject();
		Item item = stack.getItem();
		if (item instanceof ItemEnergyStorage) {
			event.addCapability(CapabilityEnergyStorageProvider.LOCATION_ENERGY_STORAGE, CapabilityEnergyStorageItemProvider.createProvider(stack));
		} else if (item instanceof ItemSeamoth) {
			event.addCapability(CapabilityItemHandlerProvider.LOCATION_ITEM_STACK_HANDLER, CapabilityItemHandlerItemProvider.createProvider(stack, 5));
		}
	}

}
