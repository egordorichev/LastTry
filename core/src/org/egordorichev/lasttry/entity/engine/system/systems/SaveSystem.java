package org.egordorichev.lasttry.entity.engine.system.systems;

import org.egordorichev.lasttry.entity.engine.SystemMessage;
import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.entity.entities.world.World;
import org.egordorichev.lasttry.entity.entities.world.WorldIO;

import java.util.Objects;

/**
 * Handles game saving
 */
public class SaveSystem implements System {
	@Override
	public void handleMessage(SystemMessage message) {
		if (Objects.equals(message.getType(), SystemMessage.Type.SAVE)) {
			WorldIO.write(World.instance);
		}
	}
}