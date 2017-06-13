package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.Layers;
import org.egordorichev.lasttry.ai.AI;
import org.egordorichev.lasttry.entity.creature.CreatureGraphicsComponent;
import org.egordorichev.lasttry.entity.creature.CreaturePhysicsComponent;
import org.egordorichev.lasttry.player.Player;

public class Enemy extends CreatureWithAI {
	public Enemy(String id, AI ai) {
		super(id, new CreaturePhysicsComponent(), new CreatureGraphicsComponent(), ai);

		this.setZIndex(Layers.enemy);
	}

	/**
	 * Updates enemy
	 */
	@Override
	public void update(int dt) {
		super.update(dt);
	}

	/**
	 * Called on player collision
	 */
	protected void onPlayerCollision(Player player) {
		if (player.isActive() && !player.isInvulnrable()) {
			this.attack(player, this.stats.getDamage());
		}
	}
}