package org.egordorichev.lasttry.entity.system;

import org.egordorichev.lasttry.entity.Entity;

import java.util.ArrayList;

public class EntitySystem {
	/**
	 * The entity list
	 */
	private ArrayList<Entity> entities;

	public EntitySystem() {
		this.entities = new ArrayList<>();
	}

	public void add(Entity entity) {
		this.entities.add(entity);
	}

	public void remove(Entity entity) {
		this.entities.remove(entity);
	}

	public void update(float delta) {
		for (Entity entity : this.entities) {
			entity.update(delta);
		}
	}

	public void render() {
		for (Entity entity : this.entities) {
			entity.render();
		}
	}

	public ArrayList<Entity> getEntities() {
		return this.entities;
	}
}