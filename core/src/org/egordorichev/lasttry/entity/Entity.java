package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.Layers;

public class Entity {
	protected boolean active = false;
	protected int zIndex = 0;

	public Entity() {
		this.setZIndex(Layers.entity);
	}

	public void render() {

	}

	public void update(int dt) {

	}

	// In Pixels!
	public void spawn(int x, int y) {
		if (this.active) {
			return;
		}

		this.active = true;
		this.onSpawn();
	}

	public void die() {
		if (!this.active) {
			return;
		}

		this.active = false;
		this.onDeath();

		Globals.entityManager.markForRemoval(this);
	}

	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	public int getzIndex() {
		return this.zIndex;
	}

	protected void onSpawn() {

	}

	protected void onDeath() {

	}

	public boolean isActive() {
		return this.active;
	}
}