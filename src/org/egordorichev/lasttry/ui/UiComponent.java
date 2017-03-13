package org.egordorichev.lasttry.ui;

import org.egordorichev.lasttry.LastTry;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import java.util.Objects;

public class UiComponent {
	public enum State {
		NORMAL,
		MOUSE_IN,
		MOUSE_DOWN
	}

	protected Rectangle rect;
	protected State state;
	protected boolean hidden;

	public UiComponent(Rectangle rectangle) {
		this.rect = rectangle;
		this.state = State.NORMAL;
		this.hidden = false;
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
		return (int) this.rect.getX();
	}

	public int getY() {
		return (int) this.rect.getY();
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
			if(this.state != State.MOUSE_DOWN && (LastTry.input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || LastTry.input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON))) {
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