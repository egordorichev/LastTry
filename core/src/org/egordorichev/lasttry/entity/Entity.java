package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.entity.components.GraphicsComponent;
import org.egordorichev.lasttry.entity.components.PhysicsComponent;

public class Entity {
	public PhysicsComponent physics = new PhysicsComponent(this);
	public GraphicsComponent graphics = new GraphicsComponent(this);
	private boolean active = false;

	public Entity() {

	}

	void render() {
		this.graphics.render();
	}

	void update(int dt) {
		if (!this.active) {
			return;
		}

		this.physics.update(dt);
	}

	void spawn(int x, int y) {
		if (this.active) {
			return;
		}

		this.active = true;
		this.physics.setGridPosition(x, y);
	}

	public boolean isActive() {
		return this.active;
	}
}