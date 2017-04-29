package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.components.*;
import org.egordorichev.lasttry.world.WorldTime;
import org.egordorichev.lasttry.world.spawn.components.CircleAreaComponent;
import org.egordorichev.lasttry.world.spawn.components.GridComponent;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CreatureWithAI extends Creature {
	public AiComponent ai = new AiComponent(this);

	public CreatureWithAI(CreaturePhysicsComponent physics, CreatureGraphicsComponent graphics) {
		super(physics, graphics);
	}

	@Override
	public void update(int dt) {
		super.update(dt);
		this.ai.update(dt);
	}

	public void updateAI() {

	}

	public void tryToDespawn() {

		try {
			final WorldTime currentTime = LastTry.environment.time;

			final CircleAreaComponent playerActiveArea = GridComponent.retrieveActiveAreaCircle(currentTime);

			final boolean isEnemyInActiveArea = GridComponent.isCreatureInPlayerActiveArea(this, playerActiveArea);

			if(!isEnemyInActiveArea){
				LastTry.entityManager.markForRemoval(this);
			}

			LastTry.debug.print("Complete in try despawn");
		} catch (Exception e){
			LastTry.handleException(e);
		}
	}

}