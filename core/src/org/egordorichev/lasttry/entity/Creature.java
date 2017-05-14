package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.components.*;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.util.Util;

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
		this.effects = new CreatureEffectsComponent(this);
	}

	// Pixels
	@Override
	public void spawn(int x, int y) {
		super.spawn(x, y);
		this.physics.setPosition(x, y);
	}

	@Override
	public void render() {
		super.render();
		this.graphics.render();

		if (this.stats.getHp() != this.stats.getMaxHP() && this.stats.getHp() != 0) {
			this.renderHealthBar();
		}
	}

	public void renderHealthBar() {
		float hp = (float) this.stats.getHp() / (float) this.stats.getMaxHP();

		if (hp < 0.3f) {
			Graphics.batch.setColor(1, 0, 0, 1);
		} else if (hp < 0.6f) {
			Graphics.batch.setColor(1, 1, 0, 1);
		} else {
			Graphics.batch.setColor(0, 1, 0, 1);
		}

		int mapped = (int) Util.map(this.stats.getHp(), 0, this.stats.getMaxHP(), 0, 26);

		Graphics.batch.draw(Graphics.healthBarTexture, this.physics.getX() + 2, this.physics.getY() - 20,
			mapped, 12, 0, 0, mapped, 12, false, false);
		Graphics.batch.setColor(1, 1, 1, 1);
		Graphics.batch.draw(Graphics.healthBarFrameTexture, this.physics.getX(), this.physics.getY() - 20);
	}

	@Override
	public void update(int dt) {
		super.update(dt);

		this.physics.update(dt);
		this.stats.update(dt);
		this.graphics.update();

		if (this.stats.getHp() == 0) {
			this.die();
		}
	}
}