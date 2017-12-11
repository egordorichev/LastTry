package org.egordorichev.lasttry.entity.entities.item.tile.interactable;

import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.entities.item.tile.Block;

/**
 * Handles clicking on block
 * TODO: other events, like step on, collide
 */
public class InteractableBlock extends Block implements Interactable {
	public InteractableBlock(String id) {
		super(id);

		this.addComponent(InteractionComponent.class);
		Engine.addEntity(this);
	}

	/**
	 * Handles mouse click
	 *
	 * @param x Mouse X
	 * @param y Mouse Y
	 */
	@Override
	public void onClick(int x, int y) {

	}

	/**
	 * Checks overlap with mouse
	 *
	 * @param x Mouse X
	 * @param y Mouse Y
	 * @return If object overlaps
	 */
	@Override
	public boolean checkOverlap(int x, int y) {
		return false;
	}
}