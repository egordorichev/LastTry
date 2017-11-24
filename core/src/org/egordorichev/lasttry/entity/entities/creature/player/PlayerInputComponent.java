package org.egordorichev.lasttry.entity.entities.creature.player;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.InputComponent;
import org.egordorichev.lasttry.entity.component.physics.AccelerationComponent;
import org.egordorichev.lasttry.entity.component.physics.CollisionComponent;

/**
 * Handles input for player
 */
public class PlayerInputComponent extends InputComponent {
	public static float JUMP_ACCELERATION = 7f;
	public static float MOVE_ACCELERATION = 0.1f;

	/**
	 * Handles input for player
	 */
	@Override
	public void processInput() {
		AccelerationComponent acceleration = this.entity.getComponent(AccelerationComponent.class);
		CollisionComponent collision = this.entity.getComponent(CollisionComponent.class);

		if (Gdx.input.isKeyPressed(Assets.keys.get("move_left"))) {
			acceleration.x -= MOVE_ACCELERATION;
		}

		if (Gdx.input.isKeyPressed(Assets.keys.get("move_right"))) {
			acceleration.x += MOVE_ACCELERATION;
		}

		if (collision.onGround && Gdx.input.isKeyPressed(Assets.keys.get("jump"))) {
			acceleration.y += JUMP_ACCELERATION;
		}
	}
}