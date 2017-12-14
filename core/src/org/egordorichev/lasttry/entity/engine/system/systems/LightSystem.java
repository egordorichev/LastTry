package org.egordorichev.lasttry.entity.engine.system.systems;

import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.entity.entities.world.World;
import org.egordorichev.lasttry.entity.entities.world.chunk.Chunk;
import org.egordorichev.lasttry.util.log.Log;

public class LightSystem implements System {
	/**
	 * Handles messages
	 *
	 * @param message Message from the engine
	 */
	@Override
	public void handleMessage(String message) {
		if (message.startsWith("set_")) {
			try {
				String[] parts = message.split("_"); // Will be set block/wall x y
				short x = Short.valueOf(parts[2]);
				short y = Short.valueOf(parts[3]);

				Chunk chunk = World.instance.getChunkFor(x, y);
				// chunk.calculateLighting();
				// TODO: place back
				// REALLY SLOW!
			} catch (Exception exception) {
				exception.printStackTrace();
				Log.error("Failed to recalculate light");
			}
		}
	}
}