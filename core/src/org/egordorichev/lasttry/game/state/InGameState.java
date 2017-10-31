package org.egordorichev.lasttry.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import org.egordorichev.lasttry.Globals;

public class InGameState extends State {
	public InGameState() {
		super("in game");
	}

	public void update(float delta) {
		Globals.entitySystem.update(delta);
	}

	public void render() {
		Globals.camera.set();

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Globals.entitySystem.render();
	}
}