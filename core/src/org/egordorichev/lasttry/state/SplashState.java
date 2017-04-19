package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.player.PlayerIO;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.world.WorldIO;

public class SplashState implements State {
    /** Splash texture */
    private Texture splash;

    public SplashState() {
        this.splash = new Texture(Gdx.files.internal("splash.png"));
    }

    /**
     * Never used
     */
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

	    Assets.assetManager.finishLoading(); // Comment this line to draw splash

	    if (Assets.load()) {
	        Graphics.load();
	        LastTry.instance.setScreen(new LoadState());
	    }
    }

    /**
     * Never used
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * Never used
     */
    @Override
    public void pause() {

    }

    /**
     * Never used
     */
    @Override
    public void resume() {

    }

    /**
     * Never used
     */
    @Override
    public void hide() {

    }

    /**
     * Never used
     */
    @Override
    public void dispose() {

    }
}