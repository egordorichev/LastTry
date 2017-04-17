package org.egordorichev.lasttry.entity.enemy;

import org.egordorichev.lasttry.entity.components.EntityComponent;

public class AiComponent extends EntityComponent {
	private int currentAi = 0;
	private int maxAi = 0;

	public AiComponent(Enemy enemy) {
		super(enemy);
	}

	public void update(int dt) {
		this.currentAi++;

		((Enemy) this.entity).updateAI();

		if (this.currentAi >= this.maxAi) {
			this.currentAi = 0;
		}
	}

	public void setMaxAi(int maxAi) {
		this.maxAi = maxAi;
		this.currentAi = 0;
	}

	public int get() {
		return this.currentAi;
	}

	public int getMax() {
		return this.maxAi;
	}
}