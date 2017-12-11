package org.egordorichev.lasttry.entity.entities.item.tile.interactable.fc;

import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.engine.system.systems.UiSystem;
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
		// If you click on another FC, open window for that FC, and do not close current
		// (they should have id, something like fc_x_y) (x and y in blocks!)
		UiWindow window = (UiWindow) UiSystem.instance.get("fc");

		if (window != null) {
			Engine.removeEntity(window);
		} else {
			window = new UiWindow(new Rectangle(96, 26, 128, 128));
			window.getComponent(IdComponent.class).id = "fc";

			Engine.addEntity(window);
		}
	}

	/**
	 * Renders the FC
	 */
	@Override
	public void renderUi() {
		super.renderUi(); // Render bg
		// TODO: render VRAM
	}
}