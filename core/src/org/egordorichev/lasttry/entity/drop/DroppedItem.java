package org.egordorichev.lasttry.entity.drop;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.Creature;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.inventory.ItemHolder;

public class DroppedItem extends Creature {
    private static final float ATTRACTION_RANGE = 60;
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
        if (LastTry.debug.isEnabled()) {
            Assets.f18.draw(Graphics.batch, String.valueOf(this.holder.getCount()), this.physics.getX(),
                    this.physics.getY());
        }
    }

    @Override
    public void update(int dt) {
        this.physics.update(dt);
        this.graphics.update(dt);
        this.updateAttraction(dt);
        this.checkPlayerAbsorbtion(dt);
        this.packRelated(dt);
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
                Globals.player.getInventory().add(this.holder);
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
        boolean inRange = dist < ATTRACTION_RANGE;
        if (inRange) {
            float attraction = 100f;
            float distPow = (float) Math.pow(dist, 2);
            this.physics.getVelocity().add(p2.sub(p1).scl(-attraction, -attraction).scl(1f / distPow));
        }
        this.physics.setSolid(!inRange);
    }

    /**
     * Packs items of the same type into a single item.
     * 
     * @param dt
     */
    private void packRelated(int dt) {
        List<Entity> entities = Globals.entityManager.getEntities();
        entities.stream().filter(e -> !e.equals(this))
                .filter(e -> physics.getHitbox().intersects(e.physics.getHitbox()))
                .filter(e -> e instanceof DroppedItem).map(e -> ((DroppedItem) e))
                .filter(i -> holder.getItem().getID() == i.holder.getItem().getID()).forEach(i -> consume(i));
    }

    /**
     * Merges this and the other item together.
     * 
     * @param other
     */
    private void consume(DroppedItem other) {
        // other.die() causes BOTH items to die
        // this.die() causes only THIS item to die
        //
        // what the fuck??
        int sum = this.holder.getCount() + other.holder.getCount();
        if (sum <= other.holder.getItem().getMaxInStack()) {
            other.holder.setCount(sum);
            this.die();
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