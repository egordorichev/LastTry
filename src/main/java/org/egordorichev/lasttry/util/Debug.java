package org.egordorichev.lasttry.util;

import org.egordorichev.lasttry.LastTry;

public class Debug {
	private boolean enabled;

	public Debug() {
		this.enabled = false;
	}

	public void render() {
		if(!this.enabled) {
			return;
		}
		
		LastTry.graphics.drawString(String.valueOf(LastTry.container.getFPS()), 10, LastTry.getWindowHeight() - 30);
		LastTry.graphics.drawString("X: " + String.format("%.2f", LastTry.player.getX())
				+ " Y: " + String.format("%.2f", LastTry.player.getY()), 10, 10);
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