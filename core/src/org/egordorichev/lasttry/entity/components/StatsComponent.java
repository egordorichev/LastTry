package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.entity.Entity;

public class StatsComponent extends EntityComponent {
	private Entity entity;
	private int hp;
	private int maxHp;
	private int defense;
	private int damage;

	public StatsComponent(Entity entity) {
		this.entity = entity;
	}

	public void update(int dt) {
		// TODO: regen
	}

	public int set(int maxHp, int defense, int damage) {
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.defense = defense;
		this.damage = damage;
	}

	public int modifyHP(int amount) {
		this.hp = Math.max(0, Math.min(this.maxHp, this.hp + amount));
		return this.hp;
	}

	public int modifyMaxHP(int amount) {
		this.maxHp = Math.max(0, this.maxHp + amount);

		if (this.hp > this.maxHp) {
			this.hp = this.maxHp;
		}

		return this.maxHp;
	}

	public int modifyDefense(int amount) {
		this.defense = Math.max(0, this.defense + amount);
		return this.defense;
	}

	public int modifyDamage(int amount) {
		this.damage = Math.max(0, this.damage + amount);
		return this.damage;
	}

	public int getHp() {
		return this.hp;
	}

	public int getMaxHP() {
		return this.maxHp;
	}

	public int getDefense() {
		return this.defense;
	}

	public int getDamage() {
		return this.damage;
	}
}