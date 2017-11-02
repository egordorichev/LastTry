package org.egordorichev.lasttry.entity.system.systems;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.RenderComponent;
import org.egordorichev.lasttry.entity.system.System;

import java.util.Objects;

public class RenderSystem implements System {
	/**
	 * Handles message
	 *
	 * @param message Message from the engine
	 */
	@Override
	public void handleMessage(String message) {
		if (Objects.equals(message, "render")) {
			render();
		}
	}

	/**
	 * Renders the game
	 */
	private void render() {
		for (Entity entity : Globals.entitySystem.getEntities()) {
			RenderComponent component = (RenderComponent) entity.getComponent(RenderComponent.class);

			if (component != null) {
				component.render();
			}
		}
	}
}