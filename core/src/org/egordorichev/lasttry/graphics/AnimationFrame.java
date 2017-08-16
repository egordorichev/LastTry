package org.egordorichev.lasttry.graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Basic animation frame holder
 */
public class AnimationFrame {
	/**
	 * Texture region
	 */
	public TextureRegion region;
	/**
	 * Frame time
	 */
	public int time;

	public AnimationFrame(TextureRegion region, int time) {
		this.region = region;
		this.time = time;
	}
}