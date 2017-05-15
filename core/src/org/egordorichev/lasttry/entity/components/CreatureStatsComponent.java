package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.entity.Creature;

public class CreatureStatsComponent extends CreatureComponent {
	private int hp;
	private int maxHp;
	private int mana;
	private int maxMana;
	private int defense;
	private int damage;

	public CreatureStatsComponent(Creature creature) {
		super(creature);
	}

	public void update(int dt) {
		// TODO: regen
	}

	public void set(int maxHp, int maxMana, int defense, int damage) {
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.maxMana = maxMana;
		this.mana = maxMana;
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

	public int modifyMana(int amount) {
		this.mana = Math.max(0, Math.min(this.maxMana, this.mana + amount));
		return this.mana;
	}

	public int modifyMaxMana(int amount) {
		this.maxMana = Math.max(0, this.maxMana + amount);

		if (this.mana > this.maxMana) {
			this.maxMana = this.maxMana;
		}

		return this.maxMana;
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