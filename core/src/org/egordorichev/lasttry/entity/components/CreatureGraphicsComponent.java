package org.egordorichev.lasttry.entity.components;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import org.egordorichev.lasttry.entity.Creature;
import org.egordorichev.lasttry.graphics.Animation;
import org.egordorichev.lasttry.graphics.Graphics;

public class CreatureGraphicsComponent<T extends Creature> extends GraphicsComponent<T> {
	protected TextureRegion texture;

	public CreatureGraphicsComponent(T entity) {
		super(entity);
		int size = CreatureStateComponent.State.values().length;

		this.animations = new Animation[size];

		for (int i = 0; i < size; i++) {
			this.animations[i] = new Animation(true);
		}
	}

	@Override
	public void render() {
		float light = getAlpha();
		Graphics.batch.setColor(light, light, light, 1f);
		this.animations[this.entity.state.get().getID()].render(this.entity.physics.getX(),
				this.entity.physics.getY(), this.entity.physics.getSize().x, this.entity.physics.getSize().y,
				!this.entity.physics.isFlipped(), false);
		Graphics.batch.setColor(1, 1, 1, 1);
	}

	@Override
	public void update(int dt) {
		this.animations[this.entity.state.get().getID()].update();
	}
}