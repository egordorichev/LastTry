package org.egordorichev.lasttry.item;

public class Item {
	public static Item[] items = new Item[32];
	public enum Type {
		ITEM,
		BLOCK,
		WALL,
		TOOL,
		CONSUMABLE,
		ARMOR,
		ACCESSORY
	}

	protected int id;
	protected String name;
	protected Type type;

	public Item(int id, String name, Type type) {
		if(items[id] != null) {
			System.out.println("Item with id " + id + " already exists.");
			throw new RuntimeException("Item with id " + id + " already exists.");
		}

		items[id] = this;

		this.id = id;
		this.name = name;
		this.type = type;
	}

	public void use() {
		// TODO
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public Type getType() {
		return this.type;
	}

	public static Item fromId(int id) {
		if(id == 0) {
			return null;
		}

		return items[id];
	}
}