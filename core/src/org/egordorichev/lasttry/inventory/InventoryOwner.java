package org.egordorichev.lasttry.inventory;

/**
 * Interface applied to creatures that hold inventories.
 */
public interface InventoryOwner<T extends InventorySlot> {
    /**
     * The inventory the owner can pull up.
     * 
     * @return
     */
    Inventory<T> getInventory();

    /**
     * Sets the entity's inventory.
     * 
     * @param inventory
     */
    void setInventory(Inventory<T> inventory);
}
