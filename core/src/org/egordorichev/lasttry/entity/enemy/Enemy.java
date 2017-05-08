package org.egordorichev.lasttry.entity.enemy;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.Creature;
import org.egordorichev.lasttry.entity.CreatureWithAI;
import org.egordorichev.lasttry.entity.ai.AI;
import org.egordorichev.lasttry.entity.components.CreatureGraphicsComponent;
import org.egordorichev.lasttry.entity.drop.Drop;
import org.egordorichev.lasttry.entity.drop.DroppedItem;
import org.egordorichev.lasttry.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends CreatureWithAI {
    protected String name;
	protected int spawnWeight = 1;
    protected List<Drop> drops = new ArrayList<>();
	protected Creature target;

    public Enemy(AI ai, String name) {
        super(new EnemyPhysicsComponent(), new CreatureGraphicsComponent(), ai);

        this.name = name;
        this.target = Globals.player;
    }

    @Override
    public void update(int dt) {
        super.update(dt);
    }

    public boolean canSpawn(){
        return true;
    }

    @Override
    public void onDeath() {
        for (Drop drop : this.drops) {
            if (drop.getChance().roll()) {
                DroppedItem droppedItem = new DroppedItem(drop.createHolder());

                Globals.entityManager.spawn(droppedItem, (int) this.physics.getCenterX(),
		            (int) this.physics.getCenterY());
            }
        }
    }

	public void setTarget(Creature target) {
		this.target = target;
	}

	protected void onPlayerCollision(Player player) {
        // TODO: hit the player
    }

	public Creature getTarget() {
		return this.target;
	}

	public String getName() {
        return this.name;
    }

    public int getSpawnWeight() {
    	return this.spawnWeight;
    }
}