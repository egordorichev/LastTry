package org.egordorichev.lasttry.graphics.particle;

import org.egordorichev.lasttry.entity.Creature;

public class Particle extends Creature {
	public void render() {
		this.graphics.render();
	}

	public void update(int dt) {
		this.physics.update(dt);
	}
}