package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.entity.Entity;

public class EntityComponent extends Component {
	protected Entity entity;

	public EntityComponent(Entity entity) {
		this.setEntity(entity);
	}

	public EntityComponent() {
		// Unsafe!
	}

	public void render() {

	}

	public void update(int dt) {

	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Entity getEntity() {
		return this.entity;
	}
}