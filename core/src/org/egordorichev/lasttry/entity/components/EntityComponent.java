package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.entity.Entity;

public class EntityComponent implements Component {
	/**
	 * Entity, that is being served
	 */
	protected Entity entity;

	public EntityComponent(Entity entity) {
		this.setEntity(entity);
	}

	public EntityComponent() {
		// Unsafe!
	}

	@Override
	public void render() {

	}

	@Override
	public void update(int dt) {

	}

	/**
	 * @return Entity, that is being served
	 */
	public Entity getEntity() {
		return this.entity;
	}

	/**
	 * Sets new entity
	 *
	 * @param entity New entity
	 */
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
}