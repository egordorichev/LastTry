package org.egordorichev.lasttry.game.state;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.engine.Engine;

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
		for (Entity entity : Engine.getEntities()) {
			entity.render();
		}
	}
}