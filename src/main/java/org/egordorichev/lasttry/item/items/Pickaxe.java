package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.item.Rarity;
import org.newdawn.slick.Image;

public class Pickaxe extends Tool {
	public Pickaxe(int id, String name, Rarity rarity, float baseDamage, int pickaxePower, int useSpeed, Image texture) {
		super(id, name, rarity, baseDamage, pickaxePower, 0, 0, useSpeed, texture);
	}

	public Pickaxe(int id, String name, float baseDamage, int pickaxePower, int useSpeed, Image texture) {
		this(id, name, Rarity.WHITE, baseDamage, pickaxePower, useSpeed, texture);
	}

	@Override
	public boolean use() {
		return false;
	}
}