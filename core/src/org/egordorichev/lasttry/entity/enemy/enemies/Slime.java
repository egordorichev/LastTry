package org.egordorichev.lasttry.entity.enemy.enemies;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.entity.drop.Drop;
import org.egordorichev.lasttry.entity.enemy.Enemy;
import org.egordorichev.lasttry.item.Items;

public class Slime extends Enemy {
    protected int nextJump;
    protected boolean canJump;

    public Slime(short id, int hp, int defense, int damage, Texture texture) {
        super(id, hp, defense, damage);

        this.drops.add(new Drop(Items.gel, Drop.Chance.ALWAYS, 1, 4));

        this.ai.setMax(90);
        this.texture = texture;

        // this.renderBounds = new Rectangle(0, 0, 32, 24);
        // this.hitbox = new Rectangle(this.renderBounds.x + 3, this.renderBounds.y + 3, this.renderBounds.width - 6,
        //        this.renderBounds.height - 3);
    }

    @Override
    public void updateAI() {
        // TODO
    }
}