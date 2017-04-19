package org.egordorichev.lasttry.entity.enemy.enemies;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.entity.drop.Drop;
import org.egordorichev.lasttry.entity.enemy.Enemy;
import org.egordorichev.lasttry.item.Items;

public class Slime extends Enemy {
	private Texture texture;

    public Slime(short id, String name, int hp, int defense, int damage, Texture texture) {
        super(id, name, hp, defense, damage);

        this.drops.add(new Drop(Items.gel, Drop.Chance.ALWAYS, 1, 4));

        this.ai.setMax(90);
        this.texture = texture;
    }

    @Override
    public void updateAI() {

    }
}