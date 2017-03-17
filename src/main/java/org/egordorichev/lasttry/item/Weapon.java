package org.egordorichev.lasttry.item;

import org.newdawn.slick.Image;

public class Weapon extends Item {
	protected DamageType damageType;
	protected int damage;

	public Weapon(int id, String name, Image texture, int damage, DamageType damageType) {
		super(id, name, texture);

		this.damage = damage;
		this.damageType = damageType;
	}
}