package org.egordorichev.lasttry.entity.entities.item.tile.multitile.chest;

import org.egordorichev.lasttry.entity.Players;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.engine.system.systems.ChestSystem;
import org.egordorichev.lasttry.entity.engine.system.systems.UiSystem;
import org.egordorichev.lasttry.entity.entities.item.inventory.InventoryComponent;
import org.egordorichev.lasttry.entity.entities.item.tile.interactable.Interactable;
import org.egordorichev.lasttry.entity.entities.item.tile.multitile.MultitileBlock;
import org.egordorichev.lasttry.entity.entities.ui.inventory.UiChestInventory;
import org.egordorichev.lasttry.util.geometry.Rectangle;

/**
 * Chest handler
 */
public class Chest extends MultitileBlock implements Interactable {
	public Chest(String id) {
		super(id);
	}

	@Override
	public void onClick(int x, int y) {
		short xx = (short) (x / 8);
		short yy = (short) (y / 8);

		ChestRegistry chest = ChestSystem.instance.find(xx, yy);

		if (chest == null) {
			return; // Did not find it
		}


		UiChestInventory ui = (UiChestInventory) UiSystem.instance.get("chest");

		if (ui != null) {
			Engine.removeEntity(ui);
		} else {
			ChestComponent inventory = chest.getComponent(ChestComponent.class);
			Engine.addEntity(new UiChestInventory(new Rectangle(5, 106, 10, 10), inventory));
			Players.clientPlayer.getComponent(InventoryComponent.class).open = true;
		}
	}

	/**
	 * Places the chest
	 *
	 * @param x In World X
	 * @param y In World Y
	 */
	@Override
	protected void place(short x, short y) {
		super.place(x, y);

		// Add the entity

		ChestRegistry chest = new ChestRegistry();
		PositionComponent position = chest.getComponent(PositionComponent.class);

		position.x = x;
		position.y = y;

		Engine.addEntity(chest);
	}
}