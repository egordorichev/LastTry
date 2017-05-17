package org.egordorichev.lasttry.effect;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.entity.Creature;

public  class Buff extends Effect {
    // TODO: Summons buffs

    public Buff(String name, String description, TextureRegion texture) {
        super(name, description, texture);
    }

    @Override
    public void apply(Creature creature) {

    }

    @Override
    public void remove(Creature creature) {

    }
}