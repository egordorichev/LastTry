package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.entity.components.GraphicsComponent;
import org.egordorichev.lasttry.entity.components.PhysicsComponent;

public class Entity {
	public PhysicsComponent physics;
	public GraphicsComponent graphics;
	private boolean active = false;

	public Entity(PhysicsComponent physics, GraphicsComponent graphics) {
		this.physics = physics;
		this.graphics = graphics;

		this.physics.setEntity(this);
		this.graphics.setEntity(this);
	}

	public void render() {
		this.graphics.render();
	}

	public void update(int dt) {
		if (!this.active) {
			return;
		}

		this.physics.update(dt);
	}

	public void spawn(int x, int y) {
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