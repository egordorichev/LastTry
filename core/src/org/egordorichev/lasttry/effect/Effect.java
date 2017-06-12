package org.egordorichev.lasttry.effect;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.entity.creature.Creature;
import org.egordorichev.lasttry.graphics.Graphics;

public abstract class Effect {
	/**
	 * Can the effect be removed by right-click
	 */
	protected boolean canBeRemoved;
	/**
	 * Effect name
	 */
	protected String name;
	/**
	 * Effect description
	 */
	protected String description;
	/**
	 * Effect icon
	 */
	protected TextureRegion texture;

	public Effect(String name, String description, TextureRegion texture) {
		this.name = name;
		this.canBeRemoved = true;
		this.description = description;
		this.texture = texture;
	}

	/**
	 * Renders effect icon
	 *
	 * @param x Icon X
	 * @param y Icon Y
	 */
	public void render(int x, int y) {
		Graphics.batch.draw(this.texture, x, y);
	}

	public abstract void apply(Creature creature);

	public abstract void remove(Creature creature);

	/**
	 * Updates effect
	 *
	 * @param creature Creature, who has the effect
	 * @param dt Time, since last update
	 */
	public void update(Creature creature, int dt) {

	}

	/**
	 * @return Effect name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return Effect description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return Can the effect be removed
	 */
	public boolean canBeRemoved() {
		return this.canBeRemoved;
	}
}