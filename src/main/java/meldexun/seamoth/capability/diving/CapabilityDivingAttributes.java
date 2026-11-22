package meldexun.seamoth.capability.diving;

import meldexun.seamoth.entity.EntitySeamoth;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;

public class CapabilityDivingAttributes implements ICapabilityDivingAttributes {

	private final EntityPlayer player;

	private boolean isDiving = false;

	private boolean isInSeamoth;
	private boolean isPrevInSeamoth;

	public CapabilityDivingAttributes() {
		this(null);
	}

	public CapabilityDivingAttributes(EntityPlayer player) {
		this.player = player;
	}

	@Override
	public void tick() {
		this.isPrevInSeamoth = this.isInSeamoth;
		this.isInSeamoth = this.player.getRidingEntity() instanceof EntitySeamoth;

		if (!this.player.world.isRemote) {
			this.handleOxygen();
		}
	}

	@Override
	public void updateSize() {

	}

	@Override
	public void setOxygen(int oxygen) {

	}

	@Override
	public int getOxygen() {
		return 0;
	}

	protected void handleOxygen() {
		EntitySeamoth seamoth = (EntitySeamoth) player.getRidingEntity();
		if (this.isInSeamoth && !this.player.world.isRemote && seamoth.getEnergy() > 0 && this.player.isInsideOfMaterial(Material.WATER)) {
			int air = this.player.getAir();

			if (air < 300) {
				this.player.setAir(Math.min(air + 4, 300));
			}
		}
	}

	@Override
	public boolean isDiving() {
		return this.isDiving;
	}

}
