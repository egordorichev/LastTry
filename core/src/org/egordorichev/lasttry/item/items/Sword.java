package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.item.Rarity;

public class Sword extends MeleeWeapon {
    public Sword(short id, String name, Rarity rarity, float baseDamage, int useSpeed, Texture texture) {
        super(id, name, rarity, baseDamage, useSpeed, texture);
    }

    public Sword(short id, String name, float baseDamage, int useSpeed, Texture texture) {
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
        if (this.isReady() && this.isAutoSwing() && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            this.use();
        }

        if (Math.abs(0.0f - this.useDelay) > 0.05f) {
            // TODO: swing
        } else {
            this.useDelay = 0.0f;
        }
    }
}