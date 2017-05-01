package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.Rarity;
import org.egordorichev.lasttry.item.block.Block;

public class ShortSword extends MeleeWeapon {
	public ShortSword(short id, String name, Rarity rarity, float baseDamage, int useSpeed, Texture texture) {
		super(id, name, rarity, baseDamage, useSpeed, texture);
	}

	public ShortSword(short id, String name, float baseDamage, int useSpeed, Texture texture) {
		super(id, name, baseDamage, useSpeed, texture);
	}

	@Override
	protected boolean onUse() {
		return false;
	}

	@Override
	public void renderAnimation() {
		if (this.isReady()) {
			return;
		}

		float width = this.texture.getWidth();
		float height = this.texture.getHeight();
		float angle = -45f;

		if (Globals.player.physics.isFlipped()) {
			Graphics.batch.draw(this.texture, Globals.player.physics.getCenterX() - width, Globals.world.getHeight() * Block.SIZE -
				Globals.player.physics.getCenterY(), width, 0, width, height, 1.0f, 1.0f, -angle, 0, 0, (int) width,
				(int) height, true, false);
		} else {
			Graphics.batch.draw(this.texture, Globals.player.physics.getCenterX(), Globals.world.getHeight() * Block.SIZE -
				Globals.player.physics.getCenterY(), 0, 0, width, height, 1.0f, 1.0f, angle, 0, 0, (int) width,
				(int) height, false, false);
		}
	}
}