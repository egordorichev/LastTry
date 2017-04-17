package org.egordorichev.lasttry.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.*;
import org.egordorichev.lasttry.entity.components.AiComponent;
import org.egordorichev.lasttry.entity.components.PhysicsComponent;
import org.egordorichev.lasttry.entity.drop.Drop;
import org.egordorichev.lasttry.entity.drop.DroppedItem;
import org.egordorichev.lasttry.entity.player.Player;
import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends CreatureWithAI {
    protected int id;
    protected Texture texture;
    protected List<Drop> drops = new ArrayList<>();

    public Enemy(short id, int maxHp, int defense, int damage) {
        super(new PhysicsComponent(), new EnemyGraphicsComponent());

        this.stats.set(maxHp, 0, damage, defense);
	    this.id = id;
    }

    public void updateAI() {

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

    public int getId() {
        return this.id;
    }
}