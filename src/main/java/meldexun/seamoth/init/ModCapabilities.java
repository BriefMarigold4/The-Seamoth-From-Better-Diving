package meldexun.seamoth.init;

import meldexun.seamoth.capability.diving.CapabilityDivingAttributesProvider;

public class ModCapabilities {

	private ModCapabilities() {

	}

	public static void registerCapabilities() {
		CapabilityDivingAttributesProvider.register();
	}

}
