package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.entity.components.*;

public class Creature extends Entity {
	public StatsComponent stats = new StatsComponent(this);
	public StateComponent state = new StateComponent(this);

	public Creature() {
		super(new CreaturePhysicsComponent(), new CreatureGraphicsComponent());
	}

	public Creature(PhysicsComponent physics, GraphicsComponent graphics) {
		super(physics, graphics);
	}

	@Override
	public void update(int dt) {
		super.update(dt);
		this.stats.update(dt);
	}
}