package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.entity.Entity;

public class EntityComponent<T extends Entity> implements Component {
	protected final T entity;

	public EntityComponent(T entity) {
		this.entity = entity;
	}
	
	@Override
	public void render() {

	}
	@Override
	public void update(int dt) {

	}

	public T getEntity() {
		return this.entity;
	}
}