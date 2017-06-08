package org.egordorichev.lasttry.effect;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.entity.Creature;
import org.egordorichev.lasttry.graphics.Graphics;

public abstract class Effect {
    protected boolean canBeRemoved;
    protected String name;
    protected String description;
    protected TextureRegion texture;

    public Effect(String name, String description, TextureRegion texture) {
        this.name = name;
        this.canBeRemoved = true;
        this.description = description;
        this.texture = texture;
    }

    public void render(int x, int y) {
        Graphics.batch.draw(this.texture, x, y);
    }

    public abstract void apply(Creature creature);
    public abstract void remove(Creature creature);

    public void update(Creature creature, int dt) {

    }


    public String getName() {
        return this.name;
    }


    public String getDescription() {
        return this.description;
    }

    public boolean canBeRemoved() {
        return this.canBeRemoved;
    }
}