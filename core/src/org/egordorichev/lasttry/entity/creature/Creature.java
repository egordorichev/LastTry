package org.egordorichev.lasttry.entity.creature;

import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.graphics.particle.DamageParticle;
import org.egordorichev.lasttry.item.items.Tool;
import org.egordorichev.lasttry.language.Language;
import org.egordorichev.lasttry.util.Util;
import org.egordorichev.lasttry.world.WorldTime;
import org.egordorichev.lasttry.world.spawn.components.CircleAreaComponent;
import org.egordorichev.lasttry.world.spawn.components.GridComponent;

public class Creature extends Entity {
	protected static final int ATTACK_INVULN_TIME = 10;
	/**
	 * Drops handler
	 */
	public CreatureDropsComponent drops = new CreatureDropsComponent(this);
	/**
	 * Stats handler
	 */
	public CreatureStatsComponent stats;
	/**
	 * State handler
	 */
	public CreatureStateComponent state;
	/**
	 * Effects handler
	 */
	public CreatureEffectsComponent effects;
	/**
	 * Creature spawn weight
	 */
	protected int spawnWeight = 1;
	/**
	 * Creature id (for ex. lt:green_slime)
	 */
	protected String id;
	/**
	 * Creature name (for ex. Green Slime)
	 */
	protected String name;

	public Creature(String id, CreaturePhysicsComponent physics, CreatureGraphicsComponent graphics) {
		super(physics, graphics);

		this.id = id;
		this.name = Language.text.get(this.id);

		this.stats = new CreatureStatsComponent(this);
		this.state = new CreatureStateComponent(this);
		this.effects = new CreatureEffectsComponent(this);
	}

	public Creature(String id) {
		this(id, new CreaturePhysicsComponent(), new CreatureGraphicsComponent());
	}

	/**
	 * Hits the creature
	 *
	 * @param damage HP to remove from health
	 */
	public void hit(int damage, boolean crit) {
		Globals.entityManager.spawn(new DamageParticle(crit, damage),
				(int) this.physics.getCenterX() + LastTry.random.nextInt(32) - 32,
				(int) this.physics.getCenterY() + LastTry.random.nextInt(32) - 32);

		this.stats.modifyHP(-damage);
		this.stats.setInvulnTime(ATTACK_INVULN_TIME);
		if (this.stats.getHP() <= 0) {
			this.die();
		}
	}

	/**
	 * Hits the creature
	 *
	 * @param damage HP to remove from health
	 */
	public void hit(int damage) {
		this.hit(damage, false);
	}

	/**
	 * Renders the creature
	 */
	@Override
	public void render() {
		if (this.stats.getHP() != this.stats.getMaxHP() && this.stats.getHP() != 0) {
			this.renderHealthBar();
		}
		super.render();
	}

	/**
	 * Renders the health bar
	 */
	protected void renderHealthBar() {
		float hp = (float) this.stats.getHP() / (float) this.stats.getMaxHP();

		if (hp < 0.3f) {
			Graphics.batch.setColor(1, 0, 0, 1);
		} else if (hp < 0.6f) {
			Graphics.batch.setColor(1, 1, 0, 1);
		} else {
			Graphics.batch.setColor(0, 1, 0, 1);
		}

		int mapped = (int) Util.map(this.stats.getHP(), 0, this.stats.getMaxHP(), 0, 26);
		int x = (int) (this.physics.getX() + (this.physics.getSize().x - 28) / 2);

		Graphics.batch.draw(Graphics.healthBarTexture, x + 2, this.physics.getY() - 20, mapped, 12, 0, 0, mapped, 12,
				false, false);
		Graphics.batch.setColor(1, 1, 1, 1);
		Graphics.batch.draw(Graphics.healthBarFrameTexture, x, this.physics.getY() - 20);

		Graphics.batch.setColor(1, 1, 1, 1);
	}

	/**
	 * Updates the creature
	 */
	@Override
	public void update(int dt) {
		super.update(dt);

		if (!this.isActive()) {
			return;
		}

		this.graphics.update(dt);
		this.stats.update(dt);
		this.effects.update(dt);

		if (this.stats.getHP() == 0) {
			this.die();
		}
	}

	/**
	 * @return Creature can't be damaged
	 */
	public boolean isInvulnrable() {
		return this.stats.getInvulnTime() > 0;
	}

	@Override
	public void onDeath() {
		this.drops.drop();
	}

	/**
	 * Tries to despawn creature
	 */
	public void tryToDespawn() {
		try {
			final WorldTime currentTime = Globals.environment.time;
			final CircleAreaComponent playerActiveArea = GridComponent.retrieveActiveAreaCircle(currentTime);
			final boolean isEnemyInActiveArea = GridComponent.isCreatureInPlayerActiveArea(this, playerActiveArea);

			if (!isEnemyInActiveArea) {
				Globals.entityManager.markForRemoval(this);
			}

		} catch (Exception e) {
			LastTry.handleException(e);
		}
	}

	/**
	 * @return Creature spawn weight
	 */
	public int getSpawnWeight() {
		return spawnWeight;
	}

	/**
	 * Sets creature spawn weight
	 *
	 * @param spawnWeight How much will creature "eat" from spawn rate
	 */
	public void setSpawnWeight(int spawnWeight) {
		this.spawnWeight = spawnWeight;
	}

	/**
	 * @return Creature name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Creature ID
	 */
	public String getID() {
		return id;
	}

	/**
	 * Attacks the given enemy with the given damage.
	 *
	 * @param enemy  Enemy to attack.
	 * @param damage Damage to inflict.
	 */
	public void attack(Creature enemy, int damage) {
		applyKnockback(enemy, damage);
		enemy.hit(damage);
	}

	/**
	 * Knocks the enemy back according to the given damage delt.
	 *
	 * @param enemy  Enemy to knock back.
	 * @param damage Damage delt, used to compute knockback power.
	 */
	private void applyKnockback(Creature enemy, int damage) {
		float knockPower = damage * -0.04F;
		Vector2 diff = physics.getPosition().cpy().sub(enemy.physics.getPosition().cpy());
		Vector2 force = diff.scl(knockPower);
		force.limit(Tool.KNOCKBACK_MAX_POWER);
		enemy.physics.getVelocity().add(force);
	}
}