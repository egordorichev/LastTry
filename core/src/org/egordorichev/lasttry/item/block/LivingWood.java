package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.item.items.ToolPower;

public class LivingWood extends Block {
	public LivingWood(short id, String name, int requiredPower, TextureRegion texture, TextureRegion tiles) {
		super(id, name, true, ToolPower.axe(requiredPower), texture, tiles);
	}

	public LivingWood(short id, String name, TextureRegion texture, TextureRegion tiles) {
		super(id, name, true, ToolPower.axe(10), texture, tiles);
	}

	@Override
	public boolean canBeUsed() {
		return false;
	}
}