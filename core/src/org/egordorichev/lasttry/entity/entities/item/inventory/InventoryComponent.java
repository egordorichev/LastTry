package org.egordorichev.lasttry.entity.entities.item.inventory;

import org.egordorichev.lasttry.entity.component.Component;

/**
 * Handles items
 */
public class InventoryComponent extends Component {
	/**
	 * The actual inventory
	 */
	public ItemComponent[] inventory;
	/**
	 * Shows, if the inventory is open
	 */
	public boolean open = false;

	public InventoryComponent() {
		this.inventory = new ItemComponent[40];

		for (int i = 0; i < 40; i++) {
			this.inventory[i] = new ItemComponent();
		}
	}
}