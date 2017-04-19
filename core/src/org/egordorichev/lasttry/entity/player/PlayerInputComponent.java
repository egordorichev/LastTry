package org.egordorichev.lasttry.entity.player;

import org.egordorichev.lasttry.entity.components.CreatureComponent;
import org.egordorichev.lasttry.entity.components.PhysicsComponent;
import org.egordorichev.lasttry.input.InputManager;
import org.egordorichev.lasttry.input.Keys;

public class PlayerInputComponent extends CreatureComponent {
	public PlayerInputComponent(Player player) {
		super(player);
	}

	public void update(int dt) {
		if (InputManager.isKeyDown(Keys.JUMP)) {
			this.creature.physics.jump();
		}

		if (InputManager.isKeyDown(Keys.MOVE_LEFT)) {
			this.creature.physics.move(PhysicsComponent.Direction.LEFT);
		}

		if (InputManager.isKeyDown(Keys.MOVE_RIGHT)) {
			this.creature.physics.move(PhysicsComponent.Direction.RIGHT);
		}

		if (InputManager.isKeyJustDown(Keys.OPEN_INVENTORY)) {
			((Player) this.creature).inventory.toggle();
		}
	}
}