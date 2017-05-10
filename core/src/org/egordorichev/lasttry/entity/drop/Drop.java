package org.egordorichev.lasttry.entity.drop;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.modifier.Modifier;

public class Drop {
    private int minAmount;
    private int maxAmount;
    private Item item;
    private int chance;

    public Drop(Item item) {
        this(item, 1, 1, 1);
    }

    public Drop(Item item, int maxAmount) {
        this(item, 1, 1, maxAmount);
    }

    public Drop(Item item, int chance, int minAmount, int maxAmount) {
        this.item = item;
        this.chance = chance;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    public ItemHolder createHolder() {
        // TODO: drop multiple for many items
        int randBound = (this.maxAmount - this.minAmount) + 1;
        int count = LastTry.random.nextInt(randBound) + this.maxAmount;
        return new ItemHolder(this.item, count, Modifier.random(this.item));
    }

    public Item getItem() {
        return this.item;
    }

    public int getMaxAmount() {
        return this.maxAmount;
    }

    public int getMinAmount() {
        return this.minAmount;
    }

    public int getChance() {
        return this.chance;
    }

	@Override
	public Drop clone() {
		return new Drop(this.item, this.chance, this.minAmount, this.maxAmount);
	}
}
