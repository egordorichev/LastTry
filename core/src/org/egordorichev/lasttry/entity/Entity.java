package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.components.GraphicsComponent;
import org.egordorichev.lasttry.entity.components.PhysicsComponent;

public class Entity {
	protected boolean active = false;
	protected int zIndex = 0;

	public PhysicsComponent physics;
	public GraphicsComponent graphics;

	public Entity(PhysicsComponent physics, GraphicsComponent graphics) {
		this.graphics = graphics;
		this.graphics.setEntity(this);

		this.physics = physics;
		this.physics.setEntity(this);
	}

	public Entity() {
		this.physics = new PhysicsComponent(this);
		this.graphics = new GraphicsComponent(this);
	}

	public void render() {
		this.graphics.render();
	}

	public void update(int dt) {
		this.physics.update(dt);
	}

	/**
	 * Spawn the entity in the world at the given X and Y coordinates in pixels.
	 * @param x
	 * @param y
	 */
	public void spawn(int x, int y) {
		if (this.active) {
			return;
		}

		this.physics.setPosition(x, y);
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

	public int getZIndex() {
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