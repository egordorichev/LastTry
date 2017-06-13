package org.egordorichev.lasttry.entity.creature;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.drop.Drop;
import org.egordorichev.lasttry.entity.drop.DroppedItem;

import java.util.ArrayList;
import java.util.List;

public class CreatureDropsComponent extends CreatureComponent {
	/**
	 * Drops list
	 */
	private List<Drop> drops = new ArrayList<>();

	public CreatureDropsComponent(Creature creature) {
		super(creature);
	}

	/**
	 * Adds a drop
	 *
	 * @param drop Drop, to add
	 */
	public void add(Drop drop) {
		this.drops.add(drop);
	}

	/**
	 * Spawns drops on creature position
	 */
	public void drop() {
		for (Drop drop : this.drops) {
			if (LastTry.random.nextInt(drop.getChance()) == 0) {
				DroppedItem droppedItem = new DroppedItem(drop.createHolder());

				Globals.entityManager.spawn(droppedItem, (int) this.creature.physics.getCenterX(), (int) this.creature.physics.getCenterY());
			}
		}
	}
}
