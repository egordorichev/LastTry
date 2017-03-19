package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.item.DamageType;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.Rarity;
import org.newdawn.slick.Image;

public class Weapon extends Item {
	protected DamageType damageType;
	protected float baseDamage;
	protected float useDelay;
	protected int useSpeed;
	protected boolean autoSwing;

	public Weapon(int id, String name, Rarity rarity, float baseDamage, DamageType damageType, int useSpeed, Image texture) {
		super(id, name, rarity, texture);

		this.baseDamage = baseDamage;
		this.damageType = damageType;
		this.autoSwing = false;
		this.useSpeed = useSpeed;
		this.useDelay = 0;
	}

	public Weapon(int id, String name, float baseDamage, DamageType damageType, int useSpeed, Image texture) {
		this(id, name, Rarity.WHITE, baseDamage, damageType, useSpeed, texture);
	}

	public DamageType getDamageType() {
		return this.damageType;
	}

	public float getBaseDamage() {
		return this.baseDamage;
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

	public boolean isAutoSwing() {
		return this.autoSwing;
	}

	public boolean isReady() {
		return Math.abs(0.0f - this.useDelay) < 0.05f;
	}
}