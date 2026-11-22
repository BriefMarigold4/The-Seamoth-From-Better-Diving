package meldexun.seamoth.capability.diving;

public interface ICapabilityDivingAttributes {

	void tick();

	void updateSize();

	/**
	 * Only used when reading the capability from NBT or when receiving oxygen sync
	 * packets.
	 * 
	 * @param oxygen
	 */
	void setOxygen(int oxygen);

	/**
	 * Returns the amount of oxygen that the player has. <br>
	 * This does not take any gear into account. <br>
	 * See {@link #getOxygenFromPlayer()}
	 * 
	 * @return
	 */
	int getOxygen();

	boolean isDiving();
}
