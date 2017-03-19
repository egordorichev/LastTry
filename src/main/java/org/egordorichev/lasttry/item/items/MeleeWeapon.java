package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.item.DamageType;
import org.egordorichev.lasttry.item.Rarity;
import org.newdawn.slick.Image;

public class MeleeWeapon extends Weapon {
	public MeleeWeapon(int id, String name, Rarity rarity, float baseDamage, int useSpeed, Image texture) {
		super(id, name, rarity, baseDamage, DamageType.MELEE, useSpeed, texture);
	}

	public MeleeWeapon(int id, String name, float baseDamage, int useSpeed, Image texture) {
		this(id, name, Rarity.WHITE, baseDamage, useSpeed, texture);
	}
}