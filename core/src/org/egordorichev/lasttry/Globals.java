package org.egordorichev.lasttry;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.egordorichev.lasttry.entity.entities.camera.Camera;
import org.egordorichev.lasttry.entity.entities.world.World;
import org.egordorichev.lasttry.entity.system.EntitySystem;
import org.egordorichev.lasttry.util.log.Log;

public class Globals {
	/**
	 * In-game camera
	 */
	public static Camera camera;
	/**
	 * Entity manager
	 */
	public static EntitySystem entitySystem;
	/**
	 * Sprite renderer
	 */
	public static SpriteBatch batch;
	/**
	 * The world
	 */
	public static World world;
	/**
	 * Shows, if debug info should be shown
	 */
	private static boolean debug = false;

	public static void init() {
		entitySystem = new EntitySystem();
		batch = new SpriteBatch();
		camera = new Camera();
		world = new World();

		entitySystem.add(camera);
		entitySystem.add(world);
	}

	/**
	 * Enables debug mode and log
	 */
	public static void enableDebug() {
		debug = true;
		Log.levels[1].enable(); // Enable debug log
	}
}