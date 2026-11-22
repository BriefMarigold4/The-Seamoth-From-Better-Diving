package meldexun.seamoth;

import org.apache.logging.log4j.Logger;

import meldexun.seamoth.init.ModCapabilities;
import meldexun.seamoth.init.ModItems;
import meldexun.seamoth.init.ModPackets;
import meldexun.seamoth.network.GuiHandler;
import meldexun.seamoth.proxy.IProxy;
import meldexun.seamoth.util.SeamothConfig;
import meldexun.seamoth.util.ByteBufHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid = Seamoth.MOD_ID, name = Seamoth.NAME, version = Seamoth.VERSION, acceptedMinecraftVersions = Seamoth.ACCEPTED_VERSIONS)
public class Seamoth {

	public static final String MOD_ID = "seamoth";
	public static final String NAME = "The Seamoth From Better Diving";
	public static final String VERSION = "1.0.0";
	public static final String ACCEPTED_VERSIONS = "[1.12.2]";

	public static final String CLIENT_PROXY_CLASS = "meldexun.seamoth.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "meldexun.seamoth.proxy.ServerProxy";

	public static final CreativeTabs TAB_BETTER_DIVING = new CreativeTabs("tab_seamoth") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModItems.SEAMOTH);
		}
	};

	@Mod.Instance(Seamoth.MOD_ID)
	public static Seamoth instance;

	@SidedProxy(clientSide = Seamoth.CLIENT_PROXY_CLASS, serverSide = Seamoth.SERVER_PROXY_CLASS)
	public static IProxy proxy;

	public static Logger logger;

	public static SimpleNetworkWrapper network;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Seamoth.logger = event.getModLog();
		Seamoth.network = NetworkRegistry.INSTANCE.newSimpleChannel(Seamoth.MOD_ID);

		ByteBufHelper.copy(SeamothConfig.MASTER_CONFIG, SeamothConfig.SLAVE_CONFIG, true);

		Seamoth.proxy.preInit();

		ModCapabilities.registerCapabilities();
		ModPackets.registerMessages();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		Seamoth.proxy.init();

		NetworkRegistry.INSTANCE.registerGuiHandler(Seamoth.MOD_ID, new GuiHandler());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		Seamoth.proxy.postInit();
	}

}
