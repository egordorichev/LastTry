package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.graphics.Animation;

public class GraphicsComponent extends EntityComponent {
	public Animation[] animations;
	private float alpha;

	public GraphicsComponent(Entity entity) {
		super(entity);
	}

	public GraphicsComponent() {
		super();
	}

	public void render() {

	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
}