package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.entity.components.*;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.util.Util;

public class Creature extends Entity {
	public CreatureStatsComponent stats;
	public CreatureStateComponent state;
	public CreatureEffectsComponent effects;

	public Creature(CreaturePhysicsComponent physics, CreatureGraphicsComponent graphics) {
		super(physics, graphics);

		this.stats = new CreatureStatsComponent(this);
		this.state = new CreatureStateComponent(this);
		this.effects = new CreatureEffectsComponent(this);
	}

	public Creature() {
		this(new CreaturePhysicsComponent(), new CreatureGraphicsComponent());
	}

	@Override
	public void render() {
		super.render();

		if (this.stats.getHp() != this.stats.getMaxHP() && this.stats.getHp() != 0) {
			this.renderHealthBar();
		}
	}

	protected void renderHealthBar() {
		float hp = (float) this.stats.getHp() / (float) this.stats.getMaxHP();

		if (hp < 0.3f) {
			Graphics.batch.setColor(1, 0, 0, 1);
		} else if (hp < 0.6f) {
			Graphics.batch.setColor(1, 1, 0, 1);
		} else {
			Graphics.batch.setColor(0, 1, 0, 1);
		}

		int mapped = (int) Util.map(this.stats.getHp(), 0, this.stats.getMaxHP(), 0, 26);
		int x = (int) (this.physics.getX() + (this.physics.getSize().x - 28) / 2);

//		Graphics.batch.draw(Graphics.healthBarTexture, x + 2, this.physics.getY() - 20,
//			mapped, 12, 0, 0, mapped, 12, false, false);
//		Graphics.batch.setColor(1, 1, 1, 1);
//		Graphics.batch.draw(Graphics.healthBarFrameTexture, x, this.physics.getY() - 20);
	}

	@Override
	public void update(int dt) {
		super.update(dt);

		this.graphics.update(dt);
		this.stats.update(dt);

		if (this.stats.getHp() == 0) {
			this.die();
		}
	}
}