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
	public static float JUMP_ACCELERATION = 7f;
	public static float FLY_ACCELERATION = 0.2f;
	public static float MOVE_ACCELERATION = 0.8f;

	public PlayerAi() {
		this.registerAll("idle", "walking", "flying");
	}

	public void idle(Creature self, int t) {
		AccelerationComponent acceleration = self.getComponent(AccelerationComponent.class);
		VelocityComponent velocity = self.getComponent(VelocityComponent.class);
		CollisionComponent collision = self.getComponent(CollisionComponent.class);

		if (collision.onGround && Gdx.input.isKeyPressed(Assets.keys.get("jump"))) {
			acceleration.y += JUMP_ACCELERATION;
			self.become("flying");
			return;
		}

		if (Gdx.input.isKeyPressed(Assets.keys.get("move_left"))
			|| Gdx.input.isKeyPressed(Assets.keys.get("move_right"))) {
			self.become("walking");
		}

		velocity.x *= 0.93;
		velocity.y *= 0.93;
	}

	public void walking(Creature self, int t) {
		AccelerationComponent acceleration = self.getComponent(AccelerationComponent.class);
		VelocityComponent velocity = self.getComponent(VelocityComponent.class);
		CollisionComponent collision = self.getComponent(CollisionComponent.class);

		if (collision.onGround && Gdx.input.isKeyPressed(Assets.keys.get("jump"))) {
			acceleration.y += JUMP_ACCELERATION;
			self.become("flying");
			return;
		}

		if (Math.abs(velocity.x) < 0.5) {
			velocity.x = 0;
			self.become("idle");
		}

		if (Gdx.input.isKeyPressed(Assets.keys.get("move_left"))) {
			acceleration.x -= MOVE_ACCELERATION;
		}

		if (Gdx.input.isKeyPressed(Assets.keys.get("move_right"))) {
			acceleration.x += MOVE_ACCELERATION;
		}

		velocity.x *= 0.93;
		velocity.y *= 0.93;
		velocity.x = NumberUtil.clamp(velocity.x, -3, 3);
	}

	public void flying(Creature self, int t) {
		AccelerationComponent acceleration = self.getComponent(AccelerationComponent.class);
		VelocityComponent velocity = self.getComponent(VelocityComponent.class);
		CollisionComponent collision = self.getComponent(CollisionComponent.class);

		if (collision.onGround) {
			self.become("idle");
			return;
		}

		if (Gdx.input.isKeyPressed(Assets.keys.get("move_left"))) {
			acceleration.x -= FLY_ACCELERATION;
			self.become("walking");
		}

		if (Gdx.input.isKeyPressed(Assets.keys.get("move_right"))) {
			acceleration.x += FLY_ACCELERATION;
			self.become("walking");
		}

		velocity.x *= 0.93;
		velocity.y *= 0.93;

		velocity.y = NumberUtil.clamp(velocity.y, -10, 10);
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