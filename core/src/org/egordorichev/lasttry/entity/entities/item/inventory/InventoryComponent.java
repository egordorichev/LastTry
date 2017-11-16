package org.egordorichev.lasttry.entity.entities.item.inventory;

import org.egordorichev.lasttry.entity.component.Component;

/**
 * Handles items
 */
public class InventoryComponent extends Component {
	/**
	 * The actual inventory
	 */
	public ItemComponent[] inventory = new ItemComponent[40];
	/**
	 * Shows, if the inventory is open
	 */
	public boolean open = false;
}