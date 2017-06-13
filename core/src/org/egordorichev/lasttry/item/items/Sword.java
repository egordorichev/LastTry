package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Sword extends MeleeWeapon {
	public Sword(String id) {
		super(id);
	}

	@Override
	public boolean use(short x, short y) {
		super.use(x, y);
		return false;
	}

	@Override
	public boolean canBeUsed(short x, short y) {
		return this.isReady() && this.isAutoSwing() && Gdx.input.isButtonPressed(Input.Buttons.LEFT);
	}
}