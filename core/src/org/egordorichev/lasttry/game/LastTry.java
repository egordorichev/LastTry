package org.egordorichev.lasttry.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import org.egordorichev.lasttry.Engine;
import org.egordorichev.lasttry.core.context.Context;
import org.egordorichev.lasttry.core.context.ContextImpl;
import org.egordorichev.lasttry.game.state.GameState;

/**
 * Main game class
 */
public class LastTry implements ApplicationListener, Engine {
	private GameState state;
	private Context rootContext = new ContextImpl();


	public LastTry(GameState state) {
		this.state = state;
	}

	/**
	 * Creates first-priority instances
	 */
	@Override
	public void create() {

	}

	/**
	 * Handles window resize
	 *
	 * @param width  new window width
	 * @param height new window height
	 */
	@Override
	public void resize(int width, int height) {

	}

	/**
	 * Renders and updates the game
	 */
	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	/**
	 * Handles game exit
	 */
	@Override
	public void dispose() {

	}

	@Override
	public void setGameState(GameState state) {
		if (state != null)
			this.state.hide();
		this.state = state;
		if (this.state != null) {
			this.state.load(rootContext);
			this.state.show();
		}
	}

	@Override
	public void setContext(Context context) {
		this.rootContext = context;
	}

}
