package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Rarity;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Util;

public class DigTool extends Tool {
	public DigTool(short id, String name, Rarity rarity, float baseDamage, ToolPower power, int useSpeed, Texture texture) {
		super(id, name, rarity, baseDamage, power, useSpeed, texture);
		this.autoSwing = true;
	}

	@Override
	protected boolean onUse() {
		int x = LastTry.getMouseXInWorld() / Block.SIZE;
		int y = LastTry.getMouseYInWorld() / Block.SIZE;

		Block block = LastTry.world.blocks.get(x, y);

		if (block == null) {
			return false;
		}

		ToolPower power = block.getRequiredPower();

		if (this.power.isEnoughFor(power)) {
			byte hp = LastTry.world.blocks.getHP(x, y);

			if (hp > 0) {
				LastTry.world.blocks.setHP((byte) (hp - 1), x, y);
			}
		}

		return false;
	}

	@Override
	public void renderAnimation() {
		if (this.isReady()) {
			return;
		}

		float width = this.texture.getWidth();
		float height = this.texture.getHeight();
		float angle = Util.map(this.useDelay, 0, this.useSpeed, -70.0f, 45.0f);

		if (LastTry.player.physics.isFlipped()) {
			LastTry.batch.draw(this.texture, LastTry.player.physics.getCenterX() - width, LastTry.world.getHeight() * Block.SIZE -
				LastTry.player.physics.getCenterY(), width, 0, width, height, 1.0f, 1.0f, -angle, 0, 0, (int) width,
				(int) height, true, false);
		} else {
			LastTry.batch.draw(this.texture, LastTry.player.physics.getCenterX(), LastTry.world.getHeight() * Block.SIZE -
				LastTry.player.physics.getCenterY(), 0, 0, width, height, 1.0f, 1.0f, angle, 0, 0, (int) width,
				(int) height, false, false);
		}
	}
}