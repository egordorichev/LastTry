package org.egordorichev.lasttry.entity.system;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.Component;
import org.egordorichev.lasttry.entity.engine.Engine;

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
	 * Returns all entities with given components
	 *
	 * @param types Component types
	 * @return Entities list
	 */
	public ArrayList<Entity> getEntitiesFor(Class<? extends Component>... types) {
		ArrayList<Entity> list = new ArrayList<>();

		for (Entity entity : this.entities) {
			boolean has = true;

			for (Class<? extends Component> type : types) {
				if (!entity.hasComponent(type)) {
					has = false;
					break;
				}
			}

			if (has) {
				list.add(entity);
			}
		}

		return list;
	}

	/**
	 * Adds an entity
	 *
	 * @param entity Entity to add
	 */
	public void add(Entity entity) {
		this.entities.add(entity);
		Engine.sendMessage("entity_added");
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