package org.egordorichev.lasttry.entity.system.systems;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.UpdateComponent;
import org.egordorichev.lasttry.entity.system.System;

import java.util.ArrayList;
import java.util.Objects;

public class UpdateSystem implements System {
	/**
	 * List of entities, with UpdateComponent
	 */
	private ArrayList<Entity> entities;

	/**
	 * Handles messages
	 *
	 * @param message Message from the engine
	 */
	@Override
	public void handleMessage(String message) {
		if (Objects.equals(message, "entity_added")) {
			this.entities = Globals.entitySystem.getEntitiesFor(UpdateComponent.class);
		}
	}

	/**
	 * Updates the game
	 */
	public void update(float delta) {
		for (Entity entity : this.entities) {
			entity.update(delta);
		}
	}
}