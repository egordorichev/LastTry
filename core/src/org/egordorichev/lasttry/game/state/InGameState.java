package org.egordorichev.lasttry.game.state;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.engine.system.systems.CameraSystem;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.graphics.Graphics;

import java.util.ArrayList;

public class InGameState extends State {
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
		ArrayList<Entity> entities = Engine.getEntities();
		Graphics.batch.setProjectionMatrix(CameraSystem.instance.get("main").getComponent(CameraComponent.class).camera.combined);

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render();
		}

		Graphics.batch.setProjectionMatrix(CameraSystem.instance.get("ui").getComponent(CameraComponent.class).camera.combined);

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).renderUi();
		}
	}
}