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
}