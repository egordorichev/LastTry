package org.egordorichev.lasttry.entity.player;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.Layers;
import org.egordorichev.lasttry.entity.Creature;
import org.egordorichev.lasttry.entity.components.CreaturePhysicsComponent;
import org.egordorichev.lasttry.ui.InventoryOwner;
import org.egordorichev.lasttry.ui.UiInventory;

public class Player extends Creature implements InventoryOwner {
	public static final int INVENTORY_SIZE = 88;

	private UiInventory inventory;
	private PlayerInputComponent input;
	private String name;

	public Player(String name) {
		super(new CreaturePhysicsComponent(), new PlayerGraphicsComponent());

		this.input = new PlayerInputComponent(this);
		this.stats.set(100, 20, 0, 0);
		this.name = name;
		this.setInventory(new UiInventory(INVENTORY_SIZE, this));

		LastTry.ui.add(this.getInventory());

		this.setZIndex(Layers.player);
	}

	@Override
	public void update(int dt) {
		super.update(dt);
		this.input.update(dt);

		if (this.getInventory().currentItem != null && this.getInventory().currentItem.getItem() != null) {
			this.getInventory().currentItem.getItem().update(this, dt);
		}
	}

	public int getItemUseRadius() {
		return 10; // TODO: modify
	}

	public String getName() {
		return this.name;
	}

	@Override
	public UiInventory getInventory() {
		return inventory;
	}

	@Override
	public void setInventory(UiInventory inventory) {
		this.inventory = inventory;
	}
}