package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.entity.CreatureWithAI;

public class AiComponent extends EntityComponent {
	private int currentAi = 0;
	private int maxAi = 0;

	public AiComponent(CreatureWithAI creature) {
		super(creature);
	}

	public void update(int dt) {
		this.currentAi++;

		((CreatureWithAI) this.entity).updateAI();

		if (this.currentAi >= this.maxAi) {
			this.currentAi = 0;
		}
	}

	public void setMax(int maxAi) {
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