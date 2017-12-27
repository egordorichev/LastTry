package org.egordorichev.lasttry.entity.entities.creature.ai;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.physics.AccelerationComponent;
import org.egordorichev.lasttry.entity.component.physics.CollisionComponent;
import org.egordorichev.lasttry.entity.component.physics.VelocityComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.entities.creature.Creature;
import org.egordorichev.lasttry.entity.entities.item.ItemEntity;
import org.egordorichev.lasttry.entity.entities.item.inventory.InventoryComponent;
import org.egordorichev.lasttry.entity.entities.item.inventory.ItemComponent;
import org.egordorichev.lasttry.util.math.NumberUtil;

/**
 * Player AI
 */
public class PlayerAi extends Ai {
	/**
	 * How strongly the player is pulled down when in the air. 
	 */
	private static final float GRAVITY_FORCE = -.04f;
	/**
	 * Maximum vertical speed.
	 */
	private static final float VERTICAL_SPEED_MAX = 10f;
	/**
	 * How strongly the player jumps. Competes with gravity.
	 */
	private static final float JUMP_FORCE = 5f;
	/**
	 * How strongly the player moves horizontally when in the air.
	 */
	private static final float AIR_MOVE_FORCE_X = .6f;
	/**
	 * Maximum horizontal speed when in the air.
	 */
	private static final float AIR_MOVE_SPEED_MAX = 1.2f;
	/**
	 * How much horizontal resistance the air has. Sensible values in range: [0, 1]
	 * Lower means less resistance.
	 */
	private static final float AIR_FRICTION = .3f;
	/**
	 * How strongly the player moves horizontally when on the ground.
	 */
	private static final float GROUND_MOVE_FORCE_X = .7f;
	/**
	 * Maximum horizontal speed when on the ground.
	 */
	private static final float GROUND_MOVE_SPEED_MAX = 1.3f;
	/**
	 * How much horizontal resistance the ground has. Sensible values in range: [0, 1]
	 * Lower means less resistance.
	 */
	private static final float GROUND_FRICTION = .3f;

	public PlayerAi() {
		this.registerAll("idle", "walking", "flying");
	}

	public void idle(Creature self, int t) {
		AccelerationComponent acceleration = self.getComponent(AccelerationComponent.class);
		CollisionComponent collision = self.getComponent(CollisionComponent.class);

		if (collision.onGround && Gdx.input.isKeyPressed(Assets.keys.get("jump"))) {
			acceleration.y += JUMP_FORCE;
			self.become("flying");
			return;
		}

		if (Gdx.input.isKeyPressed(Assets.keys.get("move_left"))
			|| Gdx.input.isKeyPressed(Assets.keys.get("move_right"))) {
			self.become("walking");
			return;
		}

		acceleration.y += GRAVITY_FORCE;
	}

	public void walking(Creature self, int t) {
		AccelerationComponent acceleration = self.getComponent(AccelerationComponent.class);
		VelocityComponent velocity = self.getComponent(VelocityComponent.class);
		CollisionComponent collision = self.getComponent(CollisionComponent.class);

		if (collision.onGround && Gdx.input.isKeyPressed(Assets.keys.get("jump"))) {
			acceleration.y += JUMP_FORCE;
			self.become("flying");
			return;
		}

		if (Math.abs(velocity.x) < 0.01f) {
			velocity.x = 0;
			self.become("idle");
		}

		if (Gdx.input.isKeyPressed(Assets.keys.get("move_left"))) {
			acceleration.x -= GROUND_MOVE_FORCE_X;
		}

		if (Gdx.input.isKeyPressed(Assets.keys.get("move_right"))) {
			acceleration.x += GROUND_MOVE_FORCE_X;
		}

		velocity.x *= (1f - GROUND_FRICTION);
		velocity.x = NumberUtil.clamp(velocity.x, -GROUND_MOVE_SPEED_MAX, GROUND_MOVE_SPEED_MAX);

		acceleration.y += GRAVITY_FORCE;
	}

	public void flying(Creature self, int t) {
		AccelerationComponent acceleration = self.getComponent(AccelerationComponent.class);
		VelocityComponent velocity = self.getComponent(VelocityComponent.class);
		CollisionComponent collision = self.getComponent(CollisionComponent.class);

		if (collision.onGround) {
			self.become("walking");
			return;
		} else {
			if (Gdx.input.isKeyPressed(Assets.keys.get("move_left"))) {
				acceleration.x -= AIR_MOVE_FORCE_X;
			} else if (Gdx.input.isKeyPressed(Assets.keys.get("move_right"))) {
				acceleration.x += AIR_MOVE_FORCE_X;
			}
		}

		velocity.x *= (1f - AIR_FRICTION);
		velocity.x = NumberUtil.clamp(velocity.x, -AIR_MOVE_SPEED_MAX, AIR_MOVE_SPEED_MAX);

		acceleration.y += GRAVITY_FORCE;
		velocity.y = NumberUtil.clamp(velocity.y, -VERTICAL_SPEED_MAX, VERTICAL_SPEED_MAX);
	}

	@Override
	public void collide(Entity self, Entity entity) {
		if (entity instanceof ItemEntity) {
			InventoryComponent inventory = self.getComponent(InventoryComponent.class);
			ItemEntity item = (ItemEntity) entity;

			if (inventory.add(item.getComponent(ItemComponent.class))) {
				Engine.removeEntity(entity);
			}
		}
	}
}