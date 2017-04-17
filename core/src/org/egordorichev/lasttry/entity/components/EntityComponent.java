package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.entity.Entity;

public class EntityComponent extends Component {
	protected Entity entity;

	public EntityComponent(Entity entity) {
		this.entity = entity;
	}

	public EntityComponent() {

	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Entity getEntity() {
		return this.entity;
	}
}