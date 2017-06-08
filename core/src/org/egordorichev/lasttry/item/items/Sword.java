package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.egordorichev.lasttry.inventory.InventoryOwner;

public class Sword extends MeleeWeapon {
    public Sword(String id) {
        super(id);
    }

    @Override
    public boolean use() {
        if (this.isReady()) {
            // TODO
        }

        return false;
    }
    
    @Override
    public boolean canBeUsed(){
    	return this.isReady() && this.isAutoSwing() && Gdx.input.isButtonPressed(Input.Buttons.LEFT);
    }

    @Override
    public void update(InventoryOwner<?> owner, int dt) {
        if (canBeUsed()) {
            this.use();
        }

        if (Math.abs(0.0f - this.useDelay) > 0.05f) {
            // TODO: swing
        } else {
            this.useDelay = 0.0f;
        }
    }
}