package org.egordorichev.lasttry.entity.entities.ui.inventory;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.component.TextureComponent;
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
	/**
	 * Used for calculating string width
	 */
	private static GlyphLayout layout;

	public UiInventory(Rectangle rect, InventoryComponent inventory) {
		super(rect);

		if (slotTexture == null) {
			slotTexture = Assets.getTexture("ui/inventory_slot");
		}

		if (layout == null) {
			layout = new GlyphLayout();
		}

		this.components.put(InventoryComponent.class, inventory);
	}

	@Override
	public void renderUi() {
		PositionComponent position = this.getComponent(PositionComponent.class);
		InventoryComponent inventory = this.getComponent(InventoryComponent.class);

		for (int i = 0; i < (inventory.open ? inventory.inventory.length : Math.min(inventory.inventory.length, 10)); i++) {
			this.renderSlot(inventory, position.x + i % 10 * 50, (float) (position.y + Math.floor(i / 10) * 50), i, inventory.inventory[i]);
		}
	}

	private void renderSlot(InventoryComponent inventory, float x, float y, int index, ItemComponent item) {
		if (inventory.selectedSlot == index) {
			Graphics.batch.setColor(1.0f, 1.0f, 0.7f, 1.0f);
		}

		Graphics.batch.draw(slotTexture, x, y);

		if (inventory.selectedSlot == index) {
			Graphics.batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		}

		if (!item.isEmpty()) {
			TextureComponent texture = item.item.getComponent(TextureComponent.class);
			TextureRegion region = texture.texture;

			// Draw the texture at the center of the slot
			Graphics.batch.draw(region, x + (48 - region.getRegionWidth()) / 2, y + (48 - region.getRegionHeight()) / 2);

			if (item.count > 1) {
				String str = String.valueOf(item.count);
				layout.setText(Assets.f14, str);

				Assets.f14.draw(Graphics.batch, str, x + 44 - layout.width, y + 16);
			}
		}

		if (index < 10) {
			Assets.f14.draw(Graphics.batch, String.valueOf(index < 9 ? index + 1 : 0), x + 6, y + 42);
		}
	}
}