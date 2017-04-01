package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.Rarity;
import org.egordorichev.lasttry.item.block.Block;

public class Plant extends Block {
	public Plant(short id, String name, Texture texture) {
		super(id, name, Rarity.WHITE, texture);
	}


}