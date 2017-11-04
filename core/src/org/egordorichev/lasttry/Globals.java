package org.egordorichev.lasttry;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.entities.camera.Camera;
import org.egordorichev.lasttry.entity.entities.creature.player.Player;
import org.egordorichev.lasttry.entity.entities.world.World;
import org.egordorichev.lasttry.util.log.Log;

public class Globals {
	/**
	 * In-game camera
	 */
	public static Camera camera;
	/**
	 * Sprite renderer
	 */
	public static SpriteBatch batch;
	/**
	 * The world
	 */
	public static World world;
	/**
	 * The player
	 */
	public static Player player;
	/**
	 * Shows, if debug info should be shown
	 */
	private static boolean debug = false;

	public static void init() {
		batch = new SpriteBatch();
		camera = new Camera();
		world = new World();
		player = new Player();

		Engine.addEntity(camera);
		Engine.addEntity(world);
		Engine.addEntity(player);
	}

	/**
	 * Enables debug mode and log
	 */
	public static void enableDebug() {
		debug = true;
		Log.levels[1].enable(); // Enable debug log
	}
}