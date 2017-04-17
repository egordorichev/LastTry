package org.egordorichev.lasttry.entity.drop;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.components.PhysicsComponent;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.Items;
import org.egordorichev.lasttry.item.block.Block;

public class DroppedItem extends Entity {
    private final ItemHolder holder;
    private Texture texture;

    public DroppedItem(ItemHolder holder) {
        super(new PhysicsComponent(), null);

        this.holder = holder;
        this.texture = this.holder.getItem().getTexture();
        this.physics.setSize(this.texture.getWidth(), this.texture.getHeight());
        this.active = true;
    }

	@Override
	public void render() {
		LastTry.batch.draw(texture, this.physics.getX(), this.physics.getY());
	}

	@Override
    public void update(int dt) {
        super.update(dt);

        if (this.physics.getHitbox().intersects(LastTry.player.physics.getHitbox())) {
            if (this.holder.getItem() == Items.heart) {
                LastTry.player.stats.modifyHP(20 * this.holder.getCount());
            } else if (this.holder.getItem() == Items.mana) {
                LastTry.player.stats.modifyMana(20 * this.holder.getCount());
            } else {
                LastTry.player.inventory.add(this.holder);
            }

            LastTry.entityManager.markForRemoval(this);
        }
    }

    public ItemHolder getHolder() {
        return holder;
    }
}