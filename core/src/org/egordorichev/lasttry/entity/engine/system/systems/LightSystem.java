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
				short x = message.get("x");
				short y =  message.get("y");

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