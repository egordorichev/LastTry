package org.egordorichev.lasttry.item.block;

public enum EffectiveToolType {
	PICKAXE, DRILL, AXE;

	public static EffectiveToolType lookup(short blockID) {
		if (blockID == 0){
			return null;
		}
		return PICKAXE;
	}
}
