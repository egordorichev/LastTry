package org.egordorichev.lasttry.entity.drop;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.Creature;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.Items;

public class DroppedItem extends Creature {
    private final ItemHolder holder;
    private Texture texture;

    public DroppedItem(ItemHolder holder) {
        super();

        this.holder = holder;
        this.texture = this.holder.getItem().getTexture();
        this.physics.setSize(this.texture.getWidth(), this.texture.getHeight());
    }

	@Override
	public void render() {
		Graphics.batch.draw(this.texture, this.physics.getX(), this.physics.getY());
	}

	@Override
    public void update(int dt) {
		this.physics.update(dt);

        if (this.physics.getHitbox().intersects(Globals.player.physics.getHitbox())) {
        	System.out.println("touch");

            if (this.holder.getItem() == Items.heart) {
                Globals.player.stats.modifyHP(20 * this.holder.getCount());
            } else if (this.holder.getItem() == Items.mana) {
                Globals.player.stats.modifyMana(20 * this.holder.getCount());
            } else {
                Globals.player.inventory.add(this.holder);
            }

            Globals.entityManager.markForRemoval(this);
        }
    }

    public ItemHolder getHolder() {
        return holder;
    }

	@Override
	public void spawn(int x, int y) {
		super.spawn(x, y);
		this.physics.setPosition(x, y);
	}
}