package org.egordorichev.lasttry.entity.engine.system.systems;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.component.SizeComponent;
import org.egordorichev.lasttry.entity.component.physics.CollisionComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.engine.system.System;

import java.util.ArrayList;
import java.util.Objects;

public class CollisionSystem implements System {
	/**
	 * The list of collidable entities
	 */
	private ArrayList<Entity> entities;

	@Override
	public void update(float delta) {
		for (Entity entity : this.entities) {
			PositionComponent position = entity.getComponent(PositionComponent.class);
			SizeComponent size = entity.getComponent(SizeComponent.class);

			for (Entity second : this.entities) {
				PositionComponent secondPosition = second.getComponent(PositionComponent.class);
				SizeComponent secondSize = second.getComponent(SizeComponent.class);

			}
		}
	}

	/**
	 * Handles adding new enemies
	 *
	 * @param message Message from the engine
	 */
	@Override
	public void handleMessage(String message) {
		if (Objects.equals(message, "entity_added")) {
			this.entities = Engine.getEntitiesFor(CollisionComponent.class);
		}
	}
}