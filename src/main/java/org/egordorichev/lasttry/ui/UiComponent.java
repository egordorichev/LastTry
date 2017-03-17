package org.egordorichev.lasttry.ui;

import org.egordorichev.lasttry.LastTry;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

public class UiComponent {
	public enum State {
		NORMAL,
		MOUSE_IN,
		MOUSE_DOWN
	}

	public enum Origin {
		TOP_LEFT,
		TOP_RIGHT,
		BOTTOM_LEFT,
		BOTTOM_RIGHT
	}

	protected Rectangle rect;
	protected State state;
	protected Origin origin;
	protected boolean hidden;

	public UiComponent(Rectangle rectangle, Origin origin) {
		this.rect = rectangle;
		this.state = State.NORMAL;
		this.origin = origin;
		this.hidden = false;
	}

	public UiComponent(Rectangle rectangle) {
		this(rectangle, Origin.TOP_LEFT);
	}

	public void render() {
		if(this.hidden) {
			return;
		}

		this.update();
	}

	public void setPosition(int x, int y) {
		this.rect.setX(x);
		this.rect.setY(y);
		this.applyRect();
	}

	public void setSize(int width, int height) {
		this.rect.setSize(width, height);
		this.applyRect();
	}

	public void show() {
		this.hidden = false;
	}

	public void hide() {
		this.hidden = true;
	}

	public int getX() {
		switch(this.origin) {
			case TOP_LEFT: case BOTTOM_LEFT: default: return (int) this.rect.getX();
			case TOP_RIGHT: case BOTTOM_RIGHT: return (int) (LastTry.getWindowWidth() - this.getWidth() - this.rect.getX());
		}
	}

	public int getY() {
		switch(this.origin) {
			case TOP_LEFT: case TOP_RIGHT: default: return (int) this.rect.getY();
			case BOTTOM_LEFT: case BOTTOM_RIGHT: return (int) (LastTry.getWindowHeight() - this.getHeight() - this.rect.getY());
		}
	}

	public int getWidth() {
		return (int) this.rect.getWidth();
	}

	public int getHeight() {
		return (int) this.rect.getHeight();
	}

	public State getState() {
		return this.state;
	}

	public boolean isHidden() {
		return this.hidden;
	}

	protected void update() {
		if(this.hidden) {
			return;
		}

		if(this.rect.contains(LastTry.input.getMouseX(), LastTry.input.getMouseY())) {
			if(this.state != State.MOUSE_DOWN && (LastTry.input.isMousePressed(Input.MOUSE_LEFT_BUTTON) || LastTry.input.isMousePressed(Input.MOUSE_RIGHT_BUTTON))) {
				this.state = State.MOUSE_DOWN;
				this.onStateChange();
			} else if(this.state != State.MOUSE_IN) {
				this.state = State.MOUSE_IN;
				this.onStateChange();
			}
		} else if(this.state != State.NORMAL) {
			this.state = State.NORMAL;
			this.onStateChange();
		}
	}

	protected void applyRect() {

	}

	protected void onStateChange() {

	}
}