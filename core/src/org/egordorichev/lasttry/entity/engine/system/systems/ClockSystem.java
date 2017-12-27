package org.egordorichev.lasttry.entity.engine.system.systems;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.engine.SystemMessage;
import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.entity.entities.world.ClockComponent;

import java.util.HashSet;
import java.util.Objects;

public class ClockSystem implements System {
	/**
	 * List of clocks
	 */
	private HashSet<Entity> entities  = new HashSet<>();

	/**
	 * Handles clock logic
	 *
	 * @param delta Time, since the last frame
	 */
	@Override
	public void update(float delta) {
		for (Entity entity : this.entities) {
			ClockComponent clock = entity.getComponent(ClockComponent.class);
			float change = clock.speed * delta;

			if (delta > 0) {
				clock.second += change;

				if (clock.second >= 60) {
					clock.second -= 60;
					clock.minute += 1;

					if (clock.minute >= 60) {
						clock.minute = 0;
						clock.hour += 1;

						if (clock.hour >= 24) {
							clock.hour = 0;
						}
					}
				}
			}
		}
	}

	/**
	 * Handles adding entities
	 *
	 * @param message Message from the engine
	 */
	@Override
	public void handleMessage(SystemMessage message) {
		if (Objects.equals(message.getType(), SystemMessage.Type.ENTITIES_UPDATED)) {
			this.entities = Engine.getWithAllTypes(ClockComponent.class);
		}
	}
}