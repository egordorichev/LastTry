package org.egordorichev.lasttry.item.block;

/**
 * Enum for which tool to use for breaking a block.
 */
public enum EffectiveToolType {
	PICKAXE, DRILL, AXE;

	public static EffectiveToolType lookup(short blockID) {
		if (blockID == 0) {
			return null;
		}
		// TODO: A proper blockID to type lookup.
		return PICKAXE;
	}
}
