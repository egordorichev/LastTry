package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.util.Direction;

public abstract class Enemy extends Entity {
	protected int currentAi;
	protected int maxAi;

	public Enemy(int id, int maxHp, int defense, int damage) {
		super(id, false, maxHp, defense, damage);

		this.currentAi = 0;
	}

	public Enemy(int id) {
		super(id, false);
		this.currentAi = 0;
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

	public static Enemy create(int id) { // TODO: think about better way to do this.
		switch(id) {
			case EntityID.none: defaul: return null;
			case EntityID.greenSlime: return new GreenSlime();
			case EntityID.blueSlime: return new BlueSlime();
		}
	}
}
