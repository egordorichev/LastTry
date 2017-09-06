package org.egordorichev.lasttry.player;

import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.Layers;
import org.egordorichev.lasttry.entity.Creature;
import org.egordorichev.lasttry.inventory.Inventory;
import org.egordorichev.lasttry.inventory.InventoryOwner;
import org.egordorichev.lasttry.ui.UiInventory;
import org.egordorichev.lasttry.ui.UiItemSlot;
import org.egordorichev.lasttry.util.Callable;

public class Player extends Creature implements InventoryOwner<UiItemSlot> {
	public static final int INVENTORY_SIZE = 88;
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
	 * Timer before respawn
	 */
	private int respawnTime;

	public Player(String name) {
		super("lt:player");
		this.stats.set(100, 20, 0, 0);
		this.name = name;
		this.setInventory(new UiInventory(INVENTORY_SIZE, this));
		LastTry.ui.add(this.getInventory());
		this.setZIndex(Layers.player);		
	}

	@Override
	protected void setupComponents() {
		super.setupComponents();
		this.input = new PlayerInputComponent(this);
		this.graphics = new PlayerGraphicsComponent(this);
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
		Vector2 spawnPoint = Globals.getWorld().getSpawnPoint();
		this.physics.setGridPosition((int) spawnPoint.x, (int) spawnPoint.y);
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
			// Reset position and health
			this.tpToSpawn();
			this.stats.modifyHP(this.stats.getMaxHP());
			this.active = true;
		}
	}

	@Override
	public void onDeath() {
		super.onDeath();
		respawnTime = Globals.getWorld().getRespawnWait();
		Globals.chat.print(this.name + " is dead");
		// TODO: Also display 'You are dead' on the screen.
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
		return 10;
	}

	/**
	 * @return Player name
	 */
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
}