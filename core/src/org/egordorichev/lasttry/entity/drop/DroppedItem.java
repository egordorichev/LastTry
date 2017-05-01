package org.egordorichev.lasttry.entity.drop;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.components.PhysicsComponent;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.Items;

public class DroppedItem extends Entity {
    private final ItemHolder holder;
    private Texture texture;
    private PhysicsComponent physics;

    public DroppedItem(ItemHolder holder) {
        super();

        this.holder = holder;
        this.texture = this.holder.getItem().getTexture();
        this.physics = new PhysicsComponent();
        this.physics.setSize(this.texture.getWidth(), this.texture.getHeight());
        this.active = true;
    }

	@Override
	public void render() {
		Graphics.batch.draw(texture, this.physics.getX(), this.physics.getY());
	}

	@Override
    public void update(int dt) {
        super.update(dt);

        if (this.physics.getHitbox().intersects(Globals.player.physics.getHitbox())) {
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
}