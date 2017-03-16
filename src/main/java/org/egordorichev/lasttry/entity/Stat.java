package org.egordorichev.lasttry.entity;

public class Stat {
	public enum Type {
		DEFENSE,
		MANA,
		CRITICAL_STRIKE_CHANCE,
		DAMAGE,
		MOVEMENT_SPEED,
		MELEE_SPEED,
		KNOCKBACK,
		SIZE,
		SPEED,
		VELOCITY,
		MANA_COST
	}

	public Type type;
	public float value;

	public Stat(Type type, float value) {
		this.type = type;
		this.value = value;
	}
}