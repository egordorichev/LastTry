package org.egordorichev.lasttry.entity.component;

import org.egordorichev.lasttry.entity.Entity;

public class RenderComponent extends Component {
	public RenderComponent(Entity entity) {
		super(entity);
	}

	/**
	 * Renders the entity
	 */
	public void render() {
		this.entity.render();
	}
}