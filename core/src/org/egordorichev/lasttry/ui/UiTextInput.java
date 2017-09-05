package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

import org.egordorichev.lasttry.input.DefaultInputProcessor;
import org.egordorichev.lasttry.input.InputManager;
import org.egordorichev.lasttry.input.Keys;

import java.util.Objects;

public class UiTextInput extends UiTextLabel {
	/**
	 * Current text string
	 */
	private String text = "";
	/**
	 * Cursor position
	 */
	private int cursorX = 0;
	/**
	 * Ignore input
	 */
	private boolean ignoreInput = false;

	public UiTextInput(Rectangle rectangle, Origin origin) {
		super(rectangle, origin, "|");

		InputManager.multiplexer.addProcessor(new DefaultInputProcessor() {
			@Override
			public boolean keyDown(int keycode) {
				if (ignoreInput) {
					return false;
				}

				if (keycode == Keys.ERASE_TEXT && text.length() > 0) {
					/* StringBuilder builder = new StringBuilder(text); // FIXME: ArrayOutOfBounds sometimes
					builder.deleteCharAt(cursorX);
					text = builder.toString();
					cursorX -= 1;
					updateLabel();
				} else if (keycode == Input.Keys.DEL && cursorX < text.length()) {
						StringBuilder builder = new StringBuilder(text);
						builder.deleteCharAt(cursorX + 1);
						text = builder.toString();
						updateLabel(); */
				} else if (keycode == Input.Keys.ENTER) {
					onEnter();
				} else if (keycode == Input.Keys.LEFT) {
					cursorX = Math.max(0, cursorX - 1);
					updateLabel();
				} else if (keycode == Input.Keys.RIGHT) {
					cursorX = Math.min(text.length(), cursorX + 1);
					updateLabel();
				}

				return false;
			}

			@Override
			public boolean keyTyped(char character) {
				if (ignoreInput) {
					return false;
				}

				if (Character.isIdentifierIgnorable(character)) {
					return false;
				}

				type(character + "");

				return false;
			}
		});
	}

	public String getText() {
		return this.text;
	}

	public void onEnter() {

	}

	public void setIgnoreInput(boolean ignoreInput) {
		this.ignoreInput = ignoreInput;
	}

	public void type(String text) {
		StringBuilder builder = new StringBuilder(this.text);
		builder.insert(cursorX, text);
		cursorX += 1;
		this.text = builder.toString();

		updateLabel();
	}

	public void clear() {
		this.text = "";
		this.cursorX = 0;
		updateLabel();
	}

	public void setCursorX(int cursorX) {
		this.cursorX = cursorX;
	}

	private void updateLabel() {
		StringBuilder builder = new StringBuilder(this.text);
		builder.insert(cursorX, '|');
		this.setLabel(builder.toString());
	}
}