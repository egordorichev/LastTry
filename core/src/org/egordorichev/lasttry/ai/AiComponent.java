package org.egordorichev.lasttry.ai;

import org.egordorichev.lasttry.entity.CreatureWithAI;
import org.egordorichev.lasttry.entity.creature.CreatureComponent;

public class AiComponent extends CreatureComponent {
	/**
	 * Creature AI
	 */
	public AI ai;
	/**
	 * Current AI step
	 */
	private int currentAi = 0;
	/**
	 * Maximum AI step
	 */
	private int maxAi = 0;
	/**
	 * Creature, that is being served
	 */
	private CreatureWithAI creature;
	/**
	 * Filed, containing different AI data
	 */
	private byte data;

	public AiComponent(CreatureWithAI creature, AI ai) {
		super(creature);

		this.creature = creature;
		this.ai = ai;
	}

	/**
	 * Updates AI
	 *
	 * @param dt Time, since last update
	 */
	public void update(int dt) {
		this.currentAi++;

		if (this.currentAi >= this.maxAi) {
			this.currentAi = 0;
		}

		this.ai.update(this.creature, dt, this.currentAi);
	}

	/**
	 * Sets current step
	 *
	 * @param ai New step
	 */
	public void set(int ai) {
		this.currentAi = Math.min(this.maxAi, ai);
	}

	/**
	 * @return Current step
	 */
	public int get() {
		return this.currentAi;
	}

	/**
	 * @return Max step
	 */
	public int getMax() {
		return this.maxAi;
	}

	/**
	 * Sets maximum AI step
	 *
	 * @param maxAi Maximum AI step
	 */
	public void setMax(int maxAi) {
		this.maxAi = maxAi;
		this.currentAi = 0;
	}

	/**
	 * @return AI data
	 */
	public byte getData() {
		return this.data;
	}

	/**
	 * Sets data
	 *
	 * @param data New data
	 */
	public void setData(byte data) {
		this.data = data;
	}
}