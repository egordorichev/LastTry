package org.egordorichev.lasttry.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.graphics.Graphics;

public class InGameState extends State {
	/**
	 * Used for resetting view
	 */
	private OrthographicCamera uiCamera;

	public InGameState() {
		this.uiCamera = new OrthographicCamera(800, 600);

		this.uiCamera.position.x = Gdx.graphics.getWidth() / 2;
		this.uiCamera.position.y = Gdx.graphics.getHeight() / 2;
		this.uiCamera.update();
	}

	/**
	 * Updates the game
	 *
	 * @param delta Time, since the last frame
	 */
	@Override
	public void update(float delta) {
		Engine.update(delta);
	}

	/**
	 * Renders the game
	 */
	@Override
	public void render() {
		for (Entity entity : Engine.getEntities()) {
			entity.render();
		}

		Graphics.batch.setProjectionMatrix(this.uiCamera.combined);

		for (Entity entity : Engine.getEntities()) {
			entity.renderUi();
		}
	}
}