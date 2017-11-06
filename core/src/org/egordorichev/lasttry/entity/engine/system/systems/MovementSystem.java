package org.egordorichev.lasttry.entity.engine.system.systems;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.component.SizeComponent;
import org.egordorichev.lasttry.entity.component.physics.AccelerationComponent;
import org.egordorichev.lasttry.entity.component.physics.CollisionComponent;
import org.egordorichev.lasttry.entity.component.physics.VelocityComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.entity.entities.item.tile.Block;
import org.egordorichev.lasttry.entity.entities.world.World;
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

			if (collision != null && collision.solid) {
				if (this.collidesWithWorld(entity)) {
					position.x -= velocity.x;
					velocity.x = 0;
				}
			}

			position.y += velocity.y;

			if (collision != null && collision.solid) {
				if (this.collidesWithWorld(entity)) {
					position.y -= velocity.y;
					velocity.y = 0;
				}
			}

			acceleration.x = 0;
			acceleration.y = 0;
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

	/**
	 * Checks for collision with world
	 *
	 * @param entity Entity to check
	 * @return
	 */
	private boolean collidesWithWorld(Entity entity) {
		PositionComponent position = entity.getComponent(PositionComponent.class);
		SizeComponent size = entity.getComponent(SizeComponent.class);

		short xStart = (short) (position.x / Block.SIZE);
		short yStart = (short) (position.y / Block.SIZE);

		for (short y = yStart; y < yStart + size.height / Block.SIZE + 1; y++) {
			for (short x = xStart; x < xStart + size.width / Block.SIZE + 1; x++) {
				String id = World.instance.getBlock(x, y);

				if (id != null) {
					Block block = (Block) Assets.items.get(id);

					if (block.getComponent(CollisionComponent.class).solid && this.collideWithBlock(position, size, x, y)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Checks for collision with block
	 *
	 * @param position Entity position
	 * @param size Entity size
	 * @param x Block X
	 * @param y Block Y
	 * @return Is entity colliding with the world
	 */
	private boolean collideWithBlock(PositionComponent position, SizeComponent size, short x, short y) {
		short ex = (short) (position.x / Block.SIZE);
		short ey = (short) (position.y / Block.SIZE);
		short w = (short) (size.width / Block.SIZE);
		short h = (short) (size.height / Block.SIZE);

		return (ex < x + Block.SIZE && ex + w > x && ey < y + Block.SIZE && ey + h > y);
	}
}