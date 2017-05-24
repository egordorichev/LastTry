package org.egordorichev.lasttry.item;

import org.egordorichev.lasttry.ui.UiInventory;

/**
 * Interface applied to creatures that hold inventories.
 */
public interface InventoryOwner {
    /**
     * The inventory the owner can pull up.
     * 
     * @return
     */
    UiInventory getInventory();

    /**
     * Sets the entity's inventory.
     * 
     * @param inventory
     */
    void setInventory(UiInventory inventory);
}
