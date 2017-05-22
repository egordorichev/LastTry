package org.egordorichev.lasttry.ui;

/**
 * Interface applied to creatures that hold inventories.
 */
public interface InventoryOwner {
	UiInventory getInventory();

	void setInventory(UiInventory inventory);
}
