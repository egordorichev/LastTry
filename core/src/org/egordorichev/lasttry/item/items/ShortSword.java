package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Graphics;

public class ShortSword extends MeleeWeapon {
	public ShortSword(String id) {
		super(id);
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

		if (Globals.getPlayer().physics.isFlipped()) {
			Graphics.batch.draw(this.texture, Globals.getPlayer().physics.getCenterX(),
				Globals.getPlayer().physics.getCenterY(), 0, 0, width, height, 1.0f, 1.0f, 135f);
		} else {
			Graphics.batch.draw(this.texture, Globals.getPlayer().physics.getCenterX(),
				Globals.getPlayer().physics.getCenterY(), 0, 0, width, height, 1.0f, 1.0f, -45f);
		}
	}
}