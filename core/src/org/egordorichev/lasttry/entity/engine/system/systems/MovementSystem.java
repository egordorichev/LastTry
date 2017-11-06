package org.egordorichev.lasttry.entity.engine.system.systems;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.component.physics.AccelerationComponent;
import org.egordorichev.lasttry.entity.component.physics.CollisionComponent;
import org.egordorichev.lasttry.entity.component.physics.VelocityComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.util.log.Log;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Handles entity movement and collision
 */
public class MovementSystem implements System {
	/**
	 * List of the entities, that move
	 */
	private ArrayList<Entity> entities;

	/**
	 * Handles entity movement
	 *
	 * @param delta Time, since the last frame
	 */
	@Override
	public void update(float delta) {
		for (Entity entity : this.entities) {
			PositionComponent position = entity.getComponent(PositionComponent.class);
			VelocityComponent velocity = entity.getComponent(VelocityComponent.class);
			AccelerationComponent acceleration = entity.getComponent(AccelerationComponent.class);
			CollisionComponent collision = entity.getComponent(CollisionComponent.class);

			velocity.x += acceleration.x;
			velocity.y += acceleration.y;

			velocity.x *= 0.9;
			velocity.y *= 0.9;

			position.x += velocity.x;
			position.y += velocity.y;

			acceleration.x = 0;
			acceleration.y = 0;

			// TODO: collision
		}
	}

	/**
	 * Handles messages
	 *
	 * @param message Message from the engine
	 */
	@Override
	public void handleMessage(String message) {
		if (Objects.equals(message, "entity_added")) {
			this.entities = Engine.getEntitiesFor(VelocityComponent.class, AccelerationComponent.class);
		}
	}
}