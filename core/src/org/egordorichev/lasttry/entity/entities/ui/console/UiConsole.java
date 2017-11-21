package org.egordorichev.lasttry.entity.entities.ui.console;

import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.entities.ui.UiElement;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.util.geometry.Rectangle;
import org.egordorichev.lasttry.util.input.Input;
import org.egordorichev.lasttry.util.input.SimpleInputProcessor;

import java.util.Objects;

/**
 * Super useful command line debug thingy
 */
public class UiConsole extends UiElement implements SimpleInputProcessor {
	public UiConsole(Rectangle rect) {
		super(rect, ConsoleCommandsComponent.class, ConsoleStateComponent.class);

		Input.multiplexer.addProcessor(this);
	}

	/**
	 * Renders the console
	 */
	@Override
	public void renderUi() {
		ConsoleStateComponent state = this.getComponent(ConsoleStateComponent.class);

		if (state.open) {
			PositionComponent position = this.getComponent(PositionComponent.class);
			Assets.f14.draw(Graphics.batch, state.input, position.x, position.y + 14);
		}
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

		String[] parts = input.split("\\s+");
		String name = parts[0];
		ConsoleCommandsComponent commands = this.getComponent(ConsoleCommandsComponent.class);
		ConsoleCommand cmd = null;

		for (ConsoleCommand command : commands.commands) {
			if (Objects.equals(command.getName(), name)) {
				cmd = command;
				break;
			}
		}

		if (cmd == null) {
			// TODO: error
			return;
		}

		String[] args = new String[parts.length - 1];

		for (int i = 0; i < args.length; i++) {
			args[i] = parts[i + 1];
		}

		cmd.run(this, args);
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Assets.keys.get("toggle_console")) {
			ConsoleStateComponent state = this.getComponent(ConsoleStateComponent.class);
			state.open = !state.open;
		}

		return false;
	}
}