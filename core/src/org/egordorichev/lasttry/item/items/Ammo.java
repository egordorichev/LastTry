package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.item.Item;

public class Ammo extends Item {
    /**
     * Ammo damage
     */
    protected short damage;
    /**
     * Ammo type
     */
    protected Type type;

    public Ammo(String id) {
        super(id);
    }

    @Override
    protected void loadFields(JsonValue root) {
        super.loadFields(root);

	    this.type = Type.valueOf(root.getString("type", "bullet").toUpperCase());
	    this.damage = root.getShort("damage", (short) 0);
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
    public short getDamage() {
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
