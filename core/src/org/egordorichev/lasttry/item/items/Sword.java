package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.inventory.InventoryOwner;
import org.egordorichev.lasttry.item.block.Block;

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
	public boolean canBeUsed(short x, short y){
		return this.isReady() && this.isAutoSwing() && Gdx.input.isButtonPressed(Input.Buttons.LEFT);
	}
}