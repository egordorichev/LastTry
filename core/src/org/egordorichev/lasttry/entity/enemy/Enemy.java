package org.egordorichev.lasttry.entity.enemy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Animation;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.*;

public abstract class Enemy extends Entity {
	/** Defined enemies */
	public static HashMap<Short, Class<? extends Enemy>> ENEMY_CACHE = new HashMap<>();

	/** Max Ai for this enemy */
	protected static int maxAi;

	/** Current Ai counter */
	protected int currentAi;

	/** Enemy id */
	protected int id;

	/** Enemy drops */
	protected List<Drop> drops = new ArrayList<>();

	/** Animations */
	protected Animation[] animations;

	static {
		define(EnemyID.greenSlime, GreenSlime.class);
		define(EnemyID.blueSlime, BlueSlime.class);
		define(EnemyID.eyeOfCthulhu, EyeOfCthulhu.class);
	}

	public Enemy(short id, int maxHp, int defense, int damage) {
		super(maxHp, defense, damage);

		this.animations = new Animation[State.values().length];
		this.id = id;
	}

	public Enemy(short id) {
		this(id, 10, 0, 5);

		this.animations = new Animation[State.values().length];
		this.id = id;
	}

	@Override
	public void render() {
		// this.animations[this.state.getId()].getCurrentFrame().getFlippedCopy(this.direction == Direction.RIGHT, false)
		// 	.draw(this.renderBounds.x, this.renderBounds.y);
	}

	@Override
	public void update(int dt) {
		super.update(dt);

		if (LastTry.player.getHitbox().intersects(this.getHitbox())) {
			this.onPlayerCollision(LastTry.player);
		}

		// this.animations[this.state.getId()].update(dt);
		this.updateAI();
	}

	public void updateAI() {
		this.currentAi++;

		if (this.currentAi >= this.maxAi) {
			this.currentAi = 0;
		}
	}

	@Override
	public void onSpawn() {

	}

	@Override
	public void onDeath() {
		// On death, drop items in world.
		for (Drop drop : this.drops) {
			// Some items may not be dropped due to rarity

			if (drop.getChance().roll()){
				DroppedItem droppedItem = new DroppedItem(drop.createHolder());

				LastTry.entityManager.spawn(droppedItem, (int) this.getCenterX(),
					(int) this.getCenterY());
			}
		}
	}

	/**
	 * Called when the entity collides with player.
	 */
	protected void onPlayerCollision(Player player) {
		// TODO
	}

	public int getId() {
		return this.id;
	}

	public static void define(short id, Class<? extends Enemy> enemy) {
		// TODO: handle duplicates
		LastTry.log("Defined [" + id + "] as " + enemy.getSimpleName());
		ENEMY_CACHE.put(id, enemy);
	}

	public static Enemy create(short id) {
		try {
			Class<? extends Enemy> aClass = ENEMY_CACHE.get(id);

			if (aClass != null) {
				return aClass.newInstance();
			} else {
				LastTry.log.warn("Enemy with id " + id + " is not found");
				return null;
			}
		} catch(Exception exception) {
			LastTry.handleException(exception);
			return null;
		}
	}
}