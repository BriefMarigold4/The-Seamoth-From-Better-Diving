package meldexun.seamoth.util;

import meldexun.seamoth.Seamoth;
import meldexun.seamoth.network.packet.SPacketSyncConfig;
import meldexun.seamoth.util.config.GuiConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

@Config(modid = Seamoth.MOD_ID)
public class SeamothConfig {

	/** Use {@link #getInstance()} instead. */
	@Config.LangKey("config.seamoth")
	@Config.Comment("")
	public static final SeamothConfig MASTER_CONFIG = new SeamothConfig();

	/** Use {@link #getInstance()} instead. */
	@Config.Ignore
	public static final SeamothConfig SLAVE_CONFIG = new SeamothConfig();

	@ByteBufHelper.Sync
	@Config.LangKey("config.category_general")
	@Config.Comment("")
	public final General general = new General();

	@ByteBufHelper.Sync
	@Config.LangKey("config.category_diving_values")
	@Config.Comment("")
	public final DivingValues divingValues = new DivingValues();

	@Config.LangKey("config.category_client_settings")
	@Config.Comment("")
	public final Client client = new Client();

	public static SeamothConfig getInstance() {
		return SeamothConfig.SLAVE_CONFIG;
	}

	public static class General {
		@ByteBufHelper.Sync
		@Config.Comment("Enable/Disable packets to sync seamoth energy. Should prevent desync of client and server. But increases network traffic and comes with a latency. Not recommended unless you have experienced desync.")
		public boolean seamothEnergySyncPackets = false;
	}

	public static class DivingValues {
		@ByteBufHelper.Sync
		@Config.Comment("Amount of energy the seamoth uses per tick (20 ticks = 1 second)")
		@Config.RangeInt(min = 0, max = 1000)
		public int seamothEnergyUsage = 130;
		@ByteBufHelper.Sync
		@Config.Comment("Speed of the seamoth. (blocks per second = x * 100 * 20 / 5)")
		@Config.RangeDouble(min = 0.0D, max = 1.0D)
		public double seamothSpeed = 0.0275D;
	}

	public static class Client {
		@Config.LangKey("config.gui_seamoth")
		@Config.Comment("")
		public GuiConfig guiSeamothConfig = new GuiConfig(true, 3, -1, -1);
	}

	@EventBusSubscriber(modid = Seamoth.MOD_ID)
	private static class EventHandler {

		@SubscribeEvent
		public static void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(Seamoth.MOD_ID)) {
				ConfigManager.sync(Seamoth.MOD_ID, Config.Type.INSTANCE);
				Minecraft mc = Minecraft.getMinecraft();
				ByteBufHelper.copy(SeamothConfig.MASTER_CONFIG, SeamothConfig.SLAVE_CONFIG, mc.world == null || mc.isSingleplayer());
			}
		}

		@SubscribeEvent
		public static void onPlayerLoggedInEvent(PlayerLoggedInEvent event) {
			if (!event.player.world.isRemote) {
				EntityPlayer player = event.player;

				Seamoth.network.sendTo(new SPacketSyncConfig(), (EntityPlayerMP) player);
			}
		}

	}

}
