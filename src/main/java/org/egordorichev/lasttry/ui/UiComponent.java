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
		BOTTOM_RIGHT,
		CENTER
	}

	/** 
	 * Holds element position and size 
	 */
	protected Rectangle rect;
	
	/**
	 * Holds element state
	 */
	protected State state;
	
	/**
	 * Shows, from witch side of the screen position coordinates are calculated
	 */
	protected Origin origin;
	
	/**
	 * Element doen't draw and react to input, if it is hidden
	 */
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

	/** Renders component and updates it */
	public void render() {
		if (this.hidden) {
			return;
		}

		this.update();
	}

	/** Shows element */
	public void show() {
		this.hidden = false;
	}

	/** Hides element */
	public void hide() {
		this.hidden = true;
	}

	public int getX() {
		switch(this.origin) {
			case TOP_LEFT: case BOTTOM_LEFT: default: return (int) this.rect.getX();
			case TOP_RIGHT: case BOTTOM_RIGHT: return (int) (LastTry.getWindowWidth() - this.getWidth() - this.rect.getX());
			case CENTER: return (int) (this.rect.getX() + (LastTry.getWindowWidth() - this.getWidth()) / 2);
		}
	}

	public int getY() {
		switch(this.origin) {
			case TOP_LEFT: case TOP_RIGHT: default: return (int) this.rect.getY();
			case BOTTOM_LEFT: case BOTTOM_RIGHT: return (int) (LastTry.getWindowHeight() - this.getHeight() - this.rect.getY());
			case CENTER: return (int) (this.rect.getY() + (LastTry.getWindowHeight() - this.getHeight()) / 2);
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
		if (this.hidden) {
			return;
		}

		Rectangle rectangle = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());

		int x = LastTry.input.getMouseX();
		int y = LastTry.input.getMouseY();

		if (rectangle.getX() < x && rectangle.getY() < y &&
				rectangle.getX() + rectangle.getWidth() > x  &&
				rectangle.getY() + rectangle.getHeight() > y) {

			if (this.state != State.MOUSE_DOWN && (LastTry.input.isMousePressed(Input.MOUSE_LEFT_BUTTON) ||
					LastTry.input.isMousePressed(Input.MOUSE_RIGHT_BUTTON))) {

				this.state = State.MOUSE_DOWN;
				this.onStateChange();
				this.onClick();
			} else if (this.state != State.MOUSE_IN) {
				this.state = State.MOUSE_IN;
				this.onStateChange();
			}
		} else if (this.state != State.NORMAL) {
			this.state = State.NORMAL;
			this.onStateChange();
		}
	}

	protected void applyRect() {

	}

	protected void onStateChange() {

	}

	public void onClick() {

	}
}