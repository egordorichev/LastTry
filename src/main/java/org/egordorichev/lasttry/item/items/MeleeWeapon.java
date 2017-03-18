package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.item.DamageType;
import org.newdawn.slick.Image;

public class MeleeWeapon extends Weapon {
	public MeleeWeapon(int id, String name, Rarity rarity, float baseDamage, Image texture) {
		super(id, name, rarity, baseDamage, DamageType.MELEE, texture);
	}

	public MeleeWeapon(int id, String name, float baseDamage, Image texture) {
		this(id, name, Rarity.WHITE, baseDamage, texture);
	}
}