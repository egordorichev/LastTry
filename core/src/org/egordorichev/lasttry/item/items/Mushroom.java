package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.item.Item;

public class Mushroom extends Item { // TODO
	public Mushroom(short id, String name, Texture texture) {
		super(id, name, texture);
	}

	@Override
	public int getMaxInStack() {
		return 999;
	}
}
