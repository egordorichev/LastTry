package org.egordorichev.lasttry.entity.components;

import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.PhysicBody;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Rectangle;

public class PhysicsComponent extends Component {
	private static final float STOP_VELOCITY = 0.2F;
	private static final float STEP_HEIGHT = 1.05F;

	private Entity entity;
	private Vector2 position = new Vector2();
	private Vector2 size = new Vector2();
	private Vector2 velocity = new Vector2();
	private boolean solid;
	private Rectangle hitbox;

	public PhysicsComponent(Entity entity) {
		this.entity = entity;
		this.size = new Vector2(32, 48);
		this.hitbox = new Rectangle(3, 3, this.size.x - 6, this.size.y - 3);
	}

	public void update(int dt) {
		if (!this.entity.isActive()) {
			return;
		}

		this.velocity.y += 0.4f;

		this.updateXVelocity();
		this.updateYVelocity();

		/*
			if (this.velocity.y > 0) {
				this.state = PhysicBody.State.FALLING;
			} else if (this.velocity.y == 0 && this.state == PhysicBody.State.FALLING) {
				this.state = PhysicBody.State.IDLE;
			}

			if (this.velocity.x == 0 && this.state != PhysicBody.State.IDLE && this.state != PhysicBody.State.FALLING
					&& this.state != PhysicBody.State.JUMPING) {
				this.state = PhysicBody.State.IDLE;
			}
		*/
	}

	private void updateXVelocity() {
		if (this.velocity.x != 0) {
			Rectangle newHitbox = new Rectangle(this.hitbox.x + this.position.x, this.hitbox.y + this.position.y,
				this.hitbox.width, this.hitbox.height);

			newHitbox.x += this.velocity.x;

			if (!this.solid) {
				this.position.x += this.velocity.x;
			} else {
				if (LastTry.world.isColliding(newHitbox)) {
					float step = Block.SIZE * STEP_HEIGHT;

					if (LastTry.world.isColliding(newHitbox.offset(0, -step))) {
						this.velocity.x = 0;
						this.onBlockHit();
					} else {
						this.position.x += this.velocity.x;
						this.position.y -= Block.SIZE / 2;
					}
				} else {
					this.position.x += this.velocity.x;
				}
			}

			this.velocity.x *= 0.8;

			if (Math.abs(this.velocity.x) < STOP_VELOCITY) {
				this.velocity.x = 0;
			}
		}
	}

	private void updateYVelocity() {
		if (this.velocity.y != 0) {
			Rectangle newHitbox = new Rectangle(this.hitbox.x + this.position.x, this.hitbox.y + this.position.y,
				this.hitbox.width, this.hitbox.height);

			newHitbox.y += this.velocity.y;

			if (!this.solid || !LastTry.world.isColliding(newHitbox)) {
				this.position.y += this.velocity.y;
			} else {
				this.velocity.y = 0;
				this.onBlockHit();
			}
		}
	}

	protected void onBlockHit() {

	}

	public void setGridPosition(float gridX, float gridY) {
		this.position.x = gridX * Block.SIZE;
		this.position.y = gridY * Block.SIZE;
	}

	public void setPosition(float x, float y) {
		this.position.x = x;
		this.position.y = y;
	}

	public Vector2 getPosition() {
		return this.position;
	}

	public int getGridX() {
		return (int) this.position.x / Block.SIZE;
	}

	public int getGridY() {
		return (int) this.position.y / Block.SIZE;
	}
}