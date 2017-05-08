package org.egordorichev.lasttry.graphics;

import java.util.ArrayList;
import java.util.List;

public class Animation {
    private List<AnimationFrame> frames = new ArrayList<>();
    private int currentTime;
    private int currentFrame;
    private boolean looped;
    private boolean stopped;

    public Animation(boolean looped) {
        this.looped = looped;
        this.currentFrame = 0;
        this.stopped = false;
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