package org.egordorichev.lasttry.effect;

import org.egordorichev.lasttry.entity.creature.Creature;

public class EffectData {
	/**
	 * Effect, witch is being handled
	 */
	private Effect effect;
	/**
	 * Time, left for the effect
	 */
	private int time;
	/**
	 * Creature, who has the effect
	 */
	private Creature creature;
	/**
	 * Effect has ended
	 */
	private boolean done;

	public EffectData(Creature creature, Effect effect, int time) {
		this.effect = effect;
		this.creature = creature;
		this.setTime(time);
		this.effect.apply(this.creature);
	}

	/**
	 * Renders effect icon
	 *
	 * @param x Render X
	 * @param y Render Y
	 */
	public void render(int x, int y) {
		this.effect.render(x, y);
	}

	/**
	 * Updates effect
	 *
	 * @param dt Time, since last update
	 * @return Effect is done
	 */
	public boolean update(int dt) {
		if (this.done) {
			return true;
		}

		this.time--;
		this.effect.update(this.creature, dt);

		if (this.time == 0) {
			this.done = true;
			this.effect.remove(this.creature);
			return true;
		}

		return false;
	}

	/**
	 * Sets effect time
	 *
	 * @param time New time
	 */
	public void setTime(int time) {
		this.time = time * 60;
	}

	/**
	 * @return The effect
	 */
	public Effect getEffect() {
		return this.effect;
	}

	/**
	 * @return Effect is done
	 */
	public boolean isDone() {
		return this.done;
	}
}