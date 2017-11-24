package org.egordorichev.lasttry.graphics.animation;

import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.util.log.Log;

import java.util.ArrayList;

/**
 * Handles animation
 */
public class Animation {
	/**
	 * List of the frames
	 */
	private ArrayList<AnimationFrame> frames = new ArrayList<>();
	/**
	 * Current time
	 */
	private float time;
	/**
	 * Current animation frame;
	 */
	private int frame;

	public Animation() {

	}

	/**
	 * Renders the animation
	 *
	 * @param x Animation X
	 * @param y Animation Y
	 */
	public void render(float x, float y) {
		AnimationFrame frame = this.frames.get(this.frame);

		if (frame != null) {
			Graphics.batch.draw(frame.texture, x, y);
		}
	}

	/**
	 * Updates the animation
	 *
	 * @param delta Time, since the last frame
	 */
	public void update(float delta) {
		this.time += delta;
		AnimationFrame frame = this.frames.get(this.frame);

		if (frame != null) {
			if (this.time > frame.delta) {
				this.time = 0.0f;
				this.frame = (this.frame + 1) % this.frames.size();
			}
		}
	}

	/**
	 * Adds given frame to animation
	 *
	 * @param frame New frame
	 */
	public void addFrame(AnimationFrame frame) {
		this.frames.add(frame);
	}

	/**
	 * Resets the animation
	 */
	public void reset() {
		this.frame = 0;
		this.time = 0;
	}
}