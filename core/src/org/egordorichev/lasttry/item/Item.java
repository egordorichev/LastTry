package org.egordorichev.lasttry.item;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Item {
    /**
     * Item identifier.
     */
    protected short id;
    /**
     * Item display name.
     */
    protected String name;
    /**
     * Item texture.
     */
    protected TextureRegion texture;
    /**
     * Item's rarity. More rare items can sell for higher prices and generally
     * have better stats than their more common counter-parts.
     */
    protected Rarity rarity;
    /**
     * Delay until the item can be used again.
     */
    protected float useDelay;
    /**
     * Maximum delay time. See {@link #useDelay} for the current delay
     * remaining.
     */
    protected int useDelayMax;

    public Item(short id, String name, Rarity rarity, TextureRegion texture) {
        if (Items.ITEM_CACHE[id] != null) {
            throw new RuntimeException("Item with id " + id + " already exists.");
        }

        Items.ITEM_CACHE[id] = this;

        this.texture = texture;
        this.id = id;
        this.name = name;
    }

    public Item(short id, String name, TextureRegion texture) {
        this(id, name, Rarity.WHITE, texture);
    }

    public boolean use() {
        return false;
    }

    /**
     * Updates the item.
     * 
     * @param owner
     *            Entity holding the item.
     * @param dt
     */
    public void update(InventoryOwner owner, int dt) {
        if (this.isReady()) {
            return;
        }

        if (this.useDelay > 0) {
            this.useDelay--;
        } else {
            this.useDelay = 0;
        }
        this.onUpdate(owner);

        if (this.isReady()) {
            this.onUseEnd();
        }
    }

    protected boolean onUse() {
        return false;
    }

    /**
     * Called when the item has been updated.
     * 
     * @param owner
     *            Entity holding the item.
     */
    protected void onUpdate(InventoryOwner owner) {

    }

    protected boolean onUseEnd() {
        return false;
    }

    public void renderAnimation() {

    }

    public short getID() {
        return this.id;
    }

    public Rarity getRarity() {
        return this.rarity;
    }

    public String getName() {
        return this.name;
    }

    public TextureRegion getTextureRegion() {
        return this.texture;
    }

    public boolean canBeUsed() {
        return true;
    }

    public boolean isAutoUse() {
        return false;
    }

    public boolean isReady() {
        return this.useDelay == 0;
    }

    public static Item fromID(int id) {
        if (id <= 0 || id >= ItemID.count) {
            return null;
        }

        return Items.ITEM_CACHE[id];
    }

    public int getMaxInStack() {
        return 999;
    }
}
