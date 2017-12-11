package org.egordorichev.lasttry.entity.entities.item.tile.interactable.fc;

import org.egordorichev.lasttry.entity.entities.item.tile.interactable.InteractableBlock;

public class FC extends InteractableBlock {
	public FC(String id) {
		super(id);
	}

	/**
	 * Returns amount of tileable neighbors
	 *
	 * @param x Block X
	 * @param y Block Y
	 * @return Packed binary
	 */
	public int getNeighbors(short x, short y) {
		// WE DO NOT TILE
		return 0;
	}
}