package meldexun.seamoth.network;

import meldexun.seamoth.client.gui.GuiSeamothContainer;
import meldexun.seamoth.container.ContainerSeamoth;
import meldexun.seamoth.entity.EntitySeamoth;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public static final int GUI_SEAMOTH_ENTITY = 0;
	public static final int GUI_SEAMOTH_ITEM = 1;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case GUI_SEAMOTH_ENTITY:
			return new ContainerSeamoth(player.inventory, (EntitySeamoth) world.getEntityByID(x));
		case GUI_SEAMOTH_ITEM:
			return new ContainerSeamoth(player.inventory, player.getHeldItem(EnumHand.values()[x]), EnumHand.values()[x]);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case GUI_SEAMOTH_ENTITY:
			return new GuiSeamothContainer((Container) this.getServerGuiElement(ID, player, world, x, y, z), (EntitySeamoth) world.getEntityByID(x));
		case GUI_SEAMOTH_ITEM:
			return new GuiSeamothContainer((Container) this.getServerGuiElement(ID, player, world, x, y, z), player.getHeldItem(EnumHand.values()[x]));
		default:
			return null;
		}
	}

}
