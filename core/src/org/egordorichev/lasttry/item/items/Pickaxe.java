package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.item.Rarity;

public class Pickaxe extends Tool {
	public Pickaxe(short id, String name, Rarity rarity, float baseDamage, int pickaxePower, int useSpeed, Texture texture) {
		super(id, name, rarity, baseDamage, pickaxePower, 0, 0, useSpeed, texture);
	}

	public Pickaxe(short id, String name, float baseDamage, int pickaxePower, int useSpeed, Texture texture) {
		this(id, name, Rarity.WHITE, baseDamage, pickaxePower, useSpeed, texture);
	}

	@Override
	public boolean use() {
		return false;
	}
}