package org.egordorichev.lasttry.effect;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.entity.Creature;

public  class Buff extends Effect {

    // TODO: Summons buffs

    public Buff(String name, String description, Texture texture) {
        super(name, description, texture);
    }

    @Override
    public void apply(Creature creature) {}

    @Override
    public void remove(Creature creature) {}
}