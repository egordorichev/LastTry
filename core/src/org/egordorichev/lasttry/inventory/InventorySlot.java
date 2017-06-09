package org.egordorichev.lasttry.inventory;

import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.items.Accessory;
import org.egordorichev.lasttry.item.items.Ammo;
import org.egordorichev.lasttry.item.items.Armor;
import org.egordorichev.lasttry.item.items.Coin;
import org.egordorichev.lasttry.item.items.Dye;

public interface InventorySlot {
    /**
     * Set the content of the item slot.
     * 
     * @param item
     *            Content to set.
     * @return If set was success.
     */
    boolean setItemHolder(ItemHolder item);

    /**
     * Returns the content of the item slot.
     * 
     * @return
     */
    ItemHolder getItemHolder();

    /**
     * Returns the content of the item slot, without stack information.
     * 
     * @return
     */
    default Item getItem() {
        return getItemHolder().getItem();
    }

    /**
     * Returns the content of the item slot, only the ID of the type of item
     * contained.
     * 
     * @return ID of content.
     */
    default String getItemID() {
        Item item = this.getItem();

        if (item == null) {
            return null;
        }

        return item.getID();
    }

    /**
     * Sets the amount of content in the slot.
     * 
     * @param count
     *            Amount of content.
     */
    default void setItemCount(int count) {
        getItemHolder().setCount(count);
    }

    /**
     * Returns the amount of content in the slot.
     * 
     * @return Amount of content.
     */
    default int getItemCount() {
        return getItemHolder().getCount();
    }

    /**
     * Returns true if there is no content in the slot. False if there is
     * content.
     * 
     * @return
     */
    default boolean isEmpty() {
        return getItemHolder().isEmpty();
    }

    /**
     * Swaps the current content with the given content.
     * 
     * @param to
     *            Content to put in the slot.
     * @return The content that was previously in the slot.
     */
    default ItemHolder swapItems(ItemHolder to) {
        // TODO: This seems wierd to me. Would returning null not make more
        // sense?
        //
        // If the slot cannot hold the given item, return the given item.
        if (to != null && !canHold(to)) {
            return to;
        }

        // Set the new item, return the item that WAS in the slot.
        ItemHolder tmp = getItemHolder();
        setItemHolder(to);
        return tmp;
    }

    /**
     * Returns true if the given content can be held by this slot. This is based
     * off of the slot {@link #getType() type}.
     * 
     * @param holder
     *            Type of content to test for insertion capability.
     * @return True if can be held.
     */
    default boolean canHold(ItemHolder holder) {
        switch (getType()) {
        case ANY:
        case TRASH:
        default:
            break;
        case ACCESSORY:
        case VANITY_ACCESSORY:
            if (!(holder.getItem() instanceof Accessory)) {
                return false;
            }
            break;
        case ARMOR:
        case VANITY:
            if (!(holder.getItem() instanceof Armor)) {
                return false;
            }
            break;
        case COIN:
            if (!(holder.getItem() instanceof Coin)) {
                return false;
            }
            break;
        case AMMO:
            if (!(holder.getItem() instanceof Ammo)) {
                return false;
            }
            break;
        case DYE:
            if (!(holder.getItem() instanceof Dye)) {
                return false;
            }
            break;
        }
        return true;
    }

    /**
     * Return the type of slot this is.
     * 
     * @return
     */
    Type getType();

    /**
     * Slot type. Determines what kinds of items can be held by the slot.
     */
    public enum Type {
        ANY, ACCESSORY, VANITY_ACCESSORY, ARMOR, COIN, AMMO, TRASH, VANITY, DYE
    }

}
