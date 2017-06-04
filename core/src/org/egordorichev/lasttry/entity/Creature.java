package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.components.*;
import org.egordorichev.lasttry.entity.drop.Drop;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.graphics.particle.DamageParticle;
import org.egordorichev.lasttry.util.Util;
import org.egordorichev.lasttry.world.WorldTime;
import org.egordorichev.lasttry.world.spawn.components.CircleAreaComponent;
import org.egordorichev.lasttry.world.spawn.components.GridComponent;

import java.util.ArrayList;
import java.util.List;

public class Creature extends Entity {
	protected static final int ATTACK_INVULN_TIME = 10;
	protected int spawnWeight = 1;
	public CreatureDropsComponent drops = new CreatureDropsComponent();
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

	public void hit(int damage) {
		Globals.entityManager.spawn(new DamageParticle(false, damage), // TODO: crit?
				(int) this.physics.getCenterX() + LastTry.random.nextInt(32) - 32,
				(int) this.physics.getCenterY() + LastTry.random.nextInt(32) - 32);

		// TODO: Determine where the damage code should be
		// Should creatures share this logic or does this only apply to enemies?

		this.stats.modifyHP(-damage);
		this.stats.setInvulnTime(ATTACK_INVULN_TIME);

		// TODO: Knockback effect when damaged. Force depends on damage.
		// TODO: Rework this method so the source of the attack is known.
		// The source is needed to calculate the direction of the knockback
	}

	@Override
	public void render() {
		if (this.stats.getHp() != this.stats.getMaxHP() && this.stats.getHp() != 0) {
			this.renderHealthBar();
		}

		super.render();
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

		Graphics.batch.draw(Graphics.healthBarTexture, x + 2, this.physics.getY() - 20, mapped, 12, 0, 0, mapped, 12,
				false, false);
		Graphics.batch.setColor(1, 1, 1, 1);
		Graphics.batch.draw(Graphics.healthBarFrameTexture, x, this.physics.getY() - 20);

		Graphics.batch.setColor(1, 1, 1, 1);
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

	public boolean isInvulnrable() {
		return this.stats.getInvulnTime() > 0;
	}

	@Override
	public void onDeath() {
		this.drops.drop();
	}

	public void tryToDespawn() {
		try {
			final WorldTime currentTime = Globals.environment.time;
			final CircleAreaComponent playerActiveArea = GridComponent.retrieveActiveAreaCircle(currentTime);
			final boolean isEnemyInActiveArea = GridComponent.isCreatureInPlayerActiveArea(this, playerActiveArea);

			if(!isEnemyInActiveArea){
				Globals.entityManager.markForRemoval(this);
			}

		} catch (Exception e){
			LastTry.handleException(e);
		}
	}

	public int getSpawnWeight() {
		return spawnWeight;
	}
}