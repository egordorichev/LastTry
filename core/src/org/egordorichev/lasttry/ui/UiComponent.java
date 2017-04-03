package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.util.Util;

public class UiComponent {
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

    /**
     * Renders component and updates it
     */
    public void render() {
        if (this.hidden) {
            return;
        }

        this.update();
    }

    /**
     * Shows element
     */
    public void show() {
        this.hidden = false;
        this.onShow();
    }

    /**
     * Hides element
     */
    public void hide() {
        this.hidden = true;
        this.onHide();
    }

    public void onShow() {

    }

    public void onHide() {

    }

    public void setPosition(int x, int y) {
        this.rect.setPosition(x, y);
    }

    public void setSize(int width, int height) {
        this.rect.setSize(width, height);
    }

    public Rectangle getRect() {
        return this.rect;
    }

    public int getX() {
        switch (this.origin) {
            case TOP_LEFT:
            case BOTTOM_LEFT:
            default:
                return (int) this.rect.getX();
            case TOP_RIGHT:
            case BOTTOM_RIGHT:
                return (int) (Gdx.graphics.getWidth() - this.getWidth() - this.rect.getX());
            case CENTER:
                return (int) (this.rect.getX() + (Gdx.graphics.getWidth() - this.getWidth()) / 2);
        }
    }

    public int getClickY() {
        switch (this.origin) {
            case TOP_LEFT:
            case TOP_RIGHT:
            default:
                return (int) this.rect.getY();
            case BOTTOM_LEFT:
            case BOTTOM_RIGHT:
                return (int) (Gdx.graphics.getHeight() - this.getHeight() - this.rect.getY());
            case CENTER:
                return (int) (this.rect.getY() + (Gdx.graphics.getHeight() - this.getHeight()) / 2);
        }
    }

    public int getY() {
        switch (this.origin) {
            case TOP_LEFT:
            case TOP_RIGHT:
            default:
                return (int) (Gdx.graphics.getHeight() - this.rect.getY() - this.getHeight());
            case BOTTOM_LEFT:
            case BOTTOM_RIGHT:
                return (int) ((this.rect.getY()));
            case CENTER:
                return (int) (Gdx.graphics.getHeight() - (this.rect.getY() + (Gdx.graphics.getHeight() - this.getHeight()) / 2));
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
        Rectangle rectangle = new Rectangle(this.getX(), this.getClickY(), this.getWidth(), this.getHeight());

        int x = Gdx.input.getX();
        int y = Gdx.input.getY();

        if (rectangle.getX() < x && rectangle.getY() < y &&
                rectangle.getX() + rectangle.getWidth() > x &&
                rectangle.getY() + rectangle.getHeight() > y) {

            if (this.state != State.MOUSE_DOWN && Util.mouseButtonJustPressed()) {
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
}