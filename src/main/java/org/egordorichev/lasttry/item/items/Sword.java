package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.LastTry;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class Sword extends MeleeWeapon {
	public Sword(int id, String name, Image texture, float baseDamage) {
		super(id, name, baseDamage, texture);
	}

	@Override
	public void use() {
		if(this.isReady()) {

		}
	}

	@Override
	public void update(int dt) {
		if(this.isReady() && this.isAutoSwing() && LastTry.input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			this.use();
		}

		if(Math.abs(0.0f - this.useDelay) > 0.05f) {
			// TODO: swing
		} else {
			this.useDelay = 0.0f;
		}
	}
}