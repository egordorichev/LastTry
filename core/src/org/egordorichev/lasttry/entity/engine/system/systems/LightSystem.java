package org.egordorichev.lasttry.entity.engine.system.systems;

import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.entity.entities.world.World;
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
				int x = Integer.valueOf(parts[2]);
				int y = Integer.valueOf(parts[3]);

				World.instance.getChunkFor(x, y).calculateLighting(1f);
				for(int chunkX = -1; chunkX <= 1; chunkX++){
					for(int chunkY = -1; chunkY <= 1; chunkY++){
						if(chunkX != x || chunkY != y)World.instance.getChunkFor(chunkX, chunkY).calculateLighting(1f);
					}
				}
			} catch (Exception exception) {
				exception.printStackTrace();
				Log.error("Failed to recalculate light");
			}
		}
	}
}