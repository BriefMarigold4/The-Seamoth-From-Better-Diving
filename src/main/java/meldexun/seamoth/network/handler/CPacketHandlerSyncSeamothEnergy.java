package meldexun.seamoth.network.handler;

import meldexun.seamoth.Seamoth;
import meldexun.seamoth.entity.EntitySeamoth;
import meldexun.seamoth.network.packet.SPacketSyncSeamothEnergy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CPacketHandlerSyncSeamothEnergy implements IMessageHandler<SPacketSyncSeamothEnergy, IMessage> {

	@Override
	public IMessage onMessage(SPacketSyncSeamothEnergy message, MessageContext ctx) {
		FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
			if (ctx.side.isClient()) {
				EntityPlayer player = Seamoth.proxy.getPlayer(ctx);
				if (player.getRidingEntity() instanceof EntitySeamoth) {
					EntitySeamoth seamoth = (EntitySeamoth) player.getRidingEntity();

					seamoth.setEnergy(message.getEnergy());
				}
			}
		});
		return null;
	}

}
