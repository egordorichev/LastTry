package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.Layers;
import org.egordorichev.lasttry.entity.ai.AI;
import org.egordorichev.lasttry.entity.components.CreatureGraphicsComponent;
import org.egordorichev.lasttry.entity.components.CreaturePhysicsComponent;
import org.egordorichev.lasttry.entity.player.Player;

public class Enemy extends CreatureWithAI {
	protected String name;
	protected Creature target;

	public Enemy(AI ai, String name) {
		super(new CreaturePhysicsComponent(), new CreatureGraphicsComponent(), ai);

		this.name = name;
		this.target = Globals.getPlayer();
		this.setZIndex(Layers.enemy);
	}

	@Override
	public void update(int dt) {
		super.update(dt);
	}

	public boolean canSpawn() {
		return this.ai.ai.canSpawn();
	}

	public void setTarget(Creature target) {
		this.target = target;
	}

	protected void onPlayerCollision(Player player) {
		// TODO: hit the player
	}

	public Creature getTarget() {
		return target;
	}

	public String getName() {
		return name;
	}
}