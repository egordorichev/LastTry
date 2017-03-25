package org.egordorichev.lasttry.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.effect.Effect;
import org.egordorichev.lasttry.effect.EffectData;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Rectangle;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
	/**
	 * Absolute speed below this value is set to 0, which makes comparisons of
	 * velocity == 0 much simpler
	 */
	private static final float STOP_VELOCITY = 0.1F;

	/** Current hit-points out of the total {@link #maxHp maximum} */
	protected int hp;

	/** Maximum hit-points */
	protected int maxHp;

	/** The height in blocks the entity can step */
	protected float stepHeight = 1.05F;

	/** Base defense-points */
	protected int defense;

	/** Base damage-points */
	protected int damage;

	/**
	 * Invulnerability status. If enabled the entity will not be able to take
	 * damage
	 */
	protected boolean invulnerable;

	/**
	 * Status declaring if the entity should receive updates. If this is false,
	 * the entity is dead and should be removed.
	 */
	protected boolean shouldUpdate = true;

	/**
	 * Status for if the entity's hitbox should be recognized. Non-solid
	 * entities can clip through block and other entities like a ghost.
	 */
	protected boolean isSolid = true;

	/** The dimensions that the entity is rendered in */
	protected Rectangle renderBounds;

	/** The dimensions that the entity takes up in 2D space */
	protected Rectangle hitbox;

	/** The image containing the entity's sprite map */
	protected Texture texture;

	/** The animation state of the entity */
	protected State state = State.IDLE;

	/** The directional motion of the entity. */
	protected Vector2 velocity;

	/** The horizontal direction the entity faces */
	protected Direction direction = Direction.RIGHT;

	/** Effects, applyed on entity */
	protected List<EffectData> effects = new ArrayList<>();

	/** Animation state of the entity */
	public enum State {
		IDLE(0), MOVING(1), JUMPING(2), FALLING(3), FLYING(4), DEAD(5);

		/**
		 * State identifier.
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

	public Entity(int maxHp, int defense, int damage) {
		this.defense = defense;
		this.hp = this.maxHp = maxHp;
		this.renderBounds = new Rectangle(0, 0, 32, 48);
		this.hitbox = new Rectangle(this.renderBounds.x + 3, this.renderBounds.y + 3, this.renderBounds.width - 6,
				this.renderBounds.height - 3);
		this.velocity = new Vector2(0, 0);
	}

	public Entity() {
		this(10, 0, 0);
	}

	/**
	 * Renders the entity using the current {@link #state}. The sprite may be
	 * flipped based on their {@link #direction}.
	 */
	public void render() {
		// this.texture.getFlippedCopy(this.direction == Direction.RIGHT, false).draw(this.renderBounds.x,
		//		this.renderBounds.y);
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

		for (int i = this.effects.size() - 1; i >= 0; i--) {
			this.effects.get(i).update(dt);

			if (this.effects.get(i).isDone()) {
				this.effects.remove(i);
			}
		}

		// Regeneration

		/*
		 * boolean still = this.velocity.x == 0 && this.velocity.y == 0;
		 * 
		 * 
		 * int buffs = 0; // TODO: count buffs int time = 1; // TODO: time since
		 * last hit
		 * 
		 * float regen = ((this.maxHp / 400.0f) * 0.85f + 0.15f) * time + buffs
		 * * ((still) ? 0.5f : 1.25f) (LastTry.world.isExpert() ? 1 : 0.5f);
		 * 
		 * this.hp = Math.min(this.maxHp, this.hp + (int) regen);
		 */

		// Increment velocity downwards.
		// TODO: Gravity should not be a constant, but instead should be based
		// off of the y-position in the world.
		// For example: Higher up (such as in outer space) should have a lower
		// value).
		if (this.state != State.FLYING) {
			this.velocity.y += 0.4f;
		}

		/*
		 * TODO: Optimize and handle both X/Y velocity in one pass. Current
		 * issue: If gravity (above) causes collision on y-axis a combined xy
		 * update check will say there is a collision and prevent motion updates
		 * for both x and y velocities even if there is no x-axis specific
		 * collision.
		 *
		 * If the entity is not still on the x-axis, update their position based
		 * on their current velocity.
		 */

		if (this.velocity.x != 0) {
			Rectangle newHitbox = new Rectangle(this.hitbox.x + this.getX(), this.hitbox.y + this.getY(),
					this.hitbox.width, this.hitbox.height);

			newHitbox.x += this.velocity.x;

			// If the entity is not solid, they can go anywhere

			if (!this.isSolid) {
				this.renderBounds.x += this.velocity.x;
			} else {
				// If they collide with the x-velocity applied...

				if (LastTry.world.isColliding(newHitbox)) {
					/*
					 * If they collide with a block when their Y-position is
					 * shifted up by a block then they are climbing up a wall
					 * that is too steep to climb.
					 */

					float step = Block.TEX_SIZE * stepHeight;

					if (LastTry.world.isColliding(newHitbox.offset(0, -step))) {
						// Intersection with a wall too steep to climb
						this.velocity.x = 0;
					} else {
						// Wall isn't steep, can be climbed by entity.

						this.renderBounds.x += this.velocity.x;
						this.renderBounds.y -= Block.TEX_SIZE / 2;
					}
				} else {
					// Moving normally
					this.renderBounds.x += this.velocity.x;
				}
			}

			this.velocity.x *= 0.8;
			if (Math.abs(this.velocity.x) < Entity.STOP_VELOCITY) {
				this.velocity.x = 0;
			}
		}

		/*
		 * If the entity is not still on the y-axis, update their position based
		 * on their current velocity.
		 */

		if (this.velocity.y != 0) {
			Rectangle newHitbox = new Rectangle(this.hitbox.x + this.getX(), this.hitbox.y + this.getY(),
					this.hitbox.width, this.hitbox.height);

			newHitbox.y += this.velocity.y;

			if (!this.isSolid || !LastTry.world.isColliding(newHitbox)) {
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

	public void fly(int x, int y) {
		if (x > 1 || x < -1) {
			x /= x;
		}

		if (y > 1 || y < -1) {
			y /= y;
		}

		this.velocity.x += x;
		this.velocity.y += y;

		this.state = State.FLYING;
	}

	/**
	 * Adds effect to entity, but if it is already applied, updates it's time
	 * 
	 * @param effect
	 *            effect to apply
	 * @param time
	 *            time of effect
	 */
	public void addEffect(Effect effect, int time) {
		for (EffectData effectData : this.effects) {
			if (effectData.getEffect() == effect) {
				effectData.setTime(time);
				return;
			}
		}

		this.effects.add(new EffectData(this, effect, time));
	}

	/**
	 * Removes effect from entity
	 * 
	 * @param effect
	 *            effect to apply
	 */
	public void removeEffect(Effect effect) {
		for (int i = 0; i < this.effects.size(); i++) {
			EffectData effectData = this.effects.get(i);

			if (effectData.getEffect() == effect) {
				this.effects.remove(i);
			}
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
	public void spawn(float x, float y) {
		this.shouldUpdate = true;
		this.renderBounds.setPosition(x * Block.TEX_SIZE, y * Block.TEX_SIZE);
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
	 * Mark the entity as dead, disable entity updates. The entity is added to
	 * the current world's removal list.
	 */
	public void die() {
		this.shouldUpdate = false;
		this.onDeath();
		LastTry.entityManager.markForRemoval(this);
	}

	/**
	 * This method is called when entity spawns
	 */
	public void onSpawn() {

	}

	/**
	 * This method is called when entity dies
	 */
	public void onDeath() {

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

	public void setDamage(int damage) {
		this.damage = damage;
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
	 * Return the width of the entity based on the render bounds,
	 * 
	 * @return World grid width.
	 */
	public int getGridWidth() {
		return (int) this.renderBounds.width / Block.TEX_SIZE;
	}

	/**
	 * Return the height of the entity based on the render bounds,
	 * 
	 * @return World grid height.
	 */
	public int getGridHeight() {
		return (int) this.renderBounds.height / Block.TEX_SIZE;
	}

	/**
	 * Sets the velocity of the entity.
	 * 
	 * @param x
	 *            New x-axis speed.
	 * @param y
	 *            New y-axis speed.
	 */
	public void setVelocity(float x, float y) {
		this.velocity.x = x;
		this.velocity.y = y;
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

	public float getCenterX() {
		return this.getX() + this.getWidth() / 2;
	}

	public float getCenterY() {
		return this.getY() + this.getHeight() / 2;
	}

	public Rectangle getHitbox() {
		return new Rectangle(this.getX() + this.hitbox.x, this.getY() + this.hitbox.y, this.hitbox.width,
				this.hitbox.height);
	}
}
