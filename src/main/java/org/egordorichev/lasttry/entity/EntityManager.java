package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.entity.enemy.Enemy;
import java.util.ArrayList;

public class EntityManager {
	/** List of active entities */
	private ArrayList<Entity> entities;

	/** TODO: gores */
	// private ArrayList<Gore> gores;

	public EntityManager() {
		this.entities = new ArrayList<>();
	}

	public void render() {
		for (Entity entity : this.entities) {
			entity.render();
		}
	}

	public void update(int dt) {
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
	 * @param entity entity to spawn
	 * @param x spawn X coordinate
	 * @param y spawn Y coordinate
	 */
	public Entity spawn(Entity entity, int x, int y) {
		entity.spawn(x, y);
		this.entities.add(entity);

		return entity;
	}

	/**
	 * Spawns new enemy with given id at given position
	 * @param id enemy id
	 * @param x spawn X coordinate
	 * @param y spawn Y coordinate
	 */
	public Enemy spawnEnemy(short id, int x, int y) {
		Enemy enemy = Enemy.create(id);
		this.spawn(enemy, x, y);

		return enemy;
	}

	/**
	 * Removes entity from update list
	 * @param entity entity to remove
	 */
	public void remove(Entity entity) {
		this.entities.remove(entity);
	}
}