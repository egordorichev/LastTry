package org.egordorichev.lasttry.entity.components;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.Creature;
import org.egordorichev.lasttry.entity.drop.Drop;
import org.egordorichev.lasttry.entity.drop.DroppedItem;

import java.util.ArrayList;
import java.util.List;

public class CreatureDropsComponent extends CreatureComponent {
	
	public CreatureDropsComponent(Creature creature) {
		super(creature);
	}

	private List<Drop> drops = new ArrayList<>();
	
	public void add(Drop drop){
		this.drops.add(drop);
	}

	public void drop() {
		for (Drop drop : this.drops) {
			if (LastTry.random.nextInt(drop.getChance()) == 0) {
				DroppedItem droppedItem = new DroppedItem(drop.createHolder());

				Globals.entityManager.spawn(droppedItem, (int) this.creature.physics.getCenterX(), (int) this.creature.physics.getCenterY());
			}
		}
	}
}
