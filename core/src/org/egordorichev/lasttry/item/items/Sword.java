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
		if (this.isReady()) {
			// TODO
		}

		return false;
	}

	@Override
	public boolean canBeUsed(short x, short y){
		return this.isReady() && this.isAutoSwing() && Gdx.input.isButtonPressed(Input.Buttons.LEFT);
	}

	@Override
	public void update(InventoryOwner<?> owner, int dt) {
		short x = (short) (LastTry.getMouseXInWorld() / Block.SIZE);
		short y = (short) (LastTry.getMouseYInWorld() / Block.SIZE);

		if (canBeUsed(x, y)) {
			this.use(x, y);
		}

		if (Math.abs(0.0f - this.useDelay) > 0.05f) {
			// TODO: swing
		} else {
			this.useDelay = 0.0f;
		}
	}
}