package org.egordorichev.lasttry.entity;

import java.util.ArrayList;
import java.util.List;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.enemy.BlueSlime;
import org.egordorichev.lasttry.entity.enemy.EyeOfCthulhu;
import org.egordorichev.lasttry.entity.enemy.GreenSlime;
import org.egordorichev.lasttry.util.Direction;
import org.newdawn.slick.Animation;

public abstract class Enemy extends Entity {
	protected int currentAi;
	protected int maxAi;
	protected int id;
	protected List<Drop> drops = new ArrayList<>();
	protected Animation[] animations;

	public Enemy(int id, int maxHp, int defense, int damage) {
		super(maxHp, defense, damage);

		this.animations = new Animation[State.values().length];
		this.id = id;
	}

	public Enemy(int id) {
		super();

		this.animations = new Animation[State.values().length];
		this.id = id;
	}

	@Override
	public void render() {
		this.animations[this.state.getId()].getCurrentFrame().getFlippedCopy(this.direction == Direction.RIGHT, false)
			.draw(this.renderBounds.x, this.renderBounds.y);
	}

	@Override
	public void update(int dt) {
		super.update(dt);
		this.animations[this.state.getId()].update(dt);
		this.updateAI();
	}

	public void updateAI() {
		this.currentAi++;

		if (this.currentAi >= this.maxAi) {
			this.currentAi = 0;
		}
	}

	@Override
	public void onSpawn() {

	}

	@Override
	public void onDeath() {
		// On death, drop items in world.
		for (Drop drop : this.drops) {
			// Some items may not be dropped due to rarity
			if (drop.getChance().roll()){
				LastTry.world.spawnDrop(drop, this.hitbox.x, this.hitbox.y);
			}
		}
	}

	public int getId() {
		return this.id;
	}

	public static Enemy create(int id) {
		// TODO: think about better way to do this.

		switch(id) {
			case EnemyID.none:
			default:
				return null;
			case EnemyID.greenSlime:
				return new GreenSlime();
			case EnemyID.blueSlime:
				return new BlueSlime();
			case EnemyID.eyeOfCthulhu:
				return new EyeOfCthulhu();
		}
	}
}
