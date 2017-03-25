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

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		LastTry.batch.draw(this.splash, 0, 0);
		this.doneLoading = Assets.isLoaded();

		if (this.doneLoading) {
			LastTry.instance.setScreen(new MenuState());
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}
}