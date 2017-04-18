package org.egordorichev.lasttry.entity.enemy.enemies;

import org.egordorichev.lasttry.entity.EnemyID;
import org.egordorichev.lasttry.entity.enemy.Enemy;

public class Zombie extends Enemy {
    public Zombie() {
        super(EnemyID.zombie, 100, 2, 25);
    }

    @Override
    public void updateAI() {
	    // TODO
    }
}