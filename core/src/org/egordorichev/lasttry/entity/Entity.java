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
	public PhysicsComponent<? extends Entity> physics;
	/**
	 * Graphics controller
	 */
	public GraphicsComponent<? extends Entity> graphics;

	public Entity() {
		setupComponents();
	}

	/**
	 * Instantiates the components of the entity.
	 */
	protected void setupComponents() {
		this.physics = new PhysicsComponent<>(this);
		this.graphics = new GraphicsComponent<>(this);
	}

	/** Renders entity */
	public void render() {
		float alpha = Globals.getWorld().light.get(this.physics.getGridX(), this.physics.getGridY() + 1);
		this.graphics.setAlpha(alpha);
		this.graphics.render();
	}

	/**
	 * Updates entity
	 * 
	 * @param dt
	 *            Time from last update
	 */
	public void update(int dt) {
		// No time had passed
		if (dt == 0) {
			return;
		}

		this.physics.update(dt);
	}

	/**
	 * Spawn the entity in the world at the given X and Y coordinates in pixels.
	 * 
	 * @param x
	 *            Spawn X
	 * @param y
	 *            Spawn Y
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
	 * 
	 * @param zIndex
	 *            New z-index
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