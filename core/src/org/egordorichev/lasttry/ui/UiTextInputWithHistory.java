package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.input.DefaultInputProcessor;
import org.egordorichev.lasttry.input.InputManager;
import org.egordorichev.lasttry.input.Keys;

import java.util.ArrayList;

public class UiTextInputWithHistory extends UiTextInput {
	/**
	 * Input history
	 */
	private ArrayList<String> history = new ArrayList<>();
	/**
	 * History index
	 */
	private int historyIndex = 0;

	public UiTextInputWithHistory(Rectangle rectangle, Origin origin) {
		super(rectangle, origin, null);
		InputManager.multiplexer.addProcessor(new DefaultInputProcessor() {
			@Override
			public boolean keyDown(int keycode) {
				if (ignoreInput) {
					return false;
				}

				if (keycode == Keys.ERASE_TEXT && text.length() > 0 && cursorX > 0) {
					StringBuilder builder = new StringBuilder(text);
					builder.deleteCharAt(cursorX - 1);
					text = builder.toString();
					cursorX -= 1;
					updateLabel();
				} else if (keycode == Input.Keys.FORWARD_DEL && cursorX < text.length()) {
					StringBuilder builder = new StringBuilder(text);
					builder.deleteCharAt(cursorX);
					text = builder.toString();
					updateLabel();
				} else if (keycode == Input.Keys.ENTER) {
					/*
					if (history.size() == 0 || (!history.get(history.size() - 1).equals(text))) {
						history.add(text);
						historyIndex = history.size();
					}
					*/
					history.add(text);
					historyIndex = history.size();
					onEnter();
				} else if (keycode == Input.Keys.LEFT) {
					cursorX = Math.max(0, cursorX - 1);
					updateLabel();
				} else if (keycode == Input.Keys.RIGHT) {
					cursorX = Math.min(text.length(), cursorX + 1);
					updateLabel();
				} else if (keycode == Input.Keys.UP) {
					if (historyIndex > 0) {
						historyIndex -= 1;
						text = history.get(historyIndex);
						System.out.println(text);
						cursorX = text.length();
						updateLabel();
					}
				} else if (keycode == Input.Keys.DOWN) {
					if (historyIndex < history.size() - 1) {
						historyIndex += 1;
						text = history.get(historyIndex);
						System.out.println(text);
						cursorX = text.length();
						updateLabel();
					}
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
}