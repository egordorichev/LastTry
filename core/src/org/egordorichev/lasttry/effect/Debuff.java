package org.egordorichev.lasttry.effect;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.entity.Creature;

public abstract class Debuff extends Effect {
    // TODO: add debuffs

    public Debuff(String name, String description, Texture texture) {
        super(name, description, texture);

        this.canBeRemoved = false;
    }

    @Override
    public abstract void apply(Creature creature);

    @Override
    public abstract void remove(Creature creature);

    @Override
    public void update(Creature creature, int dt) {
        super.update(creature, dt);
    }
}