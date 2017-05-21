package org.egordorichev.lasttry.entity.drop;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.Creature;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.Items;

public class DroppedItem extends Creature {
	private static final float ATTRACTION_RANGE = 100;
	private final ItemHolder holder;
	private TextureRegion texture;

	public DroppedItem(ItemHolder holder) {
		super();

		this.holder = holder;
		this.texture = this.holder.getItem().getTextureRegion();
		this.physics.setSize(this.texture.getRegionWidth(), this.texture.getRegionHeight());
	}

	@Override
	public void render() {
		Graphics.batch.draw(this.texture, this.physics.getX(), this.physics.getY());
	}

	@Override
	public void update(int dt) {
		this.physics.update(dt);
		this.updateAttraction(dt);
		this.checkPlayerAbsorbtion(dt);
	}

	/**
	 * Check if the item should be absorbed by the player.
	 * 
	 * @param dt
	 */
	private void checkPlayerAbsorbtion(int dt) {
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

	/**
	 * Update's velocity towards the player.
	 * 
	 * @param dt
	 */
	private void updateAttraction(int dt) {
		Vector2 p1 = Globals.player.physics.getPosition().cpy();
		Vector2 p2 = physics.getPosition().cpy();
		float dist = p1.dst(p2);
		if (dist < ATTRACTION_RANGE) {
			float attraction = 1000f;
			float distPow = (float) Math.pow(dist, 3);
			this.physics.getVelocity().add(p2.sub(p1).scl(-attraction, -attraction).scl(1f / distPow));
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