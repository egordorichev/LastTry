package org.egordorichev.lasttry.graphics.particle;

import org.egordorichev.lasttry.Layers;
import org.egordorichev.lasttry.entity.Creature;

public class Particle extends Creature {
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