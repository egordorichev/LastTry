package org.egordorichev.lasttry.game.state;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.engine.system.systems.CameraSystem;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.graphics.Graphics;
//import org.egordorichev.lasttry.graphics.LightRenderer;

import java.util.ArrayList;

public class InGameState extends State {
	
	//private final LightRenderer light = new LightRenderer();
	
	public InGameState() {
		
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
		Graphics.batch.setProjectionMatrix(CameraSystem.instance.get("main").getComponent(CameraComponent.class).camera.combined);
		
		for (Entity entity : Engine.getEntities()) {
			entity.render();
		}
		
		//light.render();

		Graphics.batch.setProjectionMatrix(CameraSystem.instance.get("ui").getComponent(CameraComponent.class).camera.combined);
		ArrayList<Entity> entities = Engine.getEntities();

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).renderUi();
		}
	}

	@Override
	public void resize(int width, int height) {
		//light.resize(width, height);
	}
}