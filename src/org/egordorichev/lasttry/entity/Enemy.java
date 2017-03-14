package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.util.Direction;

public abstract class Enemy extends Entity {
	public static final String SLIME_GREEN = "Green Slime";
	public static final String SLIME_BLUE = "Blue Slime";
	protected int currentAi;
	protected int maxAi;

	public Enemy(String name, int maxHp, int defense, int damage) {
		super(name, false, maxHp, defense, damage);

		this.currentAi = 0;
	}

	public Enemy(String name) {
		super(name, false);
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

	public static Enemy create(String name) {
		if (name.equals(Enemy.SLIME_GREEN) || name.equals(SLIME_BLUE)) {
			return new Slime(name);
		} else {
			throw new RuntimeException("Entity with name " + name + " is not found.");
		}
	}
}
