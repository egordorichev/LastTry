package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.entity.components.*;

public class Creature extends Entity {
	public CreaturePhysicsComponent physics;
	public CreatureGraphicsComponent graphics;
	public CreatureStatsComponent stats;
	public CreatureStateComponent state;
	public CreatureEffectsComponent effects;

	public Creature() {
		this.physics = new CreaturePhysicsComponent(this);
		this.stats = new CreatureStatsComponent(this);
		this.state = new CreatureStateComponent(this);
		this.effects = new CreatureEffectsComponent(this);
	}

	public Creature(CreaturePhysicsComponent physics, CreatureGraphicsComponent graphics) {
		this.physics = physics;
		this.graphics = graphics;

		this.physics.setCreature(this);
		this.graphics.setCreature(this);

		this.stats = new CreatureStatsComponent(this);
		this.state = new CreatureStateComponent(this);
	}

	@Override
	public void spawn(int x, int y) {
		super.spawn(x, y);
		this.physics.setPosition(x, y);
	}

	@Override
	public void render() {
		super.render();
		this.graphics.render();
	}

	@Override
	public void update(int dt) {
		super.update(dt);

		this.physics.update(dt);
		this.stats.update(dt);
		this.graphics.update();
	}
}