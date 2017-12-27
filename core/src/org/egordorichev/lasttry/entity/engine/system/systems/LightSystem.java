package org.egordorichev.lasttry.entity.engine.system.systems;

import org.egordorichev.lasttry.entity.engine.SystemMessage;
import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.entity.entities.world.World;
import org.egordorichev.lasttry.entity.entities.world.chunk.Chunk;
import org.egordorichev.lasttry.util.log.Log;

import com.google.common.base.Objects;

public class LightSystem implements System {
	/**
	 * Handles messages
	 *
	 * @param message Message from the engine
	 */
	@Override
	public void handleMessage(SystemMessage message) {
		if (Objects.equal(message.getOr("action", null), "set")) {
			try {
				int x = (int)message.get("x") / Chunk.SIZE;
				int y = (int)message.get("y") / Chunk.SIZE;
				
				World.instance.getChunk(x, y).calculateLighting(1f);
				for(int chunkX = -1; chunkX <= 1; chunkX++){
					for(int chunkY = -1; chunkY <= 1; chunkY++){
						if(chunkX != 0 || chunkY != 0){
							Chunk chunk = World.instance.getChunk(x + chunkX, y + chunkY);
							if(chunk != null)chunk.calculateLighting(1f);
						}
					}
				}
			} catch (Exception exception) {
				exception.printStackTrace();
				Log.error("Failed to recalculate light");
			}
		}
	}
}