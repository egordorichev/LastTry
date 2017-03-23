package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Rarity;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class Sword extends MeleeWeapon {
	public Sword(short id, String name, Rarity rarity, float baseDamage, int useSpeed, Image texture) {
		super(id, name, rarity, baseDamage, useSpeed, texture);
	}

	public Sword(short id, String name, float baseDamage, int useSpeed, Image texture) {
		this(id, name, Rarity.WHITE, baseDamage, useSpeed, texture);
	}

	@Override
	public boolean use() {
		if (this.isReady()) {

		}

		return false;
	}

	@Override
	public void update(int dt) {
		if (this.isReady() && this.isAutoSwing() && LastTry.input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			this.use();
		}

		if (Math.abs(0.0f - this.useDelay) > 0.05f) {
			// TODO: swing
		} else {
			this.useDelay = 0.0f;
		}
	}
}