package org.egordorichev.lasttry.graphics.animation;

import java.util.ArrayList;

/**
 * Handles animation
 */
public class Animation {
	/**
	 * List of the frames
	 */
	private ArrayList<AnimationFrame> frames = new ArrayList<>();

	public Animation() {

	}

	/**
	 * Adds given frame to animation
	 *
	 * @param frame New frame
	 */
	public void addFrame(AnimationFrame frame) {
		this.frames.add(frame);
	}
}