package meldexun.seamoth.init;

import meldexun.seamoth.Seamoth;
import meldexun.seamoth.network.handler.CPacketHandlerSyncConfig;
import meldexun.seamoth.network.handler.CPacketHandlerSyncSeamothBattery;
import meldexun.seamoth.network.handler.CPacketHandlerSyncSeamothEnergy;
import meldexun.seamoth.network.handler.CPacketHandlerSyncSeamothInput;
import meldexun.seamoth.network.handler.SPacketHandlerSyncSeamothInput;
import meldexun.seamoth.network.packet.CPacketSyncSeamothInput;
import meldexun.seamoth.network.packet.SPacketSyncConfig;
import meldexun.seamoth.network.packet.SPacketSyncSeamothBattery;
import meldexun.seamoth.network.packet.SPacketSyncSeamothEnergy;
import meldexun.seamoth.network.packet.SPacketSyncSeamothInput;
import net.minecraftforge.fml.relauncher.Side;

public class ModPackets {

	// Start the IDs at 1 so any unregistered messages (ID 0) throw a more obvious
	// exception when received
	private static int messageID = 1;

	private ModPackets() {

	}

	public static void registerMessages() {
		Seamoth.network.registerMessage(CPacketHandlerSyncConfig.class, SPacketSyncConfig.class, ModPackets.messageID++, Side.CLIENT);
		Seamoth.network.registerMessage(CPacketHandlerSyncSeamothEnergy.class, SPacketSyncSeamothEnergy.class, ModPackets.messageID++, Side.CLIENT);
		Seamoth.network.registerMessage(CPacketHandlerSyncSeamothBattery.class, SPacketSyncSeamothBattery.class, ModPackets.messageID++, Side.CLIENT);
		Seamoth.network.registerMessage(CPacketHandlerSyncSeamothInput.class, SPacketSyncSeamothInput.class, ModPackets.messageID++, Side.CLIENT);

		Seamoth.network.registerMessage(SPacketHandlerSyncSeamothInput.class, CPacketSyncSeamothInput.class, ModPackets.messageID++, Side.SERVER);
	}

}
