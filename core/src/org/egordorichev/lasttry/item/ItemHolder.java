package org.egordorichev.lasttry.item;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.modifier.Modifier;

public class ItemHolder {
    private int count;
    private Item item;
    private Modifier modifier;

    public ItemHolder(Item item, int count, Modifier modifier) {
        this.item = item;
        this.count = count;
        this.modifier = modifier;
    }

    public ItemHolder(Item item, int count) {
        this(item, count, null);
    }

    public void renderAt(int x, int y) {
        if (this.item != null) {
            Texture texture = this.item.getTexture();

            int th = texture.getHeight();

            Graphics.batch.draw(texture, x, y);

            if (this.count > 1) {
	            Assets.f18.draw(Graphics.batch, String.format("%d", this.count), x - 8, y + th - 8);
            }
        }
    }

    public void renderAt(int x, int y, int width, int height) {
        if (this.item != null) {
            Texture texture = this.item.getTexture();

            int tw = texture.getWidth();
            int th = texture.getHeight();
            int iy = y + (height - th) / 2;

            Graphics.batch.draw(texture, x + (width - tw) / 2, iy);

            if (this.count > 1) {
	            Assets.f18.draw(Graphics.batch, String.format("%d", this.count), x + tw / 2, iy + th / 2);
            }
        }
    }

	public void setItem(Item item) {
		this.item = item;
	}

	public void setCount(int count) {
    	if (this.item == null) {
    		this.count = 0;
	    }

    	if (count > this.item.getMaxInStack()) {
    		this.count = this.item.getMaxInStack();
	    }

		this.count = count;
	}

	public void setModifier(Modifier modifier) {
    	this.modifier = modifier;
	}

	public int getCount() {
        return this.count;
    }

    public Item getItem() {
        return this.item;
    }
    public Modifier getModifier() {
        return this.modifier;
    }
}