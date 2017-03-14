package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.util.Direction;

public abstract class Enemy extends Entity {
	protected int currentAi;
	protected int maxAi;
	protected Drop drop;

	public Enemy(int id, int maxHp, int defense, int damage) {
		super(id, false, maxHp, defense, damage);

		this.currentAi = 0;
		this.drop = new Drop();
	}

	public Enemy(int id) {
		super(id, false);

		this.currentAi = 0;
		this.drop = new Drop();
	}

	@Override
	public void render() {
		this.animations[this.state.getId()]
				.getCurrentFrame().getFlippedCopy(this.direction == Direction.RIGHT, false)
				.draw(this.renderBounds.x, this.renderBounds.y);
	}

	@Override
	public void update(int dt) {
		super.update(dt);
		this.updateAI();
	}

	public void updateAI() {
		this.currentAi++;

		if(this.currentAi >= this.maxAi) {
			this.currentAi = 0;
		}
	}

	@Override
	public void onSpawn() {

	}

	@Override
	public void onDeath() {
		// TODO: LastTry.world.addDrop(this.drop);
	}

	public static Enemy create(int id) { // TODO: think about better way to do this.
		switch(id) {
			case EntityID.none: default: return null;
			case EntityID.greenSlime: return new GreenSlime();
			case EntityID.blueSlime: return new BlueSlime();
			case EntityID.eyeOfCthulhu: return new EyeOfCthulhu();
		}
	}
}
