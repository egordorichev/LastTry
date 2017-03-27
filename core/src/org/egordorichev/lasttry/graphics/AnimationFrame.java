package org.egordorichev.lasttry.graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationFrame {
	public TextureRegion region;
	public int time;

	public AnimationFrame(TextureRegion region, int time) {
		this.region = region;
		this.time = time;
	}
}