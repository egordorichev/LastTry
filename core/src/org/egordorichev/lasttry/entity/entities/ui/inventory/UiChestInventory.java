package org.egordorichev.lasttry.entity.entities.ui.inventory;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.component.TextureComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.entities.item.inventory.InventoryComponent;
import org.egordorichev.lasttry.entity.entities.item.inventory.ItemComponent;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.util.geometry.Rectangle;
import org.egordorichev.lasttry.util.graphics.OutlinePrinter;
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

	@Override
	protected void renderSlot(InventoryComponent inventory, float x, float y, int index, ItemComponent item) {
		Graphics.batch.draw(UiInventory.slotTexture, x, y);

		if (!item.isEmpty()) {
			TextureComponent texture = item.item.getComponent(TextureComponent.class);
			TextureRegion region = texture.texture;

			// Draw the texture at the center of the slot
			Graphics.batch.draw(region, x + (24 - region.getRegionWidth()) / 2, y + (24 - region.getRegionHeight()) / 2);

			if (item.count > 1) {
				String str = String.valueOf(item.count);
				UiInventory.layout.setText(Assets.f4, str);

				OutlinePrinter.print(Assets.f4, str, (int) (x + 22 - UiInventory.layout.width), (int) (y + 8));
			}
		}
	}
}