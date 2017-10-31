package org.egordorichev.lasttry;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.egordorichev.lasttry.entity.camera.Camera;
import org.egordorichev.lasttry.entity.system.EntitySystem;

public class Globals {
	public static Camera camera;
	public static EntitySystem entitySystem;
	public static SpriteBatch batch;

	public static void init() {
		entitySystem = new EntitySystem();
		batch = new SpriteBatch();
		camera = new Camera();

		entitySystem.add(camera);
	}
}