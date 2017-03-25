package org.egordorichev.lasttry.util;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;

public class Debug {
	private boolean enabled;

	public Debug() {
		this.enabled = false;
	}

	public void render() {
		if (!this.enabled) {
			return;
		}

		Assets.font.draw(LastTry.batch, String.valueOf(Gdx.graphics.getFramesPerSecond()), 10, 30);
		Assets.font.draw(LastTry.batch, "X: " + String.format("%.2f", LastTry.player.getX())
			+ " Y: " + String.format("%.2f", LastTry.player.getY()), 40, 30);
	}

	public void enable() {
		this.enabled = true;
	}

	public void disable() {
		this.enabled = false;
	}

	public void toggle() {
		this.enabled = !this.enabled;
	}

	public boolean isEnabled() {
		return this.enabled;
	}
}