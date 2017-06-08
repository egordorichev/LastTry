package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.Layers;
import org.egordorichev.lasttry.entity.ai.AI;
import org.egordorichev.lasttry.entity.components.CreatureGraphicsComponent;
import org.egordorichev.lasttry.entity.components.CreaturePhysicsComponent;
import org.egordorichev.lasttry.entity.player.Player;
import org.egordorichev.lasttry.language.Language;

public class Enemy extends CreatureWithAI {
	/**
	 * Enemy id (for ex. lt:green_slime)
	 */
	protected String id;
	/**
	 * Enemy name (for ex. Green Slime)
	 */
	protected String name;

	public Enemy(AI ai, String id) {
		super(new CreaturePhysicsComponent(), new CreatureGraphicsComponent(), ai);

		this.id = id;
		this.name = Language.text.get(this.id);

		this.setZIndex(Layers.enemy);
	}

	/** Updates enemy */
	@Override
	public void update(int dt) {
		super.update(dt);
	}

	/** Called on player collision */
	protected void onPlayerCollision(Player player) {
		// TODO: hit the player
	}

	/** Returns enemy name */
	public String getName() {
		return name;
	}
}