package org.egordorichev.lasttry.game.state;

import com.badlogic.gdx.Screen;

/**
 * Simple state handle
 */
public class State implements Screen {
	/**
	 * State name
	 */
	private String name;

	public State(String name) {
		this.name = name;
	}

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

	@Override
	public void show() {

	}

	/**
	 * Not used!
	 */
	@Override
	public void render(float delta) {

	}

	public void update(float delta) {

	}

	public void render() {

	}

	@Override
	public void resize(int width, int height) {

	}

	/**
	 * @return State name
	 */
	public String getName() {
		return this.name;
	}
}