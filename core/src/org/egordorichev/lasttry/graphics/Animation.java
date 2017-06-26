package org.egordorichev.lasttry.graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple animation class
 */
public class Animation {
	/**
	 * Animation frames
	 */
	private List<AnimationFrame> frames = new ArrayList<>();
	/**
	 * Current animation time
	 */
	private short currentTime;
	private short currentFrame;
	private boolean looped;
	private boolean stopped;

	public Animation(boolean looped) {
		this.looped = looped;
		this.currentFrame = 0;
		this.stopped = false;
	}

	public void copyFrom(Animation animation) {
		this.looped = animation.looped;
		this.stopped = animation.stopped;
		this.currentFrame = animation.currentFrame;
		this.currentTime = animation.currentTime;
		this.frames.clear();

		for (AnimationFrame frame : animation.frames) {
			TextureRegion rect = new TextureRegion(frame.region.getTexture(), frame.region.getRegionX(),
					frame.region.getRegionY(), frame.region.getRegionWidth(), frame.region.getRegionHeight());

			this.addFrame(new AnimationFrame(rect, frame.time));
		}
	}

	public void setTexture(TextureRegion texture) {
		for (AnimationFrame frame : this.frames) {
			frame.region.setTexture(texture.getTexture());
		}
	}

	public Animation copy() {
		Animation animation = new Animation(this.looped);

		animation.stopped = this.stopped;
		animation.currentFrame = this.currentFrame;
		animation.currentTime = this.currentTime;
		animation.frames.clear();

		for (AnimationFrame frame : this.frames) {
			TextureRegion rect = new TextureRegion(frame.region.getTexture(), frame.region.getRegionX(),
					frame.region.getRegionY(), frame.region.getRegionWidth(), frame.region.getRegionHeight());

			animation.addFrame(new AnimationFrame(rect, frame.time));
		}

		return animation;
	}

	public void setLooped(boolean looped) {
		this.looped = looped;
	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}

	public void addFrame(AnimationFrame frame) {
		this.frames.add(frame);
	}

	public void update() {
		if (this.frames.size() == 0 || this.stopped) {
			return;
		}

		this.currentTime++;

		if (this.currentTime > this.frames.get(this.currentFrame).time) {
			this.currentFrame++;
			this.currentTime = 0;

			if (this.currentFrame >= this.frames.size()) {
				if (!this.looped) {
					this.stopped = true;
					this.currentFrame--;
				} else {
					this.currentFrame = 0;
				}
			}
		}
	}

	public void render(float x, float y) {
		if (this.frames.size() == 0) {
			return;
		}

		AnimationFrame frame = this.frames.get(this.currentFrame);
		Graphics.batch.draw(frame.region, x, y);
	}

	public void render(float x, float y, float width, float height) {
		if (this.frames.size() == 0) {
			return;
		}

		AnimationFrame frame = this.frames.get(this.currentFrame);
		Graphics.batch.draw(frame.region, x, y, width, height);
	}

	public void render(float x, float y, float width, float height, boolean horizontalFlip,
	                   boolean verticalFlip) {

		if (this.frames.size() == 0) {
			return;
		}

		AnimationFrame frame = this.frames.get(this.currentFrame);
		frame.region.flip((frame.region.isFlipX()) != horizontalFlip,
				(frame.region.isFlipY()) != verticalFlip);

		Graphics.batch.draw(frame.region, x, y, width, height);
	}
}