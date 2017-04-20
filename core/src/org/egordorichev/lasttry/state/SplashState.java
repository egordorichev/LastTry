package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;

public class SplashState implements State {
    private Texture splash;

    public SplashState() {
        this.splash = new Texture(Gdx.files.internal("splash.png"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        LastTry.batch.draw(this.splash, 0, 0);

	    Assets.assetManager.finishLoading(); // Comment this line to draw splash

	    if (Assets.load()) {
	        Graphics.load();
	        LastTry.instance.setScreen(new LoadState());
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