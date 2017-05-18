package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.Rarity;
import org.egordorichev.lasttry.item.block.Block;

public class ShortSword extends MeleeWeapon {
	public ShortSword(short id, String name, Rarity rarity, float baseDamage, int useSpeed, TextureRegion texture) {
		super(id, name, rarity, baseDamage, useSpeed, texture);
	}

	public ShortSword(short id, String name, float baseDamage, int useSpeed, TextureRegion texture) {
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

		float width = this.texture.getRegionWidth();
		float height = this.texture.getRegionHeight();
		float angle = -45f;

		if (Globals.player.physics.isFlipped()) {
			Graphics.batch.draw(this.texture, Globals.player.physics.getCenterX() - width,
				Globals.player.physics.getCenterY(), width, 0, width, height, 1.0f, 1.0f, -angle);
		} else {
			Graphics.batch.draw(this.texture, Globals.player.physics.getCenterX(),
				Globals.player.physics.getCenterY(), 0, 0, width, height, 1.0f, 1.0f, angle);
		}
	}
}