package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.item.DamageType;
import org.newdawn.slick.Image;

public class Weapon extends Tool {
	protected DamageType damageType;
	protected int damage;

	public Weapon(int id, String name, Image texture, int damage, DamageType damageType) {
		super(id, name, texture);

		this.damage = damage;
		this.damageType = damageType;
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