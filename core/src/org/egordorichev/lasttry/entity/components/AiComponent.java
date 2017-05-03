package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.entity.CreatureWithAI;
import org.egordorichev.lasttry.entity.ai.AI;

public class AiComponent extends CreatureComponent {
	private int currentAi = 0;
	private int maxAi = 0;
	private CreatureWithAI creature;
	private AI ai;
	private short data;

	public AiComponent(CreatureWithAI creature, AI ai) {
		super(creature);

		this.creature = creature;
		this.ai = ai;
	}

	public void update(int dt) {
		this.currentAi++;

		this.ai.update(this.creature, dt, this.currentAi);

		if (this.currentAi >= this.maxAi) {
			this.currentAi = 0;
		}
	}

	public void setMax(int maxAi) {
		this.maxAi = maxAi;
		this.currentAi = 0;
	}

	public void set(int ai) {
		this.currentAi = Math.min(this.maxAi, ai);
	}

	public void setData(short data) {
		this.data = data;
	}

	public int get() {
		return this.currentAi;
	}

	public int getMax() {
		return this.maxAi;
	}

	public short getData() {
		return this.data;
	}
}