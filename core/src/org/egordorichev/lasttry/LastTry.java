package org.egordorichev.lasttry;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import org.egordorichev.lasttry.core.Version;
import org.egordorichev.lasttry.core.boot.ArgumentParser;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.Players;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.SaveableComponents;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.engine.SystemMessages;
import org.egordorichev.lasttry.entity.engine.system.systems.CameraSystem;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.entity.entities.item.inventory.InventoryComponent;
import org.egordorichev.lasttry.entity.entities.ui.console.UiConsole;
import org.egordorichev.lasttry.entity.entities.ui.inventory.UiInventory;
import org.egordorichev.lasttry.entity.entities.world.ClockComponent;
import org.egordorichev.lasttry.entity.entities.world.World;
import org.egordorichev.lasttry.game.state.InGameState;
import org.egordorichev.lasttry.game.state.State;
import org.egordorichev.lasttry.graphics.Display;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.util.geometry.Rectangle;
import org.egordorichev.lasttry.util.input.Input;
import org.egordorichev.lasttry.util.log.Log;

import java.util.HashSet;

/**
 * The main class of the game
 * Be careful with changing it ;)
 */
public class LastTry extends Game {
	/**
	 * The default window title
	 */
	private String title;
	/**
	 * The current game state
	 */
	private State state;

	/**
	 * All init happens here
	 */
	@Override
	public void create() {
		SaveableComponents.init();

		try {
			ArgumentParser.parse();
		} catch (RuntimeException exception) {
			Log.error(exception.getMessage());
			Log.error("Failed to parse arguments aborting");

			Gdx.app.exit();
			return;
		}

		Gdx.input.setInputProcessor(Input.multiplexer);
		Gdx.graphics.setWindowedMode(Display.WIDTH * 2, Display.HEIGHT * 2);

		this.title = "LastTry " + Version.STRING;
		this.setState(new InGameState());

		Assets.load();
		Engine.init();

		HashSet<Entity> players = Engine.getWithAllTypes(InventoryComponent.class);

		if (players.size() > 0) {
			Entity player = Players.clientPlayer;

			Engine.addEntity(new UiInventory(new Rectangle(5, 5, 150, 50), player.getComponent(InventoryComponent.class))); // Share the inventory
			Engine.addEntity(new UiConsole(new Rectangle(5, 30, 150, 16)));

			/* TMP: For @PibePlayer */
			InventoryComponent inv = player.getComponent(InventoryComponent.class);
			inv.inventory[1].item = Assets.items.get("lt:fc_main");
			inv.inventory[1].count = 10;
			inv.inventory[2].item = Assets.items.get("lt:stone");
			inv.inventory[2].count = 999;
		} else {
			Log.warning("Failed to create UI inventory for the player");
		}
	}

	/**
	 * Sets LT state
	 *
	 * @param state New state
	 */
	public void setState(State state) {
		this.state = state;
		this.setScreen(state);

		Log.info("Set LT state to " + state.getClass());

		// Here you can save/load stuff
	}

	/**
	 * Handles window resizing
	 *
	 * @param width New window width
	 * @param height New window height
	 */
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		Engine.sendMessage(SystemMessages.WINDOW_RESIZED);
	}

	/**
	 * Renders the game
	 */
	@Override
	public void render() {
		float delta = Gdx.graphics.getDeltaTime();
		this.state.update(delta);
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Graphics.batch.begin();
		this.state.render();
		Graphics.batch.end();

		String title = this.title + " " + Gdx.graphics.getFramesPerSecond() + " FPS";

		if (World.instance != null) {
			ClockComponent clock = World.instance.getComponent(ClockComponent.class);

			title += ", " + String.format("%02d", clock.hour) + ":" + String.format("%02d", (int) (clock.minute)) + ":" + String.format("%02d", (int) clock.second);
		}

		Gdx.graphics.setTitle(title);
	}

	/**
	 * Saves the game
	 */
	@Override
	public void dispose() {
		super.dispose();
		Engine.sendMessage("save");
	}
}