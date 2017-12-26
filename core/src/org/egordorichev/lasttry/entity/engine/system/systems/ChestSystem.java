package org.egordorichev.lasttry.entity.engine.system.systems;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.engine.SystemMessages;
import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.entity.entities.item.tile.multitile.chest.ChestComponent;
import org.egordorichev.lasttry.entity.entities.item.tile.multitile.chest.ChestRegistry;
import org.egordorichev.lasttry.util.collision.Collider;

import java.util.HashSet;
import java.util.Objects;

/**
 * Handles chests
 */
public class ChestSystem implements System {
	/**
	 * The list of chests
	 */
	private HashSet<Entity> entities = new HashSet<>();
	/**
	 * The instance of the system
	 */
	public static ChestSystem instance;

	public ChestSystem() {
		instance = this;
	}

	/**
	 * Handles entity addition
	 *
	 * @param message Message from the engine
	 */
	@Override
	public void handleMessage(String message) {
		if (Objects.equals(message, SystemMessages.ENTITIES_UPDATED)) {
			this.entities = Engine.getWithAnyTypes(ChestComponent.class);
		}
	}

	/**
	 * Searches for chest in given location
	 *
	 * @param x Chest X
	 * @param y Chest Y
	 * @return The Chest
	 */
	public ChestRegistry find(short x, short y) {
		for (Entity entity : this.entities) {
			PositionComponent position = entity.getComponent(PositionComponent.class);

			if (Collider.testAABB(position.x, position.y, 4, 4, x, y, 1, 1)) {
				return (ChestRegistry) entity;
			}
		}

		return null;
	}
}