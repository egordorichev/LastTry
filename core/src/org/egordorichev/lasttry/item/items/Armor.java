package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.item.Item;

public class Armor extends Item {
    /**
     * Defense, provided by this armor piece.
     */
    protected int defense;
    /**
     * The slot that the armor can be placed in.
     */
    protected Slot slot;

    public Armor(short id, String name, int defense, Slot slot, Texture texture) {
        super(id, name, texture);

        this.defense = defense;
        this.slot = slot;
    }

    /**
     * Return the total defense points the armor gives.
     *
     * @return armor defense
     */
    public int getDefense() {
        return this.defense;
    }

    /**
     * Return the slot that the armor can be placed in.
     *
     * @return armor slot
     */
    public Slot getSlot() {
        return this.slot;
    }

    public enum Slot {
        HEAD, BODY, LEGS
    }
}
