package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;

public class SplashState implements State {
    /** Splash texture */
    private Texture splash;

    public SplashState() {
        this.splash = new Texture(Gdx.files.internal("splash.png"));
        Assets.load();
    }

    /**
     * Never used
     */
    @Override
    public void show() {

    }

    /**
     * Renders and updates the splash
     *
     * @param delta delta from last update
     */
    @Override
    public void render(float delta) {
        LastTry.batch.draw(this.splash, 0, 0);

        if (Assets.isLoaded()) {
	        Graphics.load();
	        LastTry.instance.setScreen(new MenuState());
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