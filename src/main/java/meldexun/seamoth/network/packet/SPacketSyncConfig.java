package meldexun.seamoth.network.packet;

import io.netty.buffer.ByteBuf;
import meldexun.seamoth.util.SeamothConfig;
import meldexun.seamoth.util.ByteBufHelper;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class SPacketSyncConfig implements IMessage {

	@Override
	public void fromBytes(ByteBuf buf) {
		// ByteBufHelper.copy(SeamothConfig.MASTER_CONFIG, SeamothConfig.SLAVE_CONFIG, false);
		ByteBufHelper.fromBytes(SeamothConfig.SLAVE_CONFIG, buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufHelper.toBytes(SeamothConfig.SLAVE_CONFIG, buf);
	}

}
