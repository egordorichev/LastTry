package org.egordorichev.lasttry.item;

/**
 * Weapon damage type
 */
public enum DamageType {
	MELEE("Melee"),
	RANGED("Randged"),
	MAGIC("Magic"),
	SUMMON("Summon"),
	THROWING("Throwing");

	/**
	 * Damage type name
	 */
	private String name;

	DamageType(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
}