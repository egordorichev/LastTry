package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.item.Rarity;

public class ShortSword extends MeleeWeapon {
	public ShortSword(short id, String name, Rarity rarity, float baseDamage, int useSpeed, Texture texture) {
		super(id, name, rarity, baseDamage, useSpeed, texture);
	}

	public ShortSword(short id, String name, float baseDamage, int useSpeed, Texture texture) {
		super(id, name, baseDamage, useSpeed, texture);
	}
}