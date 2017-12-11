package org.egordorichev.lasttry.entity.engine.system.systems;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.component.SizeComponent;
import org.egordorichev.lasttry.entity.component.physics.CollisionComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.entity.entities.item.tile.Block;
import org.egordorichev.lasttry.entity.entities.world.World;
import org.egordorichev.lasttry.entity.entities.world.chunk.Chunk;
import org.egordorichev.lasttry.util.collision.Collider;

import java.util.ArrayList;
import java.util.Objects;

public class CollisionSystem implements System {
	/**
	 * The list of collidable entities
	 */
	private ArrayList<Entity> entities = new ArrayList<>();

	@Override
	public void update(float delta) {
		SizeComponent worldSize = World.instance.getComponent(SizeComponent.class);

		float width = worldSize.width * Chunk.SIZE * Block.SIZE;
		float height = worldSize.height * Chunk.SIZE * Block.SIZE;

		for (Entity entity : this.entities) {
			PositionComponent position = entity.getComponent(PositionComponent.class);
			SizeComponent size = entity.getComponent(SizeComponent.class);

			if (position != null && size != null) {
				position.x = Math.max(Math.min(position.x, width - Block.SIZE - size.width), Block.SIZE);
				position.y = Math.max(Math.min(position.y, height - Block.SIZE - size.height), Block.SIZE);

				for (Entity second : this.entities) {
					PositionComponent secondPosition = second.getComponent(PositionComponent.class);
					SizeComponent secondSize = second.getComponent(SizeComponent.class);

					if (secondPosition != null && secondSize != null) {
						if (Collider.testAABB(position.x, position.y, size.width, size.height,
							secondPosition.x, secondPosition.y, secondSize.width, secondSize.height)) {

							// TODO: react here
						}
					}
				}
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