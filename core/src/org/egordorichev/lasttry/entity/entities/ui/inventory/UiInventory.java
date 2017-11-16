package org.egordorichev.lasttry.entity.entities.ui.inventory;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.entities.item.inventory.InventoryComponent;
import org.egordorichev.lasttry.entity.entities.item.inventory.ItemComponent;
import org.egordorichev.lasttry.entity.entities.ui.UiElement;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.util.geometry.Rectangle;

/**
 * Handles inventory
 */
public class UiInventory extends UiElement {
	/**
	 * The item slot texture
	 */
	private static TextureRegion slotTexture;

	public UiInventory(Rectangle rect, InventoryComponent inventory) {
		super(rect);

		if (slotTexture == null) {
			slotTexture = Assets.getTexture("ui/inventory_slot");
		}

		this.components.put(InventoryComponent.class, inventory);
	}

	@Override
	public void renderUi() {
		PositionComponent position = this.getComponent(PositionComponent.class);
		InventoryComponent inventory = this.getComponent(InventoryComponent.class);

		for (int i = 0; i < (inventory.open ? inventory.inventory.length : Math.min(inventory.inventory.length, 10)); i++) {
			this.renderSlot(position.x + i % 10 * 50, (float) (position.y + Math.floor(i / 10) * 50), i, inventory.inventory[i]);
		}
	}

	private void renderSlot(float x, float y, float index, ItemComponent item) {
		Graphics.batch.draw(slotTexture, x, y);

		// TODO: render item a
	}
}