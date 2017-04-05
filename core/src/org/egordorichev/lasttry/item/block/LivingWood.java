package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.item.items.ToolPower;

public class LivingWood extends Block {
	public LivingWood(short id, String name, int requiredPower, Texture texture, Texture tiles) {
		super(id, name, true, ToolPower.axe(requiredPower), texture, tiles);
	}

	public LivingWood(short id, String name, Texture texture, Texture tiles) {
		super(id, name, true, ToolPower.axe(10), texture, tiles);
	}

	@Override
	public boolean canBeUsed() {
		return false;
	}
}