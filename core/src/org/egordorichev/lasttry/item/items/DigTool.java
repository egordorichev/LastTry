package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.block.helpers.BlockHelper;
import org.egordorichev.lasttry.util.ByteHelper;
import org.egordorichev.lasttry.util.Util;

public class DigTool extends Tool {
	public DigTool(String id) {
		super(id);
		this.autoSwing = true;
	}

	@Override
	protected boolean onUse() {
		int x = LastTry.getMouseXInWorld() / Block.SIZE;
		int y = LastTry.getMouseYInWorld() / Block.SIZE;

		Block block = Globals.getWorld().blocks.get(x, y);

		if (block == null) {
			return false;
		}

		ToolPower power = block.getRequiredPower();

		if (this.power.isEnoughFor(power)) {
			byte hp = Globals.getWorld().blocks.getHP(x, y);

			if (hp > 0) {
				Globals.getWorld().blocks.setHP(BlockHelper.plain.setHP(hp, (byte) (BlockHelper.plain.getHP(hp) - 1)), x, y);
			}
		}

		return false;
	}

	@Override
	public void renderAnimation() {
		if (this.isReady()) {
			return;
		}

		float width = this.texture.getRegionWidth();
		float height = this.texture.getRegionHeight();
		float angle = Util.map(this.useDelay, 0, this.useDelayMax, -70.0f, 45.0f);

		if (Globals.getPlayer().physics.isFlipped()) {
			Graphics.batch.draw(this.texture, Globals.getPlayer().physics.getCenterX(),
				Globals.getPlayer().physics.getCenterY(), 0, 0, width, height, -1.0f, 1.0f, -angle);
		} else {
			Graphics.batch.draw(this.texture, Globals.getPlayer().physics.getCenterX(),
				Globals.getPlayer().physics.getCenterY(), 0, 0, width, height, 1.0f, 1.0f, angle);
		}
	}
}