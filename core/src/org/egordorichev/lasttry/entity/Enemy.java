package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.Layers;
import org.egordorichev.lasttry.entity.ai.AI;
import org.egordorichev.lasttry.player.Player;

public class Enemy extends CreatureWithAI {
	public Enemy(String id, AI ai) {
		super(id, ai);
		this.setZIndex(Layers.enemy);
	}

	/** Updates enemy */
	@Override
	public void update(int dt) {
		super.update(dt);
	}

	/** Called on player collision */
	protected void onPlayerCollision(Player player) {
		if (player.isActive() && !player.isInvulnrable()) {
			this.attack(player, this.stats.getDamage());
		}
	}
}