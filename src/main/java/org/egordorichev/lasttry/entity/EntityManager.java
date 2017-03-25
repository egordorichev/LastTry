package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.entity.enemy.Enemy;
import java.util.ArrayList;
import java.util.List;

public class EntityManager {
	/**
	 * List of active entities.
	 */
	private List<Entity> entities = new ArrayList<>();
	/**
	 * List of entities to remove.
	 */
	private List<Entity> clearList = new ArrayList<>();

	/**
	 * TODO: gore
	 */
	// private List<Gore> gores = new ArrayList<>();

	/**
	 * Render entities.
	 */
	public void render() {
		// TODO: Only render on-screen entities.
		// Old code for doing so:
		/*
		 * int gx = entity.getGridX(); int gy = entity.getGridY(); int w =
		 * entity.getGridWidth(); int h = entity.getGridHeight(); if ((gx > minX
		 * - w && gx < maxX + w) && (gy > minY - h && gy < maxY + h)) {
		 * entity.render(); }
		 */
		for (Entity entity : this.entities) {
			entity.render();
		}
	}

	/**
	 * Update entities.
	 * 
	 * @param dt
	 *            Time passed in milliseconds since last update.
	 */
	public void update(int dt) {
		// Remove entities marked for deletion
		for (Entity entity : this.clearList) {
			this.entities.remove(entity);
		}
		this.clearList.clear();

		// Update
		for (int i = this.entities.size() - 1; i >= 0; i--) {
			Entity entity = this.entities.get(i);
			entity.update(dt);

			if (!entity.shouldUpdate) {
				this.entities.remove(i);
			}
		}
	}

	/**
	 * Spawns given entity at given position
	 * 
	 * @param entity
	 *            entity to spawn
	 * @param x
	 *            spawn X coordinate
	 * @param y
	 *            spawn Y coordinate
	 */
	public Entity spawn(Entity entity, int x, int y) {
		entity.spawn(x, y);
		this.entities.add(entity);

		return entity;
	}

	/**
	 * Spawns new enemy with given id at given position
	 * 
	 * @param id
	 *            enemy id
	 * @param x
	 *            spawn X coordinate
	 * @param y
	 *            spawn Y coordinate
	 */
	public Enemy spawnEnemy(short id, int x, int y) {
		Enemy enemy = Enemy.create(id);
		this.spawn(enemy, x, y);
		return enemy;
	}

	/**
	 * Removes entity from update list
	 * 
	 * @param entity
	 *            entity to remove
	 */
	public void remove(Entity entity) {
		this.entities.remove(entity);
	}

	public void markForRemoval(Entity entity) {
		this.clearList.add(entity);
	}
}