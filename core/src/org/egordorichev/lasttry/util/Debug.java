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

	    Assets.f22.draw(LastTry.batch, String.valueOf(Gdx.graphics.getFramesPerSecond()), 10, 30);
	    Assets.f22.draw(LastTry.batch, "X: " + String.format("%d", LastTry.player.physics.getGridX())
            + " Y: " + String.format("%d", LastTry.player.physics.getGridY()), 40, 30);
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