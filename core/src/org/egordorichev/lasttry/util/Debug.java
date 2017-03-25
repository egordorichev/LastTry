package org.egordorichev.lasttry.util;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;

public class Debug {
	/** Shows, if debug mode is activated */
	private boolean enabled;

	public Debug() {
		this.enabled = false;
	}

	/** Renders all debug information */
	public void render() {
		if (!this.enabled) {
			return;
		}

		Assets.font.draw(LastTry.batch, String.valueOf(Gdx.graphics.getFramesPerSecond()), 10, 30);
		Assets.font.draw(LastTry.batch, "X: " + String.format("%.2f", LastTry.player.getGridX())
			+ " Y: " + String.format("%.2f", LastTry.player.getGridY()), 40, 30);
	}

	/** Enables debug */
	public void enable() {
		this.enabled = true;
	}

	/** Disables debug */
	public void disable() {
		this.enabled = false;
	}

	/** Sets debug state to opposite */
	public void toggle() {
		this.enabled = !this.enabled;
	}

	/**
	 * Returns true, if debug is enabled
	 * @return is debug enabled
	 */
	public boolean isEnabled() {
		return this.enabled;
	}
}