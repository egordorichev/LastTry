package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.modifier.Modifier;

/**
 * Item drop handler.
 */
public class Drop {
    private int minAmount;
    private int maxAmount;
    private Item item;
    private Chance chance;

    /**
     * Create a drop of a single item with 100% drop chance.
     *
     * @param item Item to drop.
     */
    public Drop(Item item) {
        this(item, Chance.ALWAYS, 1, 1);
    }

    /**
     * Create a drop of a single item with 100% drop chance. Then number of
     * items dropped will be between 1 and the given maximum.
     *
     * @param item      Item to drop.
     * @param maxAmount Maximum number of items that can drop.
     */
    public Drop(Item item, int maxAmount) {
        this(item, Chance.ALWAYS, 1, maxAmount);
    }

    /**
     * Create a drop of a single item with 100% drop chance. Then number of
     * items dropped will be between the given min and max.
     *
     * @param item      Item to drop.
     * @param maxAmount Maximum number of items that can drop.
     */
    public Drop(Item item, int minAmount, int maxAmount) {
        this(item, Chance.ALWAYS, minAmount, maxAmount);
    }

    /**
     * Create a drop of a given item <i>(Number of which is between the two
     * values given)</i>, with the given chance.
     *
     * @param item      Item to drop.
     * @param chance    Chance item will drop.
     * @param minAmount Minimum number of items that can drop.
     * @param maxAmount Maximum number of items that can drop.
     */
    public Drop(Item item, Chance chance, int minAmount, int maxAmount) {
        this.item = item;
        this.chance = chance;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    /**
     * Creates an ItemHolder with a random number <i>(Between a
     * {@link #getMinAmount() minimum} and {@link #getMaxAmount() maximum}
     * amount)</i> of items based on the {@link #getChance() chance} of the item
     * drop.
     *
     * @return ItemHolder containing.
     */
    public ItemHolder createHolder() {
        // TODO: drop multiple for many items
        int randBound = (this.maxAmount - this.minAmount) + 1;
        int count = LastTry.random.nextInt(randBound) + this.maxAmount;
        return new ItemHolder(this.item, count, Modifier.random(this.item));
    }

    /**
     * Returns the Item that will be dropped.
     *
     * @return Item dropped.
     */
    public Item getItem() {
        return this.item;
    }

    /**
     * Returns the maximum amount of items that can be dropped when generating
     * an {@link #createHolder() ItemHolder}.
     *
     * @return Maximum amount of items that can be dropped.
     */
    public int getMaxAmount() {
        return this.maxAmount;
    }

    /**
     * Returns the minimum amount of items that can be dropped when generating
     * an {@link #createHolder() ItemHolder}.
     *
     * @return Minimum amount of items that can be dropped.
     */
    public int getMinAmount() {
        return this.minAmount;
    }

    /**
     * Returns the Chance type for the item drop.
     *
     * @return Chance of item drop.
     */
    public Chance getChance() {
        return this.chance;
    }

    /**
     * Enumeration for drop chance rarity.
     */
    public enum Chance {
        ALWAYS(1), // 100% 1/1
        ALMOST_ALWAYS(2), // 50%
        VERY_COMMON(5), // 20%
        COMMON(20), // 5% 1/20
        UNCOMMON(50), // 2% 1/50
        VERY_UNCOMMON(100), // 1% 1/100
        RARE(200), // 0.5% 1/200
        VERY_RARE(286), // 0.35% 1/286
        EXTREMELY_RARE(500), // 0.2% 1/500
        LEGENDARY(1000); // 0.1% 1/1000

        /**
         * The chance factor.
         */
        private final int rate;

        private Chance(int rate) {
            this.rate = rate;
        }

        /**
         * Check if a random number between 0 and the rate (exclusive) is 0.
         *
         * @return Random chance based on rarity.
         */
        public boolean roll() {
            return LastTry.random.nextInt(rate) == 0;
        }
    }
}
