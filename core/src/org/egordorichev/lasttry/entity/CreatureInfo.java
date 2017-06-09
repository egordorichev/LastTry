package org.egordorichev.lasttry.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonValue;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.ai.AI;
import org.egordorichev.lasttry.entity.components.CreatureStateComponent;
import org.egordorichev.lasttry.entity.drop.Drop;
import org.egordorichev.lasttry.graphics.Animation;
import org.egordorichev.lasttry.graphics.AnimationFrame;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.util.Rectangle;

import java.util.ArrayList;

public class CreatureInfo {
	public AI ai;
	public int[] hp = new int[3];
	public int[] defense = new int[3];
	public int[] damage = new int[3];
	public float[] kbResist = new float[3];
	public float speed = 1;
	public byte spawnWeight = 1;
	public byte type = 1;
	public String name;
	public TextureRegion image;
	public ArrayList<Drop> drops = new ArrayList<>();
	public Rectangle hitbox = new Rectangle(0, 0, 16, 16);
	public Rectangle renderBounds = new Rectangle(0, 0, 16, 16);
	public Animation[] animations = new Animation[CreatureStateComponent.State.values().length];
	private boolean copied = false;

	public CreatureInfo(JsonValue root, String name) throws Exception {
		this.name = name;

		for (int i = 0; i < this.animations.length; i++) {
			this.animations[i] = new Animation(true);
		}

		try {
			if (root.has("copy")) {
				String from = root.getString("copy");

				if (from.equals(name)) {
					throw new Exception("You can't copy creature from it self");
				}

				this.copy(from);
			}

			this.load(root);
		} catch (Exception exception) {
			throw exception;
		}
	}

	private void copy(String from) throws Exception {
		this.copied = true;

		CreatureInfo info = Creatures.CREATURE_CACHE.get(from);

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

		this.ai = AI.fromID(ai);

		if (this.ai == null) {
			throw new Exception("AI with id " + ai + " is not found");
		}

		if (root.has("spawnWeight")) {
			this.spawnWeight = root.get("spawnWeight").asByte();
		}

		if (root.has("type")) {
			this.type = root.get("type").asByte();
		}

		if (root.has("kbResist")) {
			this.kbResist = root.get("kbResist").asFloatArray();
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
				Item item = Item.fromID(drop.getString("id"));

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

		this.image = Assets.getTexture(this.name);

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

	private void loadFrame(JsonValue frame, Animation to) {
		JsonValue rect = frame.get("rect");

		to.addFrame(new AnimationFrame(new TextureRegion(this.image, rect.get(0).asInt(),
			rect.get(1).asInt(), rect.get(2).asInt(), rect.get(3).asInt()), frame.getInt("time", 10)));
	}

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

	public Creature create() {
		Enemy creature = new Enemy(this.name, this.ai);

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

		//creature.setSpawnWeight(spawnWeight);

		return creature;

		/*
		switch (this.type) {
			case 0: // Creature

			break;
			case 1: // Enemy

			break;
			// todo: npc's
		}

		return null;*/
	}
}