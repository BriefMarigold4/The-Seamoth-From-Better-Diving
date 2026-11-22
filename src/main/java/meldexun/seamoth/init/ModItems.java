package meldexun.seamoth.init;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import meldexun.seamoth.Seamoth;
import meldexun.seamoth.item.ItemPowerCell;
import meldexun.seamoth.item.ItemSeamoth;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

@ObjectHolder(Seamoth.MOD_ID)
public class ModItems {

	public static final ItemPowerCell POWER_CELL = null;

	public static final ItemSeamoth SEAMOTH = null;

	private ModItems() {

	}

	@EventBusSubscriber(modid = Seamoth.MOD_ID)
	public static class ItemRegistrationHandler {

		public static final List<Item> ITEMS = new ArrayList<>();

		private ItemRegistrationHandler() {

		}

		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			final Item[] items = {
					ItemRegistrationHandler.setItemName(new ItemPowerCell(2000000, 125, 1000), "power_cell"),

					ItemRegistrationHandler.setItemName(new ItemSeamoth(), "seamoth")};

			IForgeRegistry<Item> registry = event.getRegistry();

			for (Item item : items) {
				registry.register(item);
				ItemRegistrationHandler.ITEMS.add(item);
			}
		}

		private static Item setItemName(Item item, String name) {
			return ItemRegistrationHandler.setItemNameAndTab(item, name, Seamoth.TAB_BETTER_DIVING);
		}

		private static Item setItemNameAndTab(Item item, String name, @Nullable CreativeTabs tab) {
			return item.setRegistryName(Seamoth.MOD_ID, name).setTranslationKey(name).setCreativeTab(tab);
		}
	}

}
