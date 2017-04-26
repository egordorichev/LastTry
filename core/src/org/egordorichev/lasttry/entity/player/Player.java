package org.egordorichev.lasttry.entity.player;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.Creature;
import org.egordorichev.lasttry.entity.components.CreaturePhysicsComponent;
import org.egordorichev.lasttry.ui.UiInventory;

public class Player extends Creature {
	public static final int INVENTORY_SIZE = 88;

	public UiInventory inventory;
	private PlayerInputComponent input;
	private String name;

	public Player(String name) {
		super(new CreaturePhysicsComponent(), new PlayerGraphicsComponent());

		this.input = new PlayerInputComponent(this);
		this.stats.set(100, 20, 0, 0);
		this.name = name;
		this.inventory = new UiInventory(INVENTORY_SIZE);

		LastTry.ui.add(this.inventory);

		this.physics.setSolid(false);
	}

	@Override
	public void update(int dt) {
		super.update(dt);
		this.input.update(dt);
	}

	public String getName() {
		return this.name;
	}
}