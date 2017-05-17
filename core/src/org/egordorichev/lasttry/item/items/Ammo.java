package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.item.Item;

public class Ammo extends Item {
    /**
     * Ammo damage
     */
    protected int damage;
    /**
     * Ammo type
     */
    protected Type type;

    public Ammo(short id, String name, int damage, Type type, TextureRegion texture) {
        super(id, name, texture);

        this.damage = damage;
        this.type = type;
    }

    /**
     * Returns true if the ammo has a damage value of &gt; 1.
     *
     * @return if ammo can damage.
     */
    public boolean hasDamage() {
        return this.damage > 0;
    }

    /**
     * Returns the damage the ammo will inflict.
     *
     * @return ammo damage.
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * Returns the ammo type.
     *
     * @return ammo type.
     */
    public Type getType() {
        return this.type;
    }

    public enum Type {
        BULLET,
        ARROW,
        ROCKET,
        DART,
        SOLUTION, // Used by some tools
        BAIT,
        WIRE,
        OTHER
    }
}
