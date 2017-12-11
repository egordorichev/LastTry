package org.egordorichev.lasttry.entity.entities.item.tile.interactable.fc;

import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.entities.item.tile.interactable.InteractableBlock;
import org.egordorichev.lasttry.entity.entities.ui.window.UiWindow;
import org.egordorichev.lasttry.util.geometry.Rectangle;

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
		// TODO: keep track of the windows
		// Close the current one, if one is open
		// (they should have id, something like fc_x_y) (x and y in blocks!)
		Engine.addEntity(new UiWindow(new Rectangle(96, 26, 128, 128))); // TMP
	}
}