package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.util.Util;

public class UiTextInput extends UiTextLabel {
    /**
     * Current text string
     */
    private String text = "";

    public UiTextInput(Rectangle rectangle, Origin origin) {
        super(rectangle, origin, "|");

        Util.multiplexer.addProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.BACKSPACE && text.length() > 0) {
                    text = text.substring(0, text.length() - 1);
                    setLabel(text + "|");
                }

                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                if (Character.isIdentifierIgnorable(character)) {
                    return false;
                }

                text += character;
                setLabel(text + "|");

                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        });
    }

    public String getText() {
        return this.text;
    }

    public void clear() {
        this.text = "";
        this.setLabel(this.text + "|");
    }

    // TODO: focus
}