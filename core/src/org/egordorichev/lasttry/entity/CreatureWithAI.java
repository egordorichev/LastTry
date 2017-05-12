package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.ai.AI;
import org.egordorichev.lasttry.entity.components.AiComponent;
import org.egordorichev.lasttry.entity.components.CreatureGraphicsComponent;
import org.egordorichev.lasttry.entity.components.CreaturePhysicsComponent;
import org.egordorichev.lasttry.world.WorldTime;
import org.egordorichev.lasttry.world.spawn.components.CircleAreaComponent;
import org.egordorichev.lasttry.world.spawn.components.GridComponent;

public class CreatureWithAI extends Creature {
	public AiComponent ai;

	public CreatureWithAI(CreaturePhysicsComponent physics, CreatureGraphicsComponent graphics, AI ai) {
		super(physics, graphics);

		this.ai = new AiComponent(this, ai);
		this.ai.ai.init(this);
	}

	@Override
	public void update(int dt) {
		super.update(dt);
		this.ai.update(dt);
	}

	public void tryToDespawn() {

		try {
			final WorldTime currentTime = Globals.environment.time;

			final CircleAreaComponent playerActiveArea = GridComponent.retrieveActiveAreaCircle(currentTime);

			final boolean isEnemyInActiveArea = GridComponent.isCreatureInPlayerActiveArea(this, playerActiveArea);

			if(!isEnemyInActiveArea){
				Globals.entityManager.markForRemoval(this);
			}

		} catch (Exception e){
			LastTry.handleException(e);
		}
	}

}