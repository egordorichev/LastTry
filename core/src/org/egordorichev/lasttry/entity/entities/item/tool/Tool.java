package org.egordorichev.lasttry.entity.entities.item.tool;

import org.egordorichev.lasttry.entity.entities.item.Item;

/**
 * Handles block / wall destruction
 */
public class Tool extends Item {
	public Tool(String id) {
		super(id);
	}

	/**
	 * Destroys block / wall
	 * @return Item should be removed from inventory
	 */
	@Override
	protected boolean onUse() {
		return false;
	}
}