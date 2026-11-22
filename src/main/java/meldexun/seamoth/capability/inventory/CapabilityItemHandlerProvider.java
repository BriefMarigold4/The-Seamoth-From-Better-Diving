package meldexun.seamoth.capability.inventory;

import meldexun.seamoth.Seamoth;
import meldexun.seamoth.capability.BasicCapabilityProviderSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class CapabilityItemHandlerProvider extends BasicCapabilityProviderSerializable<IItemHandler> {

	public static final ResourceLocation LOCATION_ITEM_STACK_HANDLER = new ResourceLocation(Seamoth.MOD_ID, "item_stack_handler");

	public CapabilityItemHandlerProvider(Capability<IItemHandler> capability, IItemHandler instance) {
		super(capability, instance);
	}

	public static CapabilityItemHandlerProvider createProvider(int slots) {
		return new CapabilityItemHandlerProvider(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, new ItemStackHandler(slots));
	}

}
