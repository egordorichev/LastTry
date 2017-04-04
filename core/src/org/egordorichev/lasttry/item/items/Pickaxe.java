package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Rarity;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Util;

public class Pickaxe extends Tool {
    public Pickaxe(short id, String name, Rarity rarity, float baseDamage, int pickaxePower, int useSpeed, Texture texture) {
        super(id, name, rarity, baseDamage, ToolPower.pickaxe(pickaxePower), useSpeed, texture);
    }

    public Pickaxe(short id, String name, float baseDamage, int pickaxePower, int useSpeed, Texture texture) {
        this(id, name, Rarity.WHITE, baseDamage, pickaxePower, useSpeed, texture);
    }

	@Override
	protected boolean onUse() {
		return false;
	}

	@Override
	protected void onUpdate() {

	}

	@Override
	protected boolean onUseEnd() {
		LastTry.world.setBlock((short) 0, LastTry.getMouseXInWorld() / Block.TEX_SIZE, LastTry.getMouseYInWorld() / Block.TEX_SIZE);

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