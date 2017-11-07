package org.egordorichev.lasttry.entity.entities.item.inventory;

import org.egordorichev.lasttry.entity.component.Component;

/**
 * Handles items
 */
public class InventoryComponent extends Component {
	/**
	 * The actual inventory
	 */
	public static ItemComponent[] inventory = new ItemComponent[40];
}