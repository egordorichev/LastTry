package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.components.GraphicsComponent;
import org.egordorichev.lasttry.entity.components.PhysicsComponent;

public class Entity {
	/**
	 * Entity is alive
	 */
	protected boolean active = false;
	/**
	 * Entity z-index (bigger == higher)
	 */
	protected int zIndex = 0;
	/**
	 * Physics controller
	 */
	public PhysicsComponent physics;
	/**
	 * Graphics controller
	 */
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

	/** Renders entity */
	public void render() {
		this.graphics.render();
	}

	/**
	 * Updates entity
	 * @param dt Time from last update
	 */
	public void update(int dt) {
		if (dt == 0) { // No time had passed
			return;
		}

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

	/**
	 * Kills the entity
	 */
	public void die() {
		if (!this.active) {
			return;
		}

		this.active = false;
		this.onDeath();

		Globals.entityManager.markForRemoval(this);
	}

	/**
	 * Sets entity z-index
	 * @param zIndex New z-index
	 */
	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	/**
	 * @return Entity z-index
	 */
	public int getZIndex() {
		return this.zIndex;
	}

	/** Callback, called on spawn */
	protected void onSpawn() {

	}

	/** Callback, called on death */
	protected void onDeath() {

	}

	/**
	 * @return Entity is alive
	 */
	public boolean isActive() {
		return this.active;
	}
}