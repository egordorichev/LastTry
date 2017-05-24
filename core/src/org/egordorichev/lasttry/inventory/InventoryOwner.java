package org.egordorichev.lasttry.inventory;

/**
 * Interface applied to creatures that hold inventories.
 */
public interface InventoryOwner {
    /**
     * The inventory the owner can pull up.
     * 
     * @return
     */
    Inventory getInventory();

    /**
     * Sets the entity's inventory.
     * 
     * @param inventory
     */
    void setInventory(Inventory inventory);
}
