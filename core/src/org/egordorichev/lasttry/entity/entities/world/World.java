package org.egordorichev.lasttry.entity.entities.world;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.RenderComponent;
import org.egordorichev.lasttry.entity.component.UpdateComponent;

/**
 * Handles chunks
 */
public class World extends Entity {
	public World() {
		this.addComponent(UpdateComponent.class);
		this.addComponent(RenderComponent.class);
	}

	@Override
	public void update(float delta) {

	}

	@Override
	public void render() {

	}
}