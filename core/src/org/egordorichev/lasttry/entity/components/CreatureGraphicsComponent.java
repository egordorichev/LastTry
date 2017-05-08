package org.egordorichev.lasttry.entity.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.graphics.Animation;
import org.egordorichev.lasttry.graphics.AnimationFrame;
import org.egordorichev.lasttry.graphics.Assets;

import java.util.Arrays;

public class CreatureGraphicsComponent extends GraphicsComponent {
	protected Animation[] animations;
	protected Texture texture;

	public CreatureGraphicsComponent() {
		int size = CreatureStateComponent.State.values().length;

		this.animations = new Animation[size];

		for (int i = 0; i < size; i++) {
			this.animations[i] = new Animation(true);
		}
	}

	public void load(JsonValue root, String texture) {
		this.texture = Assets.getTexture(texture);

		this.loadAnimation(root, "idle");
		this.loadAnimation(root, "moving");
		this.loadAnimation(root, "jumping");
		this.loadAnimation(root, "falling");
		this.loadAnimation(root, "dead");
		this.loadAnimation(root, "acting");
	}

	private void loadAnimation(JsonValue root, String type) {
		JsonValue animation = root.get(type);
		Animation to = this.animations[this.toID(type)];

		if (animation.has("copy")) {
			to = this.animations[this.toID(animation.get("copy").asString())];
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

		to.addFrame(new AnimationFrame(new TextureRegion(this.texture, rect.get(0).asInt(),
			rect.get(1).asInt(), rect.get(2).asInt(), rect.get(3).asInt()), frame.getInt("time", 10)));
	}

	private int toID(String animation) {
		if (animation == "moving") {
			return CreatureStateComponent.State.MOVING.getID();
		} else if (animation == "jumping") {
			return CreatureStateComponent.State.JUMPING.getID();
		} else if (animation == "falling") {
			return CreatureStateComponent.State.FALLING.getID();
		} else if (animation == "dead") {
			return CreatureStateComponent.State.DEAD.getID();
		} else if (animation == "acting") {
			return CreatureStateComponent.State.ACTING.getID();
		}

		return CreatureStateComponent.State.IDLE.getID();
	}

	@Override
	public void render() {
		this.animations[this.creature.state.get().getID()].render(this.creature.physics.getX(), this.creature.physics.getY());
	}

	public void update() {
		this.animations[this.creature.state.get().getID()].update();
	}
}