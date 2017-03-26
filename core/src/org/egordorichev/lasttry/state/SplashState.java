package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;

public class SplashState implements State {
	/** Shows if assets are ready */
	private boolean doneLoading = false;

	/** Splash texture */
	private Texture splash;

	public SplashState() {
		this.splash = new Texture(Gdx.files.internal("splash.png"));
		Assets.load();
	}

	/** Never used */
	@Override
	public void show() {

	}

	/**
	 * Renders and updates the splash
	 * @param delta delta from last update
	 */
	@Override
	public void render(float delta) {
		LastTry.batch.draw(this.splash, 0, 0);
		this.doneLoading = Assets.isLoaded();

		if (this.doneLoading) {
			LastTry.instance.setScreen(new GamePlayState());
		}
	}

	/** Never used */
	@Override
	public void resize(int width, int height) {

	}

	/** Never used */
	@Override
	public void pause() {

	}

	/** Never used */
	@Override
	public void resume() {

	}

	/** Never used */
	@Override
	public void hide() {

	}

	/** Never used */
	@Override
	public void dispose() {

	}
}