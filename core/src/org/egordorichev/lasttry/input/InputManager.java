package org.egordorichev.lasttry.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class InputManager {
    /**
     * InputManager handler
     */
    public static InputMultiplexer multiplexer = new InputMultiplexer();

    /**
     * Last pressed mouse button
     */
    private static int currentButton = -1;

    static {
        multiplexer.addProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return Gdx.input.isKeyPressed(keycode);
            }

            @Override
            public boolean keyUp(int keycode) {
                return !Gdx.input.isKeyPressed(keycode);
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                currentButton = button;
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                currentButton = -1;

                return false;
            }

            @Override
            @Deprecated
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                //TODO: Is this what's meant in here?
                if (Gdx.input.getX() == screenX && Gdx.input.getY() == screenY) {
                    return true;
                }
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        });
    }

    public static boolean isKeyDown(int key) {
        return multiplexer.keyDown(key);
    }

    public static boolean isKeyUp(int key) {
        return multiplexer.keyUp(key);
    }

    public static boolean isKeyJustDown(int key) {
        return Gdx.input.isKeyJustPressed(key);
    }

    public static boolean mouseAt(int x, int y) {
        return multiplexer.mouseMoved(x, y);
    }

    public static boolean isMouseButtonPressed(int button) {
        return Gdx.input.isButtonPressed(button);
    }

    public static Vector2 getMousePosition() {
        return new Vector2(Gdx.input.getX(), Gdx.input.getY());
    }


    public static boolean mouseButtonJustPressed() {
        if (currentButton != -1) {
            currentButton = -1;
            return true;
        }

        return false;
    }
}
