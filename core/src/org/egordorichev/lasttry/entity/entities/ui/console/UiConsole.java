package org.egordorichev.lasttry.entity.entities.ui.console;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.entities.ui.UiElement;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.util.geometry.Rectangle;
import org.egordorichev.lasttry.util.input.SimpleInputProcessor;
import org.egordorichev.lasttry.util.log.Log;

import java.awt.event.KeyEvent;
import java.util.Objects;

/**
 * Super useful command line debug thingy
 */
public class UiConsole extends UiElement implements SimpleInputProcessor {
	public UiConsole(Rectangle rect) {
		super(rect, ConsoleCommandsComponent.class, ConsoleStateComponent.class,
			ConsoleLinesComponent.class);

		org.egordorichev.lasttry.util.input.Input.multiplexer.addProcessor(this);
	}

	/**
	 * Renders the console
	 */
	@Override
	public void renderUi() {
		ConsoleStateComponent state = this.getComponent(ConsoleStateComponent.class);

		if (state.open) {
			PositionComponent position = this.getComponent(PositionComponent.class);
			Assets.f7.draw(Graphics.batch, "> " + state.input, position.x, position.y + 7);

			ConsoleLinesComponent lines = this.getComponent(ConsoleLinesComponent.class);
			float delta = Gdx.graphics.getDeltaTime();

			for (int i = lines.lines.size() - 1; i >= 0; i--) {
				ConsoleLine line = lines.lines.get(i);
				Assets.f7.draw(Graphics.batch, line.line, position.x, position.y + (i + 2) * 7);

				line.time += delta;

				if (line.time > ConsoleLine.EXPIRE_TIME) {
					lines.lines.remove(i);
				}
			}
		}
	}

	public void print(String string) {
		ConsoleLinesComponent lines = this.getComponent(ConsoleLinesComponent.class);
		ConsoleLine line = new ConsoleLine();

		line.line = string;
		lines.lines.add(0, line);
	}

	/**
	 * Runs a command
	 *
	 * @param input User input
	 */
	protected void runCommand(String input) {
		input = input.trim();

		if (Objects.equals(input, "")) {
			return;
		}

		if (!input.startsWith("/")) {
			this.print(input);
			return;
		}

		String[] parts = input.split("\\s+");
		String name = parts[0];
		ConsoleCommandsComponent commands = this.getComponent(ConsoleCommandsComponent.class);
		ConsoleCommand cmd = null;

		for (ConsoleCommand command : commands.commands) {
			if (Objects.equals("/" + command.getName(), name)) {
				cmd = command;
				break;
			}
		}

		if (cmd == null) {
			this.print("Unknown command");
			return;
		}

		String[] args = new String[parts.length - 1];

		for (int i = 0; i < args.length; i++) {
			args[i] = parts[i + 1];
		}

		cmd.run(this, args);
	}

	@Override
	public boolean keyDown(int key) {
		ConsoleStateComponent state = this.getComponent(ConsoleStateComponent.class);

		if (key == Assets.keys.get("open_console")) {
			state.open = true;
			org.egordorichev.lasttry.util.input.Input.blocked = true;
		} else if (key == Assets.keys.get("close_console")) {
			state.open = false;
			org.egordorichev.lasttry.util.input.Input.blocked = false;
		} else if (key == Input.Keys.ENTER) {
			this.runCommand(state.input);
			state.input = "";
		} else if (key == Input.Keys.BACKSPACE) {
			if (state.input.length() > 0) {
				state.input = state.input.substring(0, state.input.length() - 1);
			}
		}

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		ConsoleStateComponent state = this.getComponent(ConsoleStateComponent.class);

		if (state.open && this.isPrintableChar(character)) {
			state.input += character;
			return true;
		}

		return false;
	}

	private boolean isPrintableChar(char c) {
		Character.UnicodeBlock block = Character.UnicodeBlock.of(c);

		return (!Character.isISOControl(c)) &&
			c != KeyEvent.CHAR_UNDEFINED &&
			block != null &&
			block != Character.UnicodeBlock.SPECIALS;
	}
}