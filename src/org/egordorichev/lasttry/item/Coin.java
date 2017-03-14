package org.egordorichev.lasttry.item;

import org.newdawn.slick.Image;

public class Coin extends Item {
	public Coin(int id, String name, Image texture) {
		super(id, name, Type.ITEM);

		this.texture = texture;
	}

	@Override
	public boolean canBeUsed() {
		return false;
	}
}