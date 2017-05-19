package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;

public class SplashState implements State {
	private Texture splash;
	private int state = 0;
	private float alpha = 0;

	public SplashState() {
		this.splash = new Texture(Gdx.files.internal("Splash.png"));

		Assets.load();
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Graphics.batch.setColor(1, 1, 1, this.alpha);
		Graphics.batch.draw(this.splash, (Gdx.graphics.getWidth() - this.splash.getWidth()) / 2,
				(Gdx.graphics.getHeight() - this.splash.getHeight()) / 2);
		Graphics.batch.setColor(1, 1, 1, 1);

		if (this.state != 2 && Assets.isLoaded()) {
			this.state = 2;
		}

		if (this.state == 0) {
			this.alpha += 0.01f;

			if (this.alpha > 1f) {
				this.alpha = 1f;
				this.state = 1;
			}
		} else if (this.state == 2) {
			this.alpha -= 0.01f;

			if (this.alpha < 0f) {
				this.alpha = 0f;
				this.state = 3;

				Graphics.load();
				LastTry.instance.setScreen(new LoadState());
			}
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