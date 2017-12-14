package org.egordorichev.lasttry.entity.entities.creature.ai;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.physics.VelocityComponent;
import org.egordorichev.lasttry.entity.entities.creature.Creature;

public class PlayerAi extends Ai {
	public PlayerAi() {
		this.registerAll("idle", "walking", "flying");
	}

	public void idle(Creature self, int t) {
		VelocityComponent velocity = self.getComponent(VelocityComponent.class);

		if (Gdx.input.isKeyPressed(Assets.keys.get("jump"))) {
			velocity.y = -10; // Doesn't work :X
			self.become("flying");
		}
	}

	public void walking(Creature self, int t) {

	}

	public void flying(Creature self, int t) {

	}
}