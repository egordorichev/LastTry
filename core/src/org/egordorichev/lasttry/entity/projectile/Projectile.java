package org.egordorichev.lasttry.entity.projectile;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.components.GraphicsComponent;
import org.egordorichev.lasttry.entity.components.PhysicsComponent;

public class Projectile extends Entity {
	public Projectile(PhysicsComponent physics, GraphicsComponent graphics) {
		super(physics, graphics);
	}
}