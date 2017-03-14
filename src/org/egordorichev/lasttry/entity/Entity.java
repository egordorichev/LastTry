package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Block;
import org.egordorichev.lasttry.util.Direction;
import org.egordorichev.lasttry.util.Rectangle;
import org.egordorichev.lasttry.util.Vector2f;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public abstract class Entity {
	/**
	 * Absolute speed below this value is set to 0, which makes comparisons of
	 * velocity == 0 much simpler.
	 */
	private static final float STOP_VELOCITY = 0.1F;
	/**
	 * Current hit-points out of the total {@link #maxHp maximum}.
	 */
	protected int hp;
	/**
	 * Maximum hit-points.
	 */
	protected int maxHp;
	/**
	 * Base defense-points.
	 */
	protected int defense;
	/**
	 * Base damage-points.
	 */
	protected int damage;
	/**
	 * Invulnerability status. If enabled the entity will not be able to take
	 * damage.
	 */
	protected boolean invulnerable;
	/**
	 * Status declaring if the entity should receive updates. If this is false,
	 * the entity is dead and should be removed.
	 */
	protected boolean shouldUpdate;
	/**
	 * If is set to true, this is NPC or Animal
	 */
	protected boolean isFriendly;
	/**
	 * Status for if the entity's hitbox should be recognized. Non-solid
	 * entities can clip through tiles and other entities like a ghost.
	 */
	protected boolean isSolid;
	/**
	 * The name of the entity.
	 */
	protected String name;
	/**
	 * The dimensions that the entity is rendered in.
	 */
	protected Rectangle renderBounds;
	/**
	 * The dimensions that the entity takes up in 2D space.
	 */
	protected Rectangle hitbox;
	/**
	 * The image containing the entity's sprite map.
	 */
	protected Image texture;
	/**
	 * The array of animations the entity has. See {@link #state} for the
	 * Indices and their associated animations.
	 */
	protected Animation[] animations;
	/**
	 * The animation state of the entity.
	 */
	protected State state;
	/**
	 * The directional motion of the entity.
	 */
	protected Vector2f velocity;
	/**
	 * The horizontal direction the entity faces. Possible values:
	 * <ul>
	 * <li>{@link org.egordorichev.lasttry.util.Direction#LEFT Left}</li>
	 * <li>{@link org.egordorichev.lasttry.util.Direction#RIGHT Right}</li>
	 * </ul>
	 */
	protected Direction direction;

	/**
	 * Animation state of the entity.
	 */
	public enum State {
		IDLE(0), MOVING(1), JUMPING(2), FALLING(3), FLYING(4), DEAD(5);

		/**
		 * State identifier. Used as the key for
		 * {@link org.egordorichev.lasttry.entity.Entity#animations
		 * Entity.animations}
		 */
		private int id;

		State(int id) {
			this.id = id;
		}

		/**
		 * Return the state's {@link #id identifier}.
		 * 
		 * @return State's ID.
		 */
		public int getId() {
			return this.id;
		}
	}

	public Entity(String name, boolean friendly, int maxHp, int defense, int damage) {
		this.name = name;
		this.isFriendly = friendly;
		this.defense = defense;
		this.maxHp = maxHp;
		this.hp = this.maxHp;
		this.shouldUpdate = false;
		this.invulnerable = false;
		this.renderBounds = new Rectangle(0, 0, 32, 48);
		this.hitbox = new Rectangle(this.renderBounds.x + 3, this.renderBounds.y + 3, this.renderBounds.width - 6,
				this.renderBounds.height - 3);
		this.velocity = new Vector2f(0, 0);
		this.direction = Direction.RIGHT;
		this.isSolid = true;

		this.animations = new Animation[State.values().length];
		this.state = State.IDLE;
	}

	public Entity(String name, boolean friendly) {
		this(name, friendly, 10, 0, 0);
	}

	/**
	 * Renders the entity using the current {@link #state}. The sprite may be
	 * flipped based on their {@link #direction}.
	 */
	public void render() {
		this.animations[this.state.getId()].getCurrentFrame().getFlippedCopy(
				this.direction == Direction.RIGHT, false)
				.draw(this.renderBounds.x, this.renderBounds.y);
	}

	/**
	 * Updates the entity's velocity, physical and render positions, and
	 * animation state.
	 * 
	 * Skipped if {@link #shouldUpdate} is not true.
	 * 
	 * @param dt
	 *            The milliseconds passed since the last update.
	 */
	public void update(int dt) {
		if (!this.shouldUpdate) {
			return;
		}

		// Increment velocity downwards.
		// TODO: Gravity should not be a constant, but instead should be based
		// off of the y-position in the world.
		// For example: Higher up (such as in outer space) should have a lower
		// value).
		if (this.state != State.FLYING) {
			this.velocity.y += 0.4f;
		}

		// TODO: Optimize and handle both X/Y velocity in one pass.
		// Current issue: If gravity (above) causes collision on
		// y-axis a combined xy update check will say there is a
		// collision and prevent motion updates for both x and y velocities
		// even if there is no x-axis specific collision.
		//
		// If the entity is not still on the x-axis, update their position based
		// on their current velocity.
		if (this.velocity.x != 0) {
			Rectangle newHitbox = new Rectangle(this.hitbox.x, this.hitbox.y, this.hitbox.width, this.hitbox.height);

			newHitbox.x += this.velocity.x;

			if (!this.isSolid || !LastTry.world.isColliding(newHitbox)) {
				this.hitbox = newHitbox;
				this.renderBounds.x += this.velocity.x;
			} else {
				this.velocity.x = 0;
			}

			this.velocity.x *= 0.8;
			if (Math.abs(this.velocity.x) < Entity.STOP_VELOCITY) {
				this.velocity.x = 0;
			}
		}
		// If the entity is not still on the y-axis, update their position based
		// on their current velocity.
		if (this.velocity.y != 0) {
			Rectangle newHitbox = new Rectangle(this.hitbox.x, this.hitbox.y, this.hitbox.width, this.hitbox.height);

			newHitbox.y += this.velocity.y;

			if (!this.isSolid || !LastTry.world.isColliding(newHitbox)) {
				this.hitbox = newHitbox;
				this.renderBounds.y += this.velocity.y;
			} else {
				this.velocity.y = 0;
			}

			if (this.state == State.FLYING) {
				this.velocity.y *= 0.8;
				if (Math.abs(this.velocity.y) < Entity.STOP_VELOCITY) {
					this.velocity.y = 0;
				}
			}
		}

		// Update state if applicable.
		if (this.state != State.FLYING) {
			if (this.velocity.y > 0) {
				this.state = State.FALLING;
			} else if (this.velocity.y == 0 && this.state == State.FALLING) {
				this.state = State.IDLE;
			}
			if (this.velocity.x == 0 && this.state != State.IDLE && this.state != State.FALLING
					&& this.state != State.JUMPING) {
				this.state = State.IDLE;
			}
		}
		// Update animations
		this.animations[this.state.getId()].update(dt);
	}

	/**
	 * Update the entity x-axis velocity based on the given direction.
	 * 
	 * @param direction
	 *            Direction to add velocity to.
	 */
	public void move(Direction direction) {
		if (!this.shouldUpdate) {
			return;
		}

		this.velocity.x += (direction == Direction.LEFT) ? -1 : 1;
		this.direction = direction;

		if (this.state != State.JUMPING && this.state != State.FALLING && this.state != State.FLYING) {
			this.state = State.MOVING;
		}
	}

	/**
	 * Update the entity y-axis {@link #velocity} with a upwards boost.
	 */
	public void jump() {
		if (!this.shouldUpdate || this.velocity.y != 0) {
			return;
		}

		this.state = State.JUMPING;
		// Note: Jumping up by subtracting velocity seems counter-intuitive.
		this.velocity.y -= 10.0f;
	}

	/**
	 * Spawn the entity in the world at the given position.
	 * 
	 * @param x
	 *            X positon to spawn entity at.
	 * @param y
	 *            Y position to spawn entity at.
	 */
	public void spawn(int x, int y) {
		this.shouldUpdate = true;
		this.renderBounds.setPosition(x * Block.TEX_SIZE, y * Block.TEX_SIZE);
		this.hitbox.setPosition(x * Block.TEX_SIZE + 3, y * Block.TEX_SIZE + 3);
		this.onSpawn();
	}

	/**
	 * Hurt the entity by subtracting {@link #hp hit-points}. The damage taken
	 * by the entity will be modified by their {@link #defense}.
	 * 
	 * @param damage
	 *            Damage to give, unaffected by entity defense points.
	 */
	public void hurt(int damage) {
		this.modifyHp(Math.max(-1, -damage + defense / 2));
	}

	/**
	 * Update the entity's {@link #hp hit-points} if it is not
	 * {@link #invulnerable}.
	 * 
	 * @param amount
	 *            Value to increment hit-points by.
	 */
	public void modifyHp(int amount) {
		if (!this.invulnerable) {
			this.hp = Math.min(Math.max(0, this.hp + amount), this.maxHp);

			if (this.hp == 0) {
				this.die();
			}
		}
	}

	/**
	 * Update the entity's {@link #maxHp maximum hit-points}.
	 * 
	 * @param amount
	 *            Value to increment max health by.
	 */
	public void modifyMaxHp(int amount) {
		this.maxHp = Math.max(0, this.maxHp + amount);
	}

	/**
	 * Update the entity's {@link #defense defense-points}.
	 * 
	 * @param amount
	 *            Value to set {@link #defense defense-points} to.
	 */
	public void modifyDefense(int amount) {
		this.defense = Math.max(0, this.defense + amount);
	}

	/**
	 * Mark the entity as dead, disable entity updates.
	 */
	public void die() {
		this.shouldUpdate = false;
	}

	/**
	 * TODO
	 */
	public void onSpawn() {

	}

	/**
	 * Set the entity's {@link #defense defense-points}.
	 * 
	 * @param defense
	 *            Value to set defense to.
	 */
	public void setDefense(int defense) {
		this.defense = defense;
	}

	/**
	 * Return the entity's {@link #defense defense-points}.
	 * 
	 * @return Defense-points.
	 */
	public int getDefense() {
		return this.defense;
	}

	/**
	 * Set the entity's {@link #hp hit-points}.
	 * 
	 * @param hp
	 *            Value to set hit-points to.
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}

	/**
	 * Return the entity's {@link #hp hit-points}.
	 * 
	 * @return Hit-points.
	 */
	public int getHp() {
		return this.hp;
	}

	/**
	 * Set the entity's {@link #maxHp maximum hit-points}.
	 * 
	 * @param maxHp
	 *            Value to set maximum hit-points to.
	 */
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	/**
	 * Return the entity's {@link #maxHp maximum hit-points}.
	 * 
	 * @return Maximum hit-points.
	 */
	public int getMaxHp() {
		return this.maxHp;
	}

	/**
	 * Return the entity's {@link #name}.
	 * 
	 * @return Name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Return the entity's {@link #isSolid solidity}.
	 * 
	 * @return Solid status.
	 */
	public boolean isSolid() {
		return this.isSolid;
	}

	/**
	 * Return the world grid x-position of the entity based on the render
	 * bounds.
	 * 
	 * @return World grid x-position
	 */
	public int getGridX() {
		return (int) this.renderBounds.x / Block.TEX_SIZE;
	}

	/**
	 * Return the world grid y-position of the entity based on the render
	 * bounds.
	 * 
	 * @return World grid y-position
	 */
	public int getGridY() {
		return (int) this.renderBounds.y / Block.TEX_SIZE;
	}

	/**
	 * Return the entity's x-position.
	 * 
	 * @return Render x-position.
	 */
	public float getX() {
		return this.renderBounds.x;
	}

	/**
	 * Return the entity's y-position.
	 * 
	 * @return Render y-position.
	 */
	public float getY() {
		return this.renderBounds.y;
	}

	/**
	 * Return the entity's width.
	 * 
	 * @return Render width.
	 */
	public float getWidth() {
		return this.renderBounds.width;
	}

	/**
	 * Return the entity's height.
	 * 
	 * @return Render height.
	 */
	public float getHeight() {
		return this.renderBounds.height;
	}
}