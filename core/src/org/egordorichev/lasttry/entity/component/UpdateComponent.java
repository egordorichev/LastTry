package org.egordorichev.lasttry.entity.component;

import org.egordorichev.lasttry.entity.Entity;

public class UpdateComponent extends Component {
	public UpdateComponent(Entity entity) {
		super(entity);
	}

	/**
	 * Updates the entity
	 */
	public void update(float delta) {
		this.entity.update(delta);
	}
}