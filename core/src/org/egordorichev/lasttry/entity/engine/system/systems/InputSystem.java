package org.egordorichev.lasttry.entity.engine.system.systems;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.physics.AccelerationComponent;
import org.egordorichev.lasttry.entity.component.InputComponent;
import org.egordorichev.lasttry.entity.component.physics.CollisionComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.entity.entities.item.ItemUseComponent;
import org.egordorichev.lasttry.entity.entities.item.inventory.InventoryComponent;
import org.egordorichev.lasttry.entity.entities.item.inventory.ItemComponent;
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
		for (Entity entity : this.entities) {
			InputComponent input = entity.getComponent(InputComponent.class);

			if (input.handleInput) {
				AccelerationComponent acceleration = entity.getComponent(AccelerationComponent.class);
				CollisionComponent collision = entity.getComponent(CollisionComponent.class);

				if (Gdx.input.isKeyPressed(Assets.keys.get("move_left"))) {
					acceleration.x -= 0.3; // TODO: get those values from somewhere
				}

				if (Gdx.input.isKeyPressed(Assets.keys.get("move_right"))) {
					acceleration.x += 0.3;
				}

				if (collision.onGround && Gdx.input.isKeyPressed(Assets.keys.get("jump"))) {
					acceleration.y += 10;
				}
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