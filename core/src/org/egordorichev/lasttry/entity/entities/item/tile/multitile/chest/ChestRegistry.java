package org.egordorichev.lasttry.entity.entities.item.tile.multitile.chest;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.entities.creature.SaveComponent;
import org.egordorichev.lasttry.util.log.Log;

/**
 * Stores info about chest in the world
 */
public class ChestRegistry extends Entity {
	public ChestRegistry() {
		super(ChestComponent.class, PositionComponent.class, SaveComponent.class, IdComponent.class);

		IdComponent id = this.getComponent(IdComponent.class);

		id.id = "lt:chest_registry";
	}
}