package org.egordorichev.lasttry.entity.components;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.graphics.Animation;

import java.util.Arrays;

public class CreatureGraphicsComponent extends GraphicsComponent {
	protected Animation[] animations;

	public CreatureGraphicsComponent() {
		int size = CreatureStateComponent.State.values().length;

		this.animations = new Animation[size];

		for (int i = 0; i < size; i++) {
			this.animations[i] = new Animation(true);
		}
	}

	public void load(JsonValue root) {
		// TODO
	}

	@Override
	public void render() {
		this.animations[this.creature.state.get().getID()].render(this.creature.physics.getX(), this.creature.physics.getY());
	}

	public void update() {
		this.animations[this.creature.state.get().getID()].update();
	}
}