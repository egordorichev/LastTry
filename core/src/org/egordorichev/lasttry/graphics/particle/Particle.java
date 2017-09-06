package org.egordorichev.lasttry.graphics.particle;

import org.egordorichev.lasttry.Layers;
import org.egordorichev.lasttry.entity.Entity;

/**
 * Simple particle class
 */
public class Particle extends Entity {
	public Particle() {
		this.setZIndex(Layers.particle);
	}

}