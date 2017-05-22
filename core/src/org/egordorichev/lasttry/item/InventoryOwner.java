package org.egordorichev.lasttry.item;

import org.egordorichev.lasttry.ui.UiInventory;

/**
 * Interface applied to creatures that hold inventories.
 */
public interface InventoryOwner {
	UiInventory getInventory();

	void setInventory(UiInventory inventory);
}
