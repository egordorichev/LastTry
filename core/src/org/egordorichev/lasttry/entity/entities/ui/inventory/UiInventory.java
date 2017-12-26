package org.egordorichev.lasttry.entity.entities.ui.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import org.egordorichev.lasttry.entity.Players;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.component.TextureComponent;
import org.egordorichev.lasttry.entity.engine.system.systems.CameraSystem;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.entity.entities.item.ItemUseComponent;
import org.egordorichev.lasttry.entity.entities.item.inventory.InventoryComponent;
import org.egordorichev.lasttry.entity.entities.item.inventory.ItemComponent;
import org.egordorichev.lasttry.entity.entities.ui.UiElement;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.util.collision.Collider;
import org.egordorichev.lasttry.util.geometry.Rectangle;
import org.egordorichev.lasttry.util.graphics.OutlinePrinter;
import org.egordorichev.lasttry.util.input.Input;
import org.egordorichev.lasttry.util.input.SimpleInputProcessor;

/**
 * Handles inventory
 */
public class UiInventory extends UiElement implements SimpleInputProcessor {
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
		Input.multiplexer.addProcessor(this);
	}

	@Override
	public void renderUi() {
		if (Gdx.input.isButtonPressed(0)) {
			InventoryComponent inventory = this.getComponent(InventoryComponent.class);
			ItemComponent slot = inventory.inventory[inventory.selectedSlot];

			if (!slot.isEmpty()) {
				ItemUseComponent use = slot.item.getComponent(ItemUseComponent.class);

				if (use.autoUse && slot.item.use(inventory.getEntity())) {
					slot.count -= 1;

					if (slot.count == 0) {
						slot.item = null;
					}
				}
			}
		}

		PositionComponent position = this.getComponent(PositionComponent.class);
		InventoryComponent inventory = this.getComponent(InventoryComponent.class);

		for (int i = 0; i < (inventory.open ? inventory.inventory.length : Math.min(inventory.inventory.length, 10)); i++) {
			UiInventory.renderSlot(inventory, position.x + i % 10 * 25, (float) (position.y + Math.floor(i / 10) * 25), i, inventory.inventory[i]);
		}

		ItemComponent item = Players.clientPlayer.getComponent(ItemComponent.class);

		if (!item.isEmpty()) {
			Vector3 mouse = CameraSystem.instance.get("ui").getComponent(CameraComponent.class).camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			Graphics.batch.draw(item.item.getComponent(TextureComponent.class).texture, mouse.x + 8, mouse.y - 8);


			if (item.count > 1) {
				String str = String.valueOf(item.count);
				layout.setText(Assets.f4, str);

				OutlinePrinter.print(Assets.f4, str, (int) (mouse.x + 22 - layout.width), (int) mouse.y - 8);
			}
		}
	}

	private static void renderSlot(InventoryComponent inventory, float x, float y, int index, ItemComponent item) {
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
			Graphics.batch.draw(region, x + (24 - region.getRegionWidth()) / 2, y + (24 - region.getRegionHeight()) / 2);

			if (item.count > 1) {
				String str = String.valueOf(item.count);
				layout.setText(Assets.f4, str);

				OutlinePrinter.print(Assets.f4, str, (int) (x + 22 - layout.width), (int) (y + 8));
			}
		}

		if (index < 10) {
			OutlinePrinter.print(Assets.f4, String.valueOf(index < 9 ? index + 1 : 0), (int) (x + 3), (int) (y + 21));
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		if (!Input.blocked && keycode == Assets.keys.get("toggle_inventory")) {
			InventoryComponent inventory = this.getComponent(InventoryComponent.class);
			inventory.open = !inventory.open;
		}

		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == 0) { // TODO: support multiple buttons
			PositionComponent position = this.getComponent(PositionComponent.class);
			InventoryComponent inventory = this.getComponent(InventoryComponent.class);

			if (inventory.open) {
				Vector3 mouse = CameraSystem.instance.get("ui").getComponent(CameraComponent.class).camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

				for (int i = 0; i < inventory.inventory.length; i++) {
					float x = position.x + i % 10 * 25;
					float y = (float) (position.y + Math.floor(i / 10) * 25);

					if (Collider.testAABB(x, y, 24, 24, mouse.x, mouse.y, 1, 1)) {
						ItemComponent item = inventory.inventory[i];
						ItemComponent self = Players.clientPlayer.getComponent(ItemComponent.class);

						ItemComponent tmp = new ItemComponent();

						tmp.item = self.item;
						tmp.count = self.count;

						self.item = item.item;
						self.count = item.count;

						item.item = tmp.item;
						item.count = tmp.count;

						return true;
					}
				}
			}

			ItemComponent slot = inventory.inventory[inventory.selectedSlot];

			if (!slot.isEmpty()) {
				ItemUseComponent use = slot.item.getComponent(ItemUseComponent.class);

				if (!use.autoUse && slot.item.use(inventory.getEntity())) {
					slot.count -= 1;

					if (slot.count == 0) {
						slot.item = null;
					}

					return true;
				}
			}
		}

		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		InventoryComponent inventory = this.getComponent(InventoryComponent.class);
		inventory.selectedSlot = (short) ((inventory.selectedSlot + amount) % 10);

		if (inventory.selectedSlot < 0) {
			inventory.selectedSlot += 10;
		}

		return false;
	}
}