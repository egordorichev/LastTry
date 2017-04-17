package org.egordorichev.lasttry.entity.drop;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.PhysicBody;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.Items;
import org.egordorichev.lasttry.item.block.Block;

/**
 * PhysicBody representing an item dropped in the world.
 */
public class DroppedItem extends PhysicBody {
    /**
     * Items holder that this dropped entity represents.
     */
    private final ItemHolder holder;

    public DroppedItem(ItemHolder holder) {
        super();

        this.holder = holder;
        this.state = State.FALLING;
        this.texture = this.holder.getItem().getTexture();
        this.renderBounds.width = this.texture.getWidth();
        this.renderBounds.height = this.texture.getHeight();
        this.hitbox = this.renderBounds.copy();
        this.shouldUpdate = true;
        this.isSolid = true;
    }

	@Override
	public void render() {
		Texture texture = this.holder.getItem().getTexture();
    	float x = this.getX();
    	float y = LastTry.world.getHeight() * Block.SIZE - this.renderBounds.y - this.renderBounds.height;

		LastTry.batch.draw(texture, x, y);
	}

	@Override
    public void update(int dt) {
        super.update(dt);

        if (this.getHitbox().intersects(LastTry.player.getHitbox())) {
            if (this.holder.getItem() == Items.heart) {
                LastTry.player.modifyHp(20 * this.holder.getCount());
            } else if (this.holder.getItem() == Items.mana) {
                // TODO: player mana
                // LastTry.player.inventory.add(this.holder);
            } else {
                LastTry.player.inventory.add(this.holder);
            }

            LastTry.entityManager.markForRemoval(this);
        }
    }

    /**
     * Return the ItemHolder that this entity represents.
     *
     * @return
     */
    public ItemHolder getHolder() {
        return holder;
    }
}