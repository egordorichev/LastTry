package org.egordorichev.lasttry.entity.enemy;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.*;
import org.egordorichev.lasttry.entity.components.CreatureGraphicsComponent;
import org.egordorichev.lasttry.entity.components.EnemyPhysicsComponent;
import org.egordorichev.lasttry.entity.drop.Drop;
import org.egordorichev.lasttry.entity.drop.DroppedItem;
import org.egordorichev.lasttry.entity.player.Player;
import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends CreatureWithAI {
    protected int id;
	protected int spawnWeight = 1;
	protected String name;
    protected List<Drop> drops = new ArrayList<>();

    public Enemy(short id, String name, int maxHp, int defense, int damage) {
        super(new EnemyPhysicsComponent(), new CreatureGraphicsComponent());
        this. id = id; this.name = name;
    }

    @Override
    public void update(int dt) {
        super.update(dt);
    }

    public void updateAI() {

    }

    public boolean canSpawn(){
        return false;
    }

    @Override
    public void onDeath() {
        for (Drop drop : this.drops) {
            if (drop.getChance().roll()) {
                DroppedItem droppedItem = new DroppedItem(drop.createHolder());

                LastTry.entityManager.spawn(droppedItem, (int) this.physics.getCenterX(),
		            (int) this.physics.getCenterY());
            }
        }
    }

    protected void onPlayerCollision(Player player) {
        // TODO: hit the player
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getSpawnWeight() {
    	return this.spawnWeight;
    }
}