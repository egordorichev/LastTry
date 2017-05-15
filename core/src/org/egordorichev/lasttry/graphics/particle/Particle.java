package org.egordorichev.lasttry.graphics.particle;

import org.egordorichev.lasttry.Layers;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.components.GraphicsComponent;
import org.egordorichev.lasttry.entity.components.PhysicsComponent;

public class Particle extends Entity {
	public Particle(PhysicsComponent physics, GraphicsComponent graphics) {
		super(physics, graphics);

		this.setZIndex(Layers.particle);
	}

	public Particle() {
		this.setZIndex(Layers.particle);
	}

	public void render() {
		this.graphics.render();
	}

	public void update(int dt) {
		this.physics.update(dt);
	}
}