package org.egordorichev.lasttry.effect;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.Entity;

public abstract class Effect {
    /**
     * Indicates, if player can remove effect using right-click
     */
    protected boolean canBeRemoved;

    /**
     * Effect name
     */
    protected String name;

    /**
     * Effect description
     */
    protected String description;

    /**
     * Effect icon
     */
    protected Texture texture;

    public Effect(String name, String description, Texture texture) {
        this.name = name;
        this.canBeRemoved = true;
        this.description = description;
        this.texture = texture;
    }

    /**
     * Renders effect icon at given position
     *
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void render(int x, int y) {
        LastTry.batch.draw(this.texture, x, y);
    }

    /**
     * Abstact method, called on effect apply
     *
     * @param entity PhysicBody, on witch it is applied
     */
    public abstract void apply(Entity entity);

    /**
     * Abstact method, called on effect remove
     *
     * @param entity PhysicBody, from witch it is removed
     */
    public abstract void remove(Entity entity);

    /**
     * Used by some debuff, like poison
     *
     * @param dt The milliseconds passed since the last update.
     */
    public void update(Entity entity, int dt) {

    }

    /**
     * Returns effect name
     *
     * @return effect name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns effect description
     *
     * @return effect description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns if player can remove effect using right-click
     *
     * @return if player can remove effect using right-click
     */
    public boolean canBeRemoved() {
        return this.canBeRemoved;
    }
}