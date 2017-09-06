package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.graphics.Animation;

public class GraphicsComponent<T extends Entity> extends EntityComponent<T> {
	public Animation[] animations;
	private float alpha;

	public GraphicsComponent(T entity) {
		super(entity);
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
}