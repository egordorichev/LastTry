package org.egordorichev.lasttry.game.state;

import org.egordorichev.lasttry.core.context.Context;
import org.egordorichev.lasttry.graphics.Camera;

public class GamePlayState implements GameState {
	@Override
	public void load(Context rootContext) {
		Camera camera = new Camera(0, 0);
		rootContext.bindInstance(Camera.class, camera);
	}

	@Override
	public void update(Context context, float deltaT) {
		context.get(Camera.class).update(deltaT);
	}

	@Override
	public void render(Context context) {
		context.get(Camera.class).apply();
	}
}