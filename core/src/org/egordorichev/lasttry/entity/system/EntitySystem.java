package org.egordorichev.lasttry.entity.system;

import org.egordorichev.lasttry.entity.Entity;
import java.util.ArrayList;

/**
 * Handles all entities
 * Not a subclass of System!
 */
public class EntitySystem {
	/**
	 * The entity list
	 */
	private ArrayList<Entity> entities;

	public EntitySystem() {
		this.entities = new ArrayList<>();
	}

	/**
	 * Adds an entity
	 *
	 * @param entity Entity to add
	 */
	public void add(Entity entity) {
		this.entities.add(entity);
	}

	/**
	 * Removes an entity
	 *
	 * @param entity Entity to remove
	 */
	public void remove(Entity entity) {
		this.entities.remove(entity);
	}

	/**
	 * @return All entities
	 */
	public ArrayList<Entity> getEntities() {
		return this.entities;
	}
}