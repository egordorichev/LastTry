package org.egordorichev.lasttry.effect;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.Creature;

public abstract class Effect {
    protected boolean canBeRemoved;
    protected String name;
    protected String description;
    protected Texture texture;

    public Effect(String name, String description, Texture texture) {
        this.name = name;
        this.canBeRemoved = true;
        this.description = description;
        this.texture = texture;
    }

    public void render(int x, int y) {
        LastTry.batch.draw(this.texture, x, y);
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