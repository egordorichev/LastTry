package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.item.Item;

public class Coin extends Item {
	public Coin(short id, String name, Texture texture) {
		super(id, name, texture);
	}

	@Override
	public boolean canBeUsed() {
		return false;
	}

	@Override
	public int getMaxInStack() {
		return 100;
	}
}