package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.item.items.ToolPower;

public class Wood extends BlockGround {
	public Wood(short id, String name, int requiredPower, TextureRegion texture, TextureRegion tiles) {
		super(id, name, ToolPower.pickaxe(requiredPower), texture, tiles);
	}

	public Wood(short id, String name, TextureRegion texture, TextureRegion tiles) {
		super(id, name, ToolPower.pickaxe(10), texture, tiles);
	}
}