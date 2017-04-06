package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.input.InputManager;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.items.*;

public class UiItemSlot extends UiComponent {
    private static Texture inventorySlot5 = Assets.getTexture(Textures.inventorySlot5);
	private boolean active;
    private Texture texture;
    public ItemHolder itemHolder;
    private Type type;
    private TextureRegion back;

    public enum Type {
        ANY,
        ACCESSORY,
        VANITY_ACCESSORY,
        ARMOR,
        COIN,
        AMMO,
        TRASH,
        VANITY,
        DYE
    }

    public UiItemSlot(Rectangle rectangle, Type type, Origin origin, TextureRegion back) {
        super(rectangle, origin);

        this.itemHolder = null;
        this.type = type;
        this.back = back;

        switch (this.type) {
            case ANY:
            case AMMO:
            case COIN:
            case TRASH:
                this.texture = Assets.getTexture(Textures.inventorySlot1);
            break;
            case ACCESSORY:
            case ARMOR:
                this.texture = Assets.getTexture(Textures.inventorySlot2);
            break;
            case VANITY:
            case VANITY_ACCESSORY:
                this.texture = Assets.getTexture(Textures.inventorySlot3);
            break;
            case DYE:
                this.texture = Assets.getTexture(Textures.inventorySlot4);
            break;
        }

        this.active = false;
    }

    public UiItemSlot(Rectangle rectangle, Type type) {
        this(rectangle, type, Origin.TOP_LEFT, null);
    }

    public boolean setItemHolder(ItemHolder holder) {
        if (!this.canHold(holder)) {
            return false;
        }

        this.itemHolder = holder;
        return true;
    }

    @Override
    public void render() {
        if (this.hidden) {
            return;
        }

        super.render();

        int x = this.getX();
        int y = this.getY();
        int width = this.getWidth();
        int height = this.getHeight();

        LastTry.batch.setColor(1, 1, 1, 0.8f);

        if (this.active) {
            LastTry.batch.draw(inventorySlot5, x, y, width, height);
        } else {
            LastTry.batch.draw(this.texture, x, y, width, height);
        }

        if (this.itemHolder != null && this.itemHolder.getItem() != null) {
            LastTry.batch.setColor(1, 1, 1, 1);
            this.itemHolder.renderAt(x, y, width, height);
        } else if (this.back != null) {
            LastTry.batch.setColor(1, 1, 1, 0.7f);
            LastTry.batch.draw(this.back, x + (width - this.back.getRegionWidth()) / 2, y + (height
                    - this.back.getRegionHeight()) / 2);
            LastTry.batch.setColor(1, 1, 1, 1);
        }
    }

    public void setItemCount(int count) {
        this.itemHolder.setCount(count);
    }

    public ItemHolder getItemHolder() {
        return this.itemHolder;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Item getItem() {
        if (this.itemHolder == null) {
            return null;
        }

        return this.itemHolder.getItem();
    }

    public short getItemID() {
	    Item item = this.getItem();

	    if (item == null) {
	    	return 0;
	    }

	    return item.getId();
    }

    public int getItemCount() {
	    if (this.itemHolder == null) {
		    return 0;
	    }

	    return this.itemHolder.getCount();
    }

    public boolean canHold(ItemHolder holder) {
        switch (this.type) {
            case ANY:
            case TRASH:
            default:
            break;
            case ACCESSORY:
            case VANITY_ACCESSORY:
                if (!(holder.getItem() instanceof Accessory)) {
                    return false;
                }
            break;
            case ARMOR:
            case VANITY:
                if (!(holder.getItem() instanceof Armor)) {
                    return false;
                }
            break;
            case COIN:
                if (!(holder.getItem() instanceof Coin)) {
                    return false;
                }
            break;
            case AMMO:
                if (!(holder.getItem() instanceof Ammo)) {
                    return false;
                }
            break;
            case DYE:
                if (!(holder.getItem() instanceof Dye)) {
                    return false;
                }
            break;
        }

        return true;
    }

    @Override
    protected void onStateChange() {
        if (this.state == State.MOUSE_DOWN) {
            if (LastTry.player.inventory.isOpen()) {
                if (InputManager.isMouseButtonPressed(Input.Buttons.LEFT)) {
                    if (LastTry.player.inventory.currentItem != null && this.itemHolder != null
                            && this.itemHolder.getItem() == LastTry.player.inventory.currentItem.getItem()) {

                        if (this.canHold(LastTry.player.inventory.currentItem)) {
                            int count = LastTry.player.inventory.currentItem.getCount() + this.itemHolder.getCount();
                            int max = this.itemHolder.getItem().getMaxInStack();

                            if (count <= max) {
                                this.itemHolder.setCount(count);
                                LastTry.player.inventory.currentItem = null;
                            } else {
                                ItemHolder tmp = this.itemHolder;
                                this.itemHolder = LastTry.player.inventory.currentItem;
                                LastTry.player.inventory.currentItem = tmp;
                            }
                        }
                    } else {
	                    if (this.itemHolder == null || this.canHold(LastTry.player.inventory.currentItem)) {
                            ItemHolder tmp = LastTry.player.inventory.currentItem;
                            LastTry.player.inventory.currentItem = this.itemHolder;
                            this.itemHolder = tmp;
                        }
                    }
                } else if (InputManager.isMouseButtonPressed(Input.Buttons.RIGHT)) {
                    // TODO
                }
            }
        }
    }
}
