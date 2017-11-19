package org.egordorichev.lasttry.entity.engine.system.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.physics.AccelerationComponent;
import org.egordorichev.lasttry.entity.component.InputComponent;
import org.egordorichev.lasttry.entity.component.physics.CollisionComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.entity.entities.item.ItemUseComponent;
import org.egordorichev.lasttry.entity.entities.item.inventory.InventoryComponent;
import org.egordorichev.lasttry.entity.entities.item.inventory.ItemComponent;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Handles in-game input
 */
public class InputSystem implements System, InputProcessor {
	/**
	 * List of entities, that react on input
	 */
	private ArrayList<Entity> entities = new ArrayList<>();

	public InputSystem() {
		Gdx.input.setInputProcessor(this);
	}

	/**
	 * Handles input
	 *
	 * @param delta Time, since the last frame
	 */
	@Override
	public void update(float delta) {
		for (Entity entity : this.entities) {
			InputComponent input = entity.getComponent(InputComponent.class);
			AccelerationComponent acceleration = entity.getComponent(AccelerationComponent.class);
			CollisionComponent collision = entity.getComponent(CollisionComponent.class);
			InventoryComponent inventory = entity.getComponent(InventoryComponent.class);

			if (Gdx.input.isKeyPressed(input.moveLeft)) {
				acceleration.x -= 0.3;
			}

			if (Gdx.input.isKeyPressed(input.moveRight)) {
				acceleration.x += 0.3;
			}

			if (inventory != null) {
				if (Gdx.input.isKeyJustPressed(input.openInventory)) {
					inventory.open = !inventory.open;
					// TODO: sfx here
				}

				if (Gdx.input.isButtonPressed(0)) {
					ItemComponent slot = inventory.inventory[inventory.selectedSlot];

					if (!slot.isEmpty()) {
						ItemUseComponent use = slot.item.getComponent(ItemUseComponent.class);

						if (use.autoUse && slot.item.use(entity)) {
							slot.count -= 1;

							if (slot.count == 0) {
								slot.item = null;
							}
						}
					}
				}
			}

			if (collision.onGround && Gdx.input.isKeyPressed(input.jump)) {
				acceleration.y += 10;
			}
		}
	}

	/**
	 * Handles in-coming messages
	 *
	 * @param message Message from the engine
	 */
	@Override
	public void handleMessage(String message) {
		if (Objects.equals(message, "entity_added")) {
			this.entities = Engine.getEntitiesFor(InputComponent.class, AccelerationComponent.class);
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		for (Entity entity : this.entities) {
			InventoryComponent inventory = entity.getComponent(InventoryComponent.class);

			if (inventory != null) {
				ItemComponent slot = inventory.inventory[inventory.selectedSlot];

				if (!slot.isEmpty()) {
					ItemUseComponent use = slot.item.getComponent(ItemUseComponent.class);

					if (!use.autoUse && slot.item.use(entity)) {
						slot.count -= 1;

						if (slot.count == 0) {
							slot.item = null;
						}
					}
				}
			}
		}

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		for (Entity entity : this.entities) {
			InventoryComponent inventory = entity.getComponent(InventoryComponent.class);

			if (inventory != null) {
				inventory.selectedSlot = (short) ((inventory.selectedSlot + amount) % 10);

				if (inventory.selectedSlot < 0) {
					inventory.selectedSlot += 10;
				}
			}
		}

		return false;
	}
}