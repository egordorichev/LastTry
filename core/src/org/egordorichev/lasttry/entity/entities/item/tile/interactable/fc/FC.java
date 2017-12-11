package org.egordorichev.lasttry.entity.entities.item.tile.interactable.fc;

import org.egordorichev.lasttry.entity.entities.item.tile.Block;
import org.egordorichev.lasttry.entity.entities.item.tile.interactable.InteractableBlock;
import org.egordorichev.lasttry.entity.entities.world.World;

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

	/**
	 * Handle click!
	 *
	 * @param x Mouse X
	 * @param y Mouse Y
	 */
	@Override
	public void onClick(int x, int y) {
		World.instance.setBlock("lt:dirt", (short) Math.floor(x / Block.SIZE), (short) Math.floor(y / Block.SIZE));
	}
}