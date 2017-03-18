package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.item.DamageType;
import org.newdawn.slick.Image;

public class Weapon extends Tool {
	protected DamageType damageType;
	protected int damage;

	public Weapon(int id, String name, Rarity rarity, float baseDamage, DamageType damageType, Image texture) {
		super(id, name, rarity, baseDamage, texture);

		this.damageType = damageType;
	}

	public Weapon(int id, String name, float baseDamage, DamageType damageType, Image texture) {
		this(id, name, Rarity.WHITE, baseDamage, damageType, texture);
	}

	public DamageType getDamageType() {
		return this.damageType;
	}

	public int getDamage() {
		return this.damage;
	}

	public boolean isMagic() {
		return this.damageType == DamageType.MAGIC;
	}

	public boolean isMelee() {
		return this.damageType == DamageType.MELEE;
	}

	public boolean isThrowing() {
		return this.damageType == DamageType.THROWING;
	}

	public boolean isRanged() {
		return this.damageType == DamageType.RANGED;
	}

	public boolean isSummoning() {
		return this.damageType == DamageType.SUMMON;
	}
}