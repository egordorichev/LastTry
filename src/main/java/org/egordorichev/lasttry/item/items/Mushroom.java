package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.item.Item;
import org.newdawn.slick.Image;

public class Mushroom extends Item { // TODO
	public Mushroom(short id, String name, Image texture) {
		super(id, name, texture);
	}

	@Override
	public int getMaxInStack() {
		return 999;
	}
}
