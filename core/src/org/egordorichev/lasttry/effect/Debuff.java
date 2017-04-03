package org.egordorichev.lasttry.effect;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.entity.Entity;

public abstract class Debuff extends Effect {
    // TODO: add debuffs

    public Debuff(String name, String description, Texture texture) {
        super(name, description, texture);

        this.canBeRemoved = false;
    }

    /**
     * Abstact method, called on effect apply
     *
     * @param entity PhysicBody, on witch it is applied
     */
    @Override
    public abstract void apply(Entity entity);

    /**
     * Abstact method, called on effect remove
     *
     * @param entity PhysicBody, from witch it is removed
     */
    @Override
    public abstract void remove(Entity entity);

    /**
     * Used by some debuff, like poison
     *
     * @param dt The milliseconds passed since the last update.
     */
    @Override
    public void update(Entity entity, int dt) {
        super.update(entity, dt);
    }
}