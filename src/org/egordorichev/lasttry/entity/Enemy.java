package org.egordorichev.lasttry.entity;

public abstract class Enemy extends Entity {
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
		this.image.draw(this.renderBounds.x, this.renderBounds.y);
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

	public static Enemy create(String name) {
		// TODO: Create a list of constant values for names instead of local
		// string constants
		if (name == "Green Slime") {
			return new GreenSlime();
		} else {
			throw new RuntimeException("Entity with name " + name + " is not found.");
		}
	}
}
