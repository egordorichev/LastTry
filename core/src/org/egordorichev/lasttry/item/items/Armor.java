package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.item.Item;

public class Armor extends Item {
    /**
     * Defense, provided by this armor piece.
     */
    protected short defense;
    /**
     * The slot that the armor can be placed in.
     */
    protected Slot slot;

    public Armor(String id) {
        super(id);
    }

    @Override
    protected void loadFields(JsonValue root) {
        super.loadFields(root);

        this.slot = Slot.valueOf(root.getString("slot", "body").toUpperCase());
        this.defense = root.getShort("defense", (short) 10);
    }

    /**
     * Return the total defense points the armor gives.
     *
     * @return armor defense
     */
    public short getDefense() {
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
