package org.egordorichev.lasttry.entity.components;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.graphics.Animation;

public class CreatureGraphicsComponent extends GraphicsComponent {
	public Animation[] animations;
	protected Texture texture;

	public CreatureGraphicsComponent() {
		int size = CreatureStateComponent.State.values().length;

		this.animations = new Animation[size];

		for (int i = 0; i < size; i++) {
			this.animations[i] = new Animation(true);
		}
	}

	@Override
	public void render() {
		this.animations[this.creature.state.get().getID()].render(this.creature.physics.getX(), this.creature.physics.getY(),
			this.creature.physics.getSize().x, this.creature.physics.getSize().y, !this.creature.physics.isFlipped(), false);
	}

	public void update() {
		this.animations[this.creature.state.get().getID()].update();
	}
}