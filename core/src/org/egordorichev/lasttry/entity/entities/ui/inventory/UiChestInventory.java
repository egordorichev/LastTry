package org.egordorichev.lasttry.entity.entities.ui.inventory;

import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.entities.item.inventory.InventoryComponent;
import org.egordorichev.lasttry.util.geometry.Rectangle;
import org.egordorichev.lasttry.util.input.Input;

public class UiChestInventory extends UiInventory {
	public UiChestInventory(Rectangle rect, InventoryComponent inventory) {
		super(rect, inventory);

		this.getComponent(InventoryComponent.class).open = true;
		((IdComponent) this.addComponent(IdComponent.class)).id = "chest";
	}

	@Override
	public boolean keyDown(int keycode) {
		if (!Input.blocked && keycode == Assets.keys.get("toggle_inventory")) {
			Engine.removeEntity(this);
		}

		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}