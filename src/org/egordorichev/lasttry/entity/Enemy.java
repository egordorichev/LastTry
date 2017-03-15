package org.egordorichev.lasttry.entity;

import java.util.ArrayList;
import java.util.List;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.enemy.BlueSlime;
import org.egordorichev.lasttry.entity.enemy.EyeOfCthulhu;
import org.egordorichev.lasttry.entity.enemy.GreenSlime;
import org.egordorichev.lasttry.util.Direction;

public abstract class Enemy extends Entity {
	protected int currentAi;
	protected int maxAi;
	protected List<Drop> drops = new ArrayList<>();

	public Enemy(int id, int maxHp, int defense, int damage) {
		super(id, false, maxHp, defense, damage);
	}

	public Enemy(int id) {
		super(id, false);
	}

	@Override
	public void render() {
		this.animations[this.state.getId()].getCurrentFrame().getFlippedCopy(this.direction == Direction.RIGHT, false)
				.draw(this.renderBounds.x, this.renderBounds.y);
	}

	@Override
	public void update(int dt) {
		super.update(dt);
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

	public static Enemy create(int id) {
		// TODO: think about better way to do this.
		switch (id) {
		case EntityID.none:
		default:
			return null;
		case EntityID.greenSlime:
			return new GreenSlime();
		case EntityID.blueSlime:
			return new BlueSlime();
		case EntityID.eyeOfCthulhu:
			return new EyeOfCthulhu();
		}
	}
}
