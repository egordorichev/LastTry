package org.egordorichev.lasttry.entity.system.systems;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.RenderComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.system.System;

import java.util.ArrayList;
import java.util.Objects;

public class RenderSystem implements System {
	/**
	 * List of entities, with RenderComponent
	 */
	private ArrayList<Entity> entities;

	/**
	 * Handles message
	 *
	 * @param message Message from the engine
	 */
	@Override
	public void handleMessage(String message) {
		if (Objects.equals(message, "render")) {
			render();
		} else if (Objects.equals(message, "entity_added")) {
			this.entities = Globals.entitySystem.getEntitiesFor(RenderComponent.class);
		}
	}

	/**
	 * Renders the game
	 */
	private void render() {
		for (Entity entity : this.entities) {
			entity.render();
		}
	}
}