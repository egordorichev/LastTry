package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.item.Item;
import org.newdawn.slick.*;

public class Dye extends Item {
	protected Color color;
	
	public Dye(short id, String name, Color color, Image texture) {
		super(id, name, texture);
	
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}	
}
