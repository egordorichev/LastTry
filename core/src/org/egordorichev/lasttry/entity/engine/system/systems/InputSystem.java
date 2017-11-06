package org.egordorichev.lasttry.entity.engine.system.systems;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.physics.AccelerationComponent;
import org.egordorichev.lasttry.entity.component.InputComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.engine.system.System;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Handles in-game input
 */
public class InputSystem implements System {
	/**
	 * List of entities, that react on input
	 */
	private ArrayList<Entity> entities;

	/**
	 * Handles input
	 *
	 * @param delta Time, since the last frame
	 */
	@Override
	public void update(float delta) {
		for (Entity entity : this.entities) {
			InputComponent input = entity.getComponent(InputComponent.class);
			AccelerationComponent acceleration = entity.getComponent(AccelerationComponent.class);

			if (Gdx.input.isKeyPressed(input.moveLeft)) {
				acceleration.x -= 1;
			}

			if (Gdx.input.isKeyPressed(input.moveRight)) {
				acceleration.x += 1;
			}

			acceleration.y = -1f;

			if (Gdx.input.isKeyPressed(input.jump)) {
				acceleration.y += 2;
			}
		}
	}

	/**
	 * Handles in-coming messages
	 *
	 * @param message Message from the engine
	 */
	@Override
	public void handleMessage(String message) {
		if (Objects.equals(message, "entity_added")) {
			this.entities = Engine.getEntitiesFor(InputComponent.class, AccelerationComponent.class);
		}
	}
}