package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.entity.Entity;

public class EntityComponent implements Component {
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

	public Entity getEntity() {
		return this.entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
}