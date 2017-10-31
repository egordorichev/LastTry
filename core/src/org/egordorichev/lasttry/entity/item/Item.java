package org.egordorichev.lasttry.entity.item;

import org.egordorichev.lasttry.entity.Entity;

/**
 * Represents an item in the game
 */
public class Item extends Entity {
	/**
	 * Item ID
	 */
	protected String id;

	public Item(String id) {
		this.id = id;
	}

	/**
	 * @return Item ID
	 */
	public String getId() {
		return this.id;
	}
}