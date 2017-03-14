package org.egordorichev.lasttry.item;

import org.newdawn.slick.Image;

public class Item {
	/**
	 * Item lookup. Item ID used as the index.
	 */
	public static final Item[] ITEM_CACHE = new Item[32];


	/**
	 * Item identifier.
	 */
	protected int id;
	/**
	 * Item name.
	 */
	protected String name;
	/**
	 * Type of item. Possible values:
	 * <ul>
	 * <li>{@link org.egordorichev.lasttry.item.Item.Type#ITEM Item}</li>
	 * <li>{@link org.egordorichev.lasttry.item.Item.Type#BLOCK Block}</li>
	 * <li>{@link org.egordorichev.lasttry.item.Item.Type#WALL Wall}</li>
	 * <li>{@link org.egordorichev.lasttry.item.Item.Type#TOOL Tool}</li>
	 * <li>{@link org.egordorichev.lasttry.item.Item.Type#CONSUMABLE Consumable}
	 * </li>
	 * <li>{@link org.egordorichev.lasttry.item.Item.Type#ARMOR Armor}</li>
	 * <li>{@link org.egordorichev.lasttry.item.Item.Type#ACCESSORY Accessory}
	 * </li>
	 * </ul>
	 */
	protected Type type;

	/**
	 * Item texture.
	 */
	protected Image texture;

	/**
	 * Type category enum.
	 */
	public enum Type {
		ITEM, BLOCK, WALL, TOOL, CONSUMABLE, ARMOR, ACCESSORY
	}

	public Item(int id, String name, Type type) {
		if (ITEM_CACHE[id] != null) {
			System.out.println("Item with id " + id + " already exists.");
			throw new RuntimeException("Item with id " + id + " already exists.");
		}
		ITEM_CACHE[id] = this;
		this.id = id;
		this.name = name;
		this.type = type;
	}

	/**
	 * TODO
	 */
	public void use() {
		// TODO
	}

	/**
	 * Return the item's {@link #id ID}.
	 * 
	 * @return Item ID.
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Return the item's {@link #name}.
	 * 
	 * @return Item name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Return the item's {@link #type}.
	 * 
	 * @return Item type.
	 */
	public Type getType() {
		return this.type;
	}

	/**
	 * Retrieve an item instance from an item identifier.
	 * 
	 * @param id
	 *            Item ID.
	 * @return Item instance.
	 */
	public static Item fromId(int id) {
		if (id == 0) {
			return null;
		}

		return ITEM_CACHE[id];
	}
}