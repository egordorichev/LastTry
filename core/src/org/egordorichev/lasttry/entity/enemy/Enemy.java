package org.egordorichev.lasttry.entity.enemy;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.*;
import org.egordorichev.lasttry.entity.ai.AI;
import org.egordorichev.lasttry.entity.components.CreatureGraphicsComponent;
import org.egordorichev.lasttry.entity.drop.Drop;
import org.egordorichev.lasttry.entity.drop.DroppedItem;
import org.egordorichev.lasttry.entity.player.Player;
import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends CreatureWithAI {
    protected String name;
	protected int spawnWeight = 1;
    protected List<Drop> drops = new ArrayList<>();

    public Enemy(AI ai, String name, int maxHp, int defense, int damage) {
        super(new EnemyPhysicsComponent(), new CreatureGraphicsComponent(), ai);
        this.name = name;
    }

    @Override
    public void update(int dt) {
        super.update(dt);
    }

    public boolean canSpawn(){
        return false;
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

    protected void onPlayerCollision(Player player) {
        // TODO: hit the player
    }

    public String getName() {
        return this.name;
    }

    public int getSpawnWeight() {
    	return this.spawnWeight;
    }
}