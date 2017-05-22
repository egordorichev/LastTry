package org.egordorichev.lasttry.entity.components;

import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.drop.DroppedItem;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Log;
import org.egordorichev.lasttry.util.Rectangle;

public class PhysicsComponent extends EntityComponent {
	public enum Direction {
		LEFT,
		RIGHT
	}

	protected static final float STOP_VELOCITY = 0.2F;
	protected static final float STEP_HEIGHT = 1.05F;

	protected Vector2 position = new Vector2();
	protected Vector2 size = new Vector2();
	protected Vector2 velocity = new Vector2();
	protected Rectangle hitbox;
	protected Direction direction = Direction.RIGHT;
	protected float speed = 1.0f;
	protected boolean solid = true;

	public PhysicsComponent(Entity entity) {
		super(entity);
	}

	public PhysicsComponent() {
		this.hitbox = new Rectangle(0, 0, 0, 0);
	}

	@Override
	public void setEntity(Entity entity) {
		super.setEntity(entity);

		this.size = new Vector2(32, 48); // TODO: get the size
		this.hitbox = new Rectangle(3, 3, this.size.x - 6, this.size.y - 3);
	}

	public void update(int dt) {
		if (!this.entity.isActive()) {
			return;
		}

		if (this.solid) {
			this.velocity.y -= 0.4f;
		}

		this.updateXVelocity();
		this.updateYVelocity();
	}

	public void jump() {

	}

	public void move(Direction direction) {

	}

	private void updateXVelocity() {
		if (this.velocity.x != 0) {
			
			Rectangle newHitbox = new Rectangle(
					this.hitbox.x + this.position.x, 
					this.hitbox.y + this.position.y,
					this.hitbox.width, 
					this.hitbox.height);
			newHitbox.x += this.velocity.x;

			if (!this.solid) {
				this.position.x += this.velocity.x;
			} else {
				if (Globals.world.isColliding(newHitbox)) {
					float step = Block.SIZE * STEP_HEIGHT;

					if (Globals.world.isColliding(newHitbox.offset(0, step))) {
						this.velocity.x = 0;
						this.onBlockCollide();
					} else {
						this.position.x += this.velocity.x;
						this.position.y += Block.SIZE / 2;
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

			if (!this.solid || !Globals.world.isColliding(newHitbox)) {
				this.position.y += this.velocity.y;
			} else {
				this.velocity.y = 0;
				this.onBlockCollide();
			}
		}
	}

	protected void onBlockCollide() {
		// TODO: callback?
	}

	public void setGridPosition(float gridX, float gridY) {
		this.position.x = gridX * Block.SIZE;
		this.position.y = gridY * Block.SIZE;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public void setPosition(float x, float y) {
		this.position.x = x;
		this.position.y = y;
	}

	public void setSize(int width, int height) {
		this.size.x = width;
		this.size.y = height;
	}

	public boolean isFlipped() {
		return this.direction == Direction.LEFT;
	}

	public Vector2 getPosition() {
		return this.position;
	}

	public Vector2 getSize() {
		return this.size;
	}

	public int getGridX() {
		return (int) this.position.x / Block.SIZE;
	}

	public int getGridY() {
		return (int) this.position.y / Block.SIZE;
	}

	public float getX() {
		return this.position.x;
	}

	public float getY() {
		return this.position.y;
	}

	public float getCenterX() {
		return this.position.x + this.size.x / 2;
	}

	public float getCenterY() {
		return this.position.y + this.size.y / 2;
	}

	public Rectangle getHitbox() {
		return new Rectangle(this.getX() + this.hitbox.x, this.getY() + this.hitbox.y, this.hitbox.width,
			this.hitbox.height);
	}

	public Direction getDirection() {
		return this.direction;
	}

	public Vector2 getVelocity() {
		return this.velocity;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}

	public float getSpeed() {
		return this.speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}