package org.egordorichev.lasttry.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.ai.AI;
import org.egordorichev.lasttry.entity.ai.AIManager;
import org.egordorichev.lasttry.entity.components.CreatureStateComponent;
import org.egordorichev.lasttry.entity.drop.Drop;
import org.egordorichev.lasttry.graphics.Animation;
import org.egordorichev.lasttry.graphics.AnimationFrame;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.injection.CoreRegistry;
import org.egordorichev.lasttry.injection.InjectionHelper;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemManager;
import org.egordorichev.lasttry.util.Rectangle;
import java.util.ArrayList;

/**
 * Because every creature is an instance, this json handler
 * is very different from other ones.
 * It loads all data into CreatureInfo fields, and creates
 * Creature from it
 */

public class CreatureInfo {
	/**
	 * Creature ai
	 */
	public AI ai;
	/**
	 * Info about hp: normal, expert, hardmode
	 */
	public int[] hp = new int[3];
	/**
	 * Info about defense: normal, expert, hardmode
	 */
	public int[] defense = new int[3];
	/**
	 * Info about damage: normal, expert, hardmode
	 */
	public int[] damage = new int[3];
	/**
	 * Info about resistance to knockback
	 */
	public float[] kbResist = new float[3];
	/**
	 * Creature speed
	 */
	public float speed = 1;
	/**
	 * How much spawn will creature "eat"
	 */
	public byte spawnWeight = 1;
	/**
	 * Creature type: enemy, creature, npc, etc.
	 * TODO: replace with string?
	 */
	public byte type = 1;
	/**
	 * Creature id
	 */
	public String id;
	/**
	 * Creature texture
	 */
	public TextureRegion image;
	/**
	 * Items, that creature drops on death
	 */
	public ArrayList<Drop> drops = new ArrayList<>();
	/**
	 * Creature hitbox (can be different from renderBounds)
	 */
	public Rectangle hitbox = new Rectangle(0, 0, 16, 16);
	/**
	 * Creature render bounds
	 */
	public Rectangle renderBounds = new Rectangle(0, 0, 16, 16);
	/**
	 * Animations of creature
	 */
	public Animation[] animations = new Animation[CreatureStateComponent.State.values().length];
	/**
	 * If the creature was copied from another one
	 */
	private boolean copied = false;

	public CreatureInfo(JsonValue root, String id) throws Exception {
		this.id = id;

		for (int i = 0; i < this.animations.length; i++) {
			this.animations[i] = new Animation(true);
		}

		try {
			if (root.has("copy")) {
				String from = root.getString("copy");

				if (from.equals(id)) {
					throw new Exception("You can't copy creature from it self");
				}

				this.copy(from);
			}

			this.load(root);
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * Copies all info from another creature
	 *
	 * @param from Creature, to copy from
	 * @throws Exception Exception, containing parse error
	 */
	private void copy(String from) throws Exception {
		this.copied = true;


		CreatureInfo info = InjectionHelper.getInstance(CreatureManager.class).getCreatureInfo(from);

		if (info == null) {
			throw new Exception("Creature for copy " + from + " is not found");
		}

		this.hp = info.hp.clone();
		this.defense = info.defense.clone();
		this.damage = info.damage.clone();
		this.speed = info.speed;
		this.spawnWeight = info.spawnWeight;
		this.type = info.type;

		for (Drop drop : info.drops) {
			this.drops.add(drop.clone());
		}

		this.ai = info.ai;
		this.kbResist = info.kbResist.clone();
		this.hitbox = info.hitbox.copy();
		this.renderBounds = info.renderBounds.copy();

		for (int i = 0; i < info.animations.length; i++) {
			this.animations[i] = info.animations[i].copy();
		}
	}

	/**
	 * Loads creature info from json root
	 *
	 * @param root Json root
	 * @throws Exception Exception, containing parse error
	 */
	private void load(JsonValue root) throws Exception {
		if (root.has("hp")) {
			this.hp = root.get("hp").asIntArray();
		}

		if (root.has("defense")) {
			this.defense = root.get("defense").asIntArray();
		}

		if (root.has("damage")) {
			this.damage = root.get("damage").asIntArray();
		}

		if (root.has("speed")) {
			this.speed = root.get("speed").asFloat();
		}

		short ai = 0;

		if (root.has("ai")) {
			ai = root.get("ai").asShort();
		}

		if (root.has("ai")) {
			ai = root.get("ai").asShort();
		}


		this.ai = InjectionHelper.getInstance(AIManager.class).get(ai);

		if (this.ai == null) {
			throw new Exception("AI with id " + ai + " is not found");
		}

		if (root.has("spawnWeight")) {
			this.spawnWeight = root.get("spawnWeight").asByte();
		}

		if (root.has("type")) {
			this.type = root.get("type").asByte();
		}

		if (root.has("kb_resist")) {
			this.kbResist = root.get("kb_resist").asFloatArray();
		}

		if (root.has("hitbox")) {
			JsonValue hitbox = root.get("hitbox");

			this.hitbox.x = hitbox.getInt(0);
			this.hitbox.y = hitbox.getInt(1);
			this.hitbox.width = hitbox.getInt(2);
			this.hitbox.height = hitbox.getInt(3);
		}

		if (root.has("size")) {
			JsonValue hitbox = root.get("size");

			this.renderBounds.width = hitbox.getInt(0);
			this.renderBounds.height = hitbox.getInt(1);
		} else if (!this.copied) {
			this.renderBounds = this.hitbox.copy();
		}

		if (root.has("drop")) {
			JsonValue drops = root.get("drop");

			for (JsonValue drop : drops) {

				Item item = InjectionHelper.getInstance(ItemManager.class).getItem(drop.getString("id"));

				if (item == null) {
					throw new Exception("Item with id " + drop.getString("id") + " is not found");
				}

				short chance = drop.getShort("chance", (short) 1);

				short min = 1;
				short max = 1;

				try {
					max = drop.get("count").getShort(1);
					min = drop.get("count").getShort(0);
				} catch (Exception exception) {
					exception.printStackTrace();
				}

				this.drops.add(new Drop(item, chance, min, max));
			}
		}

		this.image = Assets.getTexture(this.id.replace(':', '_'));

		if (root.has("animation")) {
			JsonValue animation = root.get("animation");

			this.loadAnimation(animation, "idle");
			this.loadAnimation(animation, "moving");
			this.loadAnimation(animation, "jumping");
			this.loadAnimation(animation, "falling");
			this.loadAnimation(animation, "dead");
			this.loadAnimation(animation, "acting");
		}
	}

	/**
	 * Loads animation with given type
	 * @param root Json root
	 * @param type Animation type
	 */
	private void loadAnimation(JsonValue root, String type) {
		JsonValue animation = root.get(type);
		Animation to = this.animations[this.toID(type)];

		if (animation.has("copy")) {
			to.copyFrom(this.animations[this.toID(animation.get("copy").asString())]);
		} else {
			if (animation.has("looping")) {
				to.setLooped(animation.get("looping").asBoolean());
			}

			if (animation.has("stopped")) {
				to.setStopped(animation.get("stopped").asBoolean());
			}

			if (!animation.has("frames")) {
				return;
			}

			for (JsonValue frame : animation.get("frames")) {
				this.loadFrame(frame, to);
			}
		}
	}

	/**
	 * Loads animation from
	 *
	 * @param frame Json root for frame
	 * @param to Animation, that will receive that frame
	 */
	private void loadFrame(JsonValue frame, Animation to) {
		JsonValue rect = frame.get("rect");

		to.addFrame(new AnimationFrame(new TextureRegion(this.image, rect.get(0).asInt(),
			rect.get(1).asInt(), rect.get(2).asInt(), rect.get(3).asInt()), frame.getInt("time", 10)));
	}

	/**
	 * Converts string to animation number
	 *
	 * @param animation String, containing animation type
	 * @return Animation number
	 */
	private int toID(String animation) {
		switch (animation) {
			case "moving": return CreatureStateComponent.State.MOVING.getID();
			case "jumping": return CreatureStateComponent.State.JUMPING.getID();
			case "falling": return CreatureStateComponent.State.FALLING.getID();
			case "dead": return CreatureStateComponent.State.DEAD.getID();
			case "acting": return CreatureStateComponent.State.ACTING.getID();
			default: return CreatureStateComponent.State.IDLE.getID();
		}
	}

	/**
	 * @return New creature
	 */
	public Creature create() {
		Enemy creature = new Enemy(this.id, this.ai);

		int hp = this.hp[0];
		int defense = this.damage[0];
		int damage = this.damage[0];

		if (Globals.getWorld().flags.isHardmode()) {
			hp = this.hp[2];
			defense = this.defense[2];
			damage = this.damage[2];
		} else if (Globals.getWorld().flags.isExpertMode()) {
			hp = this.hp[1];
			defense = this.defense[1];
			damage = this.damage[1];
		}

		creature.stats.set(hp, 0, defense, damage);
		creature.physics.setHitbox(this.hitbox.copy());
		creature.physics.setSize((int) this.renderBounds.width, (int) this.renderBounds.height);
		creature.physics.setSpeed(this.speed);

		for (int i = 0; i < this.animations.length; i++) {
			creature.graphics.animations[i] = this.animations[i].copy();
			creature.graphics.animations[i].setTexture(this.image);
		}

		for (int i = 0; i < this.drops.size(); i++) {
			creature.drops.drop();
			creature.drops.add(this.drops.get(i).clone());
		}

		creature.setSpawnWeight(spawnWeight);

		return creature;

		/*
		switch (this.type) {
			case 0: // Creature

			break;
			case 1: // Enemy

			break;
			// todo: npc's
		}

		return null;*/ // FIXME
	}
}