package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;

public class SplashState implements State {
	private Texture splash;

	public SplashState() {
		this.splash = new Texture(Gdx.files.internal("Splash.png"));
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		if (Assets.assetManager.update()) {
			Graphics.load();
			LastTry.instance.setScreen(new LoadState());
		}

		Graphics.batch.draw(this.splash, (Gdx.graphics.getWidth() - this.splash.getWidth()) / 2,
			(Gdx.graphics.getHeight() - this.splash.getHeight()) / 2);
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