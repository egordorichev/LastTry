package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Block;
import org.egordorichev.lasttry.util.Direction;
import org.egordorichev.lasttry.util.Rectangle;
import org.egordorichev.lasttry.util.Vector2f;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public class Entity {
	protected int hp;
	protected int maxHp;
	protected int defense;
	protected int damage;
	protected boolean invulnerable;
	protected boolean active;
	protected boolean friendly;
	protected boolean solid;
	protected String name;
	protected Rectangle rect;
	protected Rectangle hitbox;
	protected Image image;
	protected Animation[] animations;
	protected State state;
	protected Vector2f velocity;
	protected Direction direction;

	public enum State {
		IDLE(0),
		MOVING(1),
		JUMPING(2),
		FALLING(3),
		FLYING(4),
		DEAD(5);

		private int id;

		State(int id) {
			this.id = id;
		}

		public int getId() {
			return this.id;
		}
	}

	public Entity(String name, boolean friendly, int maxHp, int defense, int damage) {
		this.name = name;
		this.friendly = friendly;
		this.defense = defense;
		this.maxHp = maxHp;
		this.hp = this.maxHp;
		this.active = false;
		this.invulnerable = false;
		this.rect = new Rectangle(0, 0, 32, 48);
		this.hitbox = new Rectangle(this.rect.x + 3, this.rect.y + 3, this.rect.width - 6, this.rect.height - 3);
		this.velocity = new Vector2f(0, 0);
		this.direction = Direction.RIGHT;
		this.solid = true;

		this.animations = new Animation[State.values().length];
		this.state = State.IDLE;
	}

	public Entity(String name, boolean friendly) {
		this(name, friendly, 10, 0, 0);
	}

	public void render() {
		this.animations[this.state.getId()].getCurrentFrame().getFlippedCopy(this.direction == Direction.RIGHT, false).draw(this.rect.x, this.rect.y);
	}

	public void update(int dt) {
		if(!this.active) {
			return;
		}

		if(this.state != State.FLYING) {
			this.velocity.y += 0.4f;
		}

		if(this.velocity.x != 0) {
			Rectangle newHitbox = new Rectangle(this.hitbox.x, this.hitbox.y, this.hitbox.width, this.hitbox.height);

			newHitbox.x += this.velocity.x;

			if(!this.solid || !LastTry.world.isColliding(newHitbox)) {
				this.hitbox = newHitbox;
				this.rect.x += this.velocity.x;
			} else {
				this.velocity.x = 0;
			}

			this.velocity.x *= 0.8;
		}

		if(this.velocity.y != 0) {
			Rectangle newHitbox = new Rectangle(this.hitbox.x, this.hitbox.y, this.hitbox.width, this.hitbox.height);

			newHitbox.y += this.velocity.y;

			if(!this.solid || !LastTry.world.isColliding(newHitbox)) {
				this.hitbox = newHitbox;
				this.rect.y += this.velocity.y;
			} else {
				this.velocity.y = 0;
			}

			if(this.state == State.FLYING) {
				this.velocity.y *= 0.8;
			}
		}

		if(this.state != State.FLYING) {
			if(this.velocity.y > 0) {
				this.state = State.FALLING;
			} else if(this.velocity.y == 0 && this.state == State.FALLING) {
				this.state = State.IDLE;
			}

			if(this.velocity.x == 0 && this.state != State.IDLE && this.state != State.FALLING && this.state != State.JUMPING) {
				this.state = State.IDLE; // Does not work
			}
		}

		this.animations[this.state.getId()].update(dt);
	}

	public void move(Direction direction) {
		if(!this.active) {
			return;
		}

		this.velocity.x += (direction == Direction.LEFT) ? -1 : 1;
		this.direction = direction;

		if(this.state != State.JUMPING && this.state != State.FALLING && this.state != State.FLYING) {
			this.state = State.MOVING;
		}
	}

	public void jump() {
		if(!this.active || this.velocity.y != 0) {
			return;
		}

		this.state = State.JUMPING;
		this.velocity.y -= 10.0f;
	}

	public void spawn(int x, int y) {
		this.active = true;
		this.rect.setPosition(x * Block.size, y * Block.size);
		this.hitbox.setPosition(x * Block.size + 3, y * Block.size + 3);
		this.onSpawn();
	}

	public void hit(int damage) {
		this.modifyHp(Math.max(-1, -damage + defense / 2));
	}

	public void modifyHp(int amount) {
		if(!this.invulnerable) {
			this.hp = Math.min(Math.max(0, this.hp + amount), this.maxHp);

			if(this.hp == 0) {
				this.die();
			}
		}
	}

	public void modifyMaxHp(int amount) {
		this.maxHp = Math.max(0, this.maxHp + amount);
	}

	public void modifyDefense(int amount) {
		this.defense = Math.max(0, this.defense + amount);
	}

	public void die() {
		this.active = false;
	}

	public void onSpawn() {

	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getDefense() {
		return this.defense;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getHp() {
		return this.hp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getMaxHp() {
		return this.maxHp;
	}

	public String getName() {
		return this.name;
	}

	public int getGridX() {
		return (int) this.rect.x / Block.size;
	}

	public int getGridY() {
		return (int) this.rect.y / Block.size;
	}

	public float getX() {
		return this.rect.x;
	}

	public float getY() {
		return this.rect.y;
	}

	public float getWidth() {
		return this.rect.width;
	}

	public float getHeight() {
		return this.rect.height;
	}
}