package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.entity.components.StatsComponent;

public class Creature extends Entity {
	public StatsComponent stats = new StatsComponent(this);

	public Creature() {

	}

	@Override
	void update(int dt) {
		super.update(dt);
		this.stats.update(dt);
	}
}