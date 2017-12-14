package org.egordorichev.lasttry.entity.entities.item.inventory;

import org.egordorichev.lasttry.entity.component.Component;
import org.egordorichev.lasttry.entity.entities.item.Item;

/**
 * Stores item, count, and other data
 */
public class ItemComponent extends Component {
	/**
	 * The item
	 */
	public Item item;
	/**
	 * Amount of that item
	 */
	public short count = 0;

	/*
	 * You can have more data here, like modifiers
	 */

	/**
	 * @return This holder doesn't hold any items
	 */
	public boolean isEmpty() {
		return this.item == null;
	}
}