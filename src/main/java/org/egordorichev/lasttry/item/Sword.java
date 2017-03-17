package org.egordorichev.lasttry.item;

import org.newdawn.slick.Image;

public class Sword extends Weapon { // TODO
	public Sword(int id, String name, Image texture, int damage) {
		super(id, name, texture, damage, DamageType.MELEE);
	}
}