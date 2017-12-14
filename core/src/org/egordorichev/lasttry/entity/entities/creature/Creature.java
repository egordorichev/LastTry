package org.egordorichev.lasttry.entity.entities.creature;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.*;
import org.egordorichev.lasttry.entity.component.physics.AccelerationComponent;
import org.egordorichev.lasttry.entity.component.physics.CollisionComponent;
import org.egordorichev.lasttry.entity.component.physics.VelocityComponent;
import org.egordorichev.lasttry.graphics.animation.Animation;

import java.util.Objects;

/**
 * Represents all mobs, players, NPCs and etc
 */
public class Creature extends Entity {
	public Creature(Class<? extends Component> ... types) {
		super(HealthComponent.class, PositionComponent.class, AccelerationComponent.class,
			VelocityComponent.class, TextureComponent.class, AnimationComponent.class, SizeComponent.class,
			CollisionComponent.class, IdComponent.class, StateComponent.class, AiComponent.class);

		this.addComponent(types);
		this.zIndex = 1;
	}

	/**
	 * Renders the entity
	 */
	@Override
	public void render() {
		AnimationComponent animation = this.getComponent(AnimationComponent.class);

		if (animation.current != null) {
			PositionComponent position = this.getComponent(PositionComponent.class);
			animation.current.render(position.x, position.y);
		}
	}

	/**
	 * Updates creature state
	 *
	 * @param state New state
	 */
	public void become(String state) {
		StateComponent stateComponent = this.getComponent(StateComponent.class);

		if (Objects.equals(state, stateComponent.state)) {
			return;
		}

		stateComponent.state = state;

		AnimationComponent animationComponent = this.getComponent(AnimationComponent.class);
		Animation animation = animationComponent.animations.get(state);

		if (animation == null) {
			animationComponent.current = animationComponent.animations.get("idle");
		} else {
			animationComponent.current = animation;
		}

		animation.reset();
	}
}