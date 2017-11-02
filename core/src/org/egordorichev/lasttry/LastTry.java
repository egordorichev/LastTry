package org.egordorichev.lasttry;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import org.egordorichev.lasttry.core.Version;
import org.egordorichev.lasttry.core.boot.ArgumentParser;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.util.log.Log;

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
	 * All init happens here
	 */
	@Override
	public void create() {
		try {
			ArgumentParser.parse();
		} catch (RuntimeException exception) {
			Log.error(exception.getMessage());
			Log.error("Failed to parse arguments aborting");

			Gdx.app.exit();
			return;
		}

		this.title = "LastTry " + Version.STRING;

		Assets.load();
		Engine.init();

		Globals.init();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		Engine.sendMessage("resize");
	}

	@Override
	public void render() {
		float delta = Gdx.graphics.getDeltaTime();

		Engine.update(delta);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Globals.camera.set();
		Globals.batch.begin();
		Engine.sendMessage("render");
		Globals.batch.end();

		Gdx.graphics.setTitle(this.title + " " + Gdx.graphics.getFramesPerSecond() + " FPS");
	}
}