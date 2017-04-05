package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.Rarity;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Util;

public class Axe extends Tool {
	public Axe(short id, String name, Rarity rarity, float baseDamage, int power, int useSpeed, Texture texture) {
		super(id, name, rarity, baseDamage, ToolPower.axe(power), useSpeed, texture);
		this.autoSwing = true;
	}

	public Axe(short id, String name, float baseDamage, int power, int useSpeed, Texture texture) {
		this(id, name, Rarity.WHITE, baseDamage, power, useSpeed, texture);
	}

	@Override
	protected boolean onUse() {
		int x = LastTry.getMouseXInWorld() / Block.TEX_SIZE;
		int y = LastTry.getMouseYInWorld() / Block.TEX_SIZE;

		Block block = LastTry.world.getBlock(x, y);

		if (block == null) {
			return false;
		}

		ToolPower power = block.getRequiredPower();

		if (this.power.isEnoughFor(power)) {
			byte hp = LastTry.world.getBlockHp(x, y);

			if (hp > 1) {
				LastTry.world.setBlockHP((byte) (hp - 1), x, y);
			} else {
				LastTry.world.setBlock(ItemID.none, x, y);
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

		if (LastTry.player.isFlipped()) { // TODO: change the animation
			LastTry.batch.draw(this.texture, LastTry.player.getCenter().x, LastTry.world.getHeight() * Block.TEX_SIZE -
				LastTry.player.getCenter().y, 0, 0, width, height, 1.0f, 1.0f, angle, 0, 0, (int) width,
				(int) height, false, false);
		} else {
			LastTry.batch.draw(this.texture, LastTry.player.getCenter().x, LastTry.world.getHeight() * Block.TEX_SIZE -
				LastTry.player.getCenter().y, 0, 0, width, height, 1.0f, 1.0f, angle, 0, 0, (int) width,
				(int) height, false, false);
		}
	}
}