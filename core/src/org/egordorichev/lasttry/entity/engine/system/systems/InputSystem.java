package org.egordorichev.lasttry.entity.engine.system.systems;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.InputComponent;
import org.egordorichev.lasttry.entity.component.physics.AccelerationComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.engine.SystemMessages;
import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.entity.entities.creature.player.PlayerInputComponent;
import org.egordorichev.lasttry.util.input.Input;
import org.egordorichev.lasttry.util.input.SimpleInputProcessor;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Handles in-game input
 */
public class InputSystem implements System, SimpleInputProcessor {
	/**
	 * List of entities, that react on input
	 */
	private ArrayList<Entity> entities = new ArrayList<>();

	public InputSystem() {
		Input.multiplexer.addProcessor(this);
	}

	/**
	 * Handles input
	 *
	 * @param delta Time, since the last frame
	 */
	@Override
	public void update(float delta) {
		if (Input.blocked) {
			return;
		}

		for (Entity entity : this.entities) {
			InputComponent input = entity.getComponent(InputComponent.class);

			if (input == null) {
				// TODO: fix
				input = entity.getComponent(PlayerInputComponent.class);
			}

			if (input.handleInput) {
				input.processInput();
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
		if (Objects.equals(message, SystemMessages.ENTITIES_UPDATED)) {
			this.entities = Engine.getEntitiesFor(InputComponent.class, AccelerationComponent.class);

			// TODO: optimize
			ArrayList<Entity> players = Engine.getEntitiesFor(PlayerInputComponent.class, AccelerationComponent.class);

			if (!players.isEmpty()) {
				this.entities.addAll(players);
			}
		}
	}
}