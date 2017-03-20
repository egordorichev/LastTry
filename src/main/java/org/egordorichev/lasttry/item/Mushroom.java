package org.egordorichev.lasttry.item;

import org.newdawn.slick.Image;

public class Mushroom extends Item { // TODO
	public Mushroom(int id, String name, Image texture) {
		super(id, name, texture);
	}

	@Override
	public int getMaxInStack() {
		return 999;
	}
}
