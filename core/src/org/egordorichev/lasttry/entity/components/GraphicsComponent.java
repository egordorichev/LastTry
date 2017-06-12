package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.graphics.Animation;

public class GraphicsComponent extends EntityComponent {
	public Animation[] animations;

	public GraphicsComponent(Entity entity) {
		super(entity);
	}

	public GraphicsComponent() {
		super();
	}
}