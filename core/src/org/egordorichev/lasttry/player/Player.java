package org.egordorichev.lasttry.player;

import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.Layers;
import org.egordorichev.lasttry.entity.Creature;
import org.egordorichev.lasttry.entity.components.CreaturePhysicsComponent;
import org.egordorichev.lasttry.inventory.Inventory;
import org.egordorichev.lasttry.inventory.InventoryOwner;
import org.egordorichev.lasttry.ui.UiInventory;
import org.egordorichev.lasttry.ui.UiItemSlot;
import org.egordorichev.lasttry.util.Callable;

public class Player extends Creature implements InventoryOwner<UiItemSlot> {
	public static final int INVENTORY_SIZE = 88;
	public static final int RESPAWN_DELAY = 360;
	/**
	 * Timer before respawn
	 */
	private int respawnTime = RESPAWN_DELAY;
	/**
	 * Inventory
	 */
	private UiInventory inventory;
	/**
	 * Input handler
	 */
	private PlayerInputComponent input;
	/**
	 * Player name
	 */
	private String name;
	/**
	 * Player spawn point
	 */
	private Vector2 spawnPoint = new Vector2();

	public Player(String name) {
		super("lt:player", new CreaturePhysicsComponent(), new PlayerGraphicsComponent());

		this.input = new PlayerInputComponent(this);
		this.stats.set(100, 20, 0, 0);
		this.name = name;
		this.setInventory(new UiInventory(INVENTORY_SIZE, this));

		LastTry.ui.add(this.getInventory());

		this.setZIndex(Layers.player);

		this.physics.setOnGroundHit(new Callable() {
			@Override
			public void call() {
				int damage = (int) (Math.abs(physics.getVelocity().y) - 10);

				if (damage > 0) {
					hit(damage);
				}
			}
		});
	}

	/**
	 * Teleports player to spawn
	 */
	public void tpToSpawn() {
		this.physics.setGridPosition((int) this.spawnPoint.x, (int) this.spawnPoint.y);
	}

	@Override
	public void update(int dt) {
		super.update(dt);

		if (!this.isActive()) {
			updateRespawn();
			return;
		}

		this.input.update(dt);

		if (this.getInventory().getActiveItem() != null && this.getInventory().getActiveItem().getItem() != null) {
			this.getInventory().getActiveItem().getItem().update(this, dt);
		}

		if (this.getInventory().getSelectedItem() != null && this.getInventory().getSelectedItem().getItem() != null) {
			this.getInventory().getSelectedItem().getItem().update(this, dt);
		}
	}

	/**
	 * Update respawn logic.
	 */
	private void updateRespawn() {
		if (respawnTime > 0) {
			respawnTime--;
		} else if (respawnTime == 0) {
			// Reset respawn time for next death
			respawnTime = RESPAWN_DELAY;
			// Reset position and health
			this.tpToSpawn();
			this.stats.modifyHP(this.stats.getMaxHP());
			this.active = true;
		}
	}

	@Override
	public void onDeath() {
		super.onDeath();

		Globals.chat.print(this.name + " is dead");
		// TODO: dead screen
	}

	@Override
	public void render() {
		if (this.isActive()) {
			super.render();
		}
	}

	@Override
	public void die() {
		// Overridden so the player is not marked for removal.
		this.active = false;
		this.onDeath();
	}

	public int getItemUseRadius() {
		// TODO: modify
		return 10;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public UiInventory getInventory() {
		return inventory;
	}

	@Override
	public void setInventory(Inventory<UiItemSlot> inventory) {
		this.inventory = (UiInventory) inventory;
	}

	/**
	 * Sets player spawn point
	 *
	 * @param spawnPoint New spawn point
	 */
	public void setSpawnPoint(Vector2 spawnPoint) {
		this.spawnPoint = spawnPoint;
	}

	/**
	 * @return Player spawn point
	 */
	public Vector2 getSpawnPoint() {
		return this.spawnPoint;
	}
}