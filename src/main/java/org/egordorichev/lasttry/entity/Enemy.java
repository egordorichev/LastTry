package org.egordorichev.lasttry.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.enemy.BlueSlime;
import org.egordorichev.lasttry.entity.enemy.EyeOfCthulhu;
import org.egordorichev.lasttry.entity.enemy.GreenSlime;
import org.egordorichev.lasttry.util.Direction;
import org.newdawn.slick.Animation;

public abstract class Enemy extends Entity {
	public static HashMap<Integer, Class<? extends Enemy>> ENEMY_CACHE = new HashMap<>();

	protected int currentAi;
	protected int maxAi;
	protected int id;
	protected List<Drop> drops = new ArrayList<>();
	protected Animation[] animations;

	static {
		define(EnemyID.greenSlime, GreenSlime.class);
		define(EnemyID.blueSlime, BlueSlime.class);
		define(EnemyID.eyeOfCthulhu, EyeOfCthulhu.class);
	}

	public Enemy(int id, int maxHp, int defense, int damage) {
		super(maxHp, defense, damage);

		this.animations = new Animation[State.values().length];
		this.id = id;
	}

	public Enemy(int id) {
		super();

		this.animations = new Animation[State.values().length];
		this.id = id;
	}

	@Override
	public void render() {
		this.animations[this.state.getId()].getCurrentFrame().getFlippedCopy(this.direction == Direction.RIGHT, false)
			.draw(this.renderBounds.x, this.renderBounds.y);
	}

	@Override
	public void update(int dt) {
		super.update(dt);

		if (LastTry.player.getHitbox().intersects(this.getHitbox())) {
			this.onPlayerCollision(LastTry.player);
		}

		this.animations[this.state.getId()].update(dt);
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
				LastTry.world.spawnDrop(drop, this.getCenterX(), this.getCenterY());
			}
		}
	}

	/**
	 * Called when the entity collides with player.
	 */
	protected void onPlayerCollision(Player player) {
		System.out.println("onPlayerCollision");
	}

	public int getId() {
		return this.id;
	}

	public static void define(int id, Class<? extends Enemy> enemy) { 
		// TODO: handle duplicates
		LastTry.log("Defined [" + id + "] as " + enemy.getSimpleName());
		ENEMY_CACHE.put(id, enemy);
	}

	public static Enemy create(int id) {
		try {
			Class<? extends Enemy> aClass = ENEMY_CACHE.get(id);

			if(aClass != null) {
				return aClass.newInstance();
			} else {
				return null;
			}
		} catch(Exception exception) {
			LastTry.handleException(exception);
			return null;
		}
	}
}
