package org.egordorichev.lasttry.entity.engine.system.systems;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.engine.SystemMessages;
import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.entity.entities.item.ItemUseComponent;
import org.egordorichev.lasttry.entity.entities.item.inventory.InventoryComponent;
import org.egordorichev.lasttry.entity.entities.item.inventory.ItemComponent;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Handles item use time and animations
 */
public class InventorySystem implements System {
	/**
	 * Entities with inventories
	 */
	private ArrayList<Entity> entities = new ArrayList<>();

	/**
	 * Updates inventories
	 *
	 * @param delta Time, since the last frame
	 */
	@Override
	public void update(float delta) {
		for (Entity entity : this.entities) {
			InventoryComponent inventory = entity.getComponent(InventoryComponent.class);

			for (ItemComponent item : inventory.inventory) {
				if (!item.isEmpty()) {
					ItemUseComponent use = item.item.getComponent(ItemUseComponent.class);
					use.currentTime = Math.max(0, use.currentTime - delta);
				}
			}
		}
	}

	/**
	 * Handles messages
	 *
	 * @param message Message from the engine
	 */
	@Override
	public void handleMessage(String message) {
		if (Objects.equals(message, SystemMessages.ENTITIES_UPDATED)) {
			this.entities = Engine.getEntitiesFor(InventoryComponent.class);
		}
	}
}