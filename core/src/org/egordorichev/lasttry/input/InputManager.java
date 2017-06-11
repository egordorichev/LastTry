package org.egordorichev.lasttry.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.Globals;

public class InputManager {
    /** InputManager handler */
    public static InputMultiplexer multiplexer = new InputMultiplexer();

    /** Last pressed mouse button */
    private static int currentButton = -1;

    static {
        multiplexer.addProcessor(new DefaultInputProcessor() {
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
        });
    }

    public static boolean isKeyDown(int key) {
    	if (key != Keys.DEBUG_MODE && Globals.chat.isOpen()) {
    		return false;
	    }

        return Gdx.input.isKeyPressed(key);
    }

    public static boolean isKeyUp(int key) {
	    if (Globals.chat.isOpen()) {
		    return true;
	    }

    	return !Gdx.input.isKeyPressed(key);
    }

    public static boolean isKeyJustDown(int key) {
	    if (key != Keys.DEBUG_MODE && Globals.chat.isOpen()) {
		    return false;
	    }

        return Gdx.input.isKeyJustPressed(key);
    }

    public static int getCurrentKeyDown() {
	    if (Globals.chat.isOpen()) {
		    return -2;
	    }

    	for(int i = 0; i < 255; i++){
    		if(Gdx.input.isKeyPressed(i)){
    			return i;
			}
		}

		return -2;
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
