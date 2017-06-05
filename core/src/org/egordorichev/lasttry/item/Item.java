package org.egordorichev.lasttry.item;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.Items;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.inventory.InventoryOwner;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.block.plant.Grass;
import org.egordorichev.lasttry.item.items.Coin;
import org.egordorichev.lasttry.item.items.Pickaxe;
import org.egordorichev.lasttry.language.Language;

import java.lang.reflect.Constructor;

public class Item {
	/**
	 * Item identifier.
	 */
	protected String id;
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

	public Item(String id) {
		if (Items.ITEM_CACHE.containsKey(id)) {
			throw new RuntimeException("Item with id " + id + " already exists.");
		}

		Items.ITEM_CACHE.put(id, this);

		this.id = id;
		this.name = Language.text.get(this.id);
		this.texture = Assets.getTexture(this.id);
	}

	public static Item load(JsonValue root) throws Exception {
		String className = root.getString("type", "org.egordorichev.lasttry.item.Item");

		try {
			return createInstance(root, className);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new Exception("Failed to parse " + root.name());
		}
	}

	public static Item createInstance(JsonValue root, String className) throws Exception {
		try {
			Class itemClass = Class.forName(className);

			Class[] types = { String.class };
			Constructor constructor = itemClass.getConstructor(types);

			Object[] parameters = { root.name() };

			Item item = (Item) constructor.newInstance(parameters);
			item.loadFields(root);

			return item;
		} catch (ClassNotFoundException exception) {
			throw new Exception("Class " + className + " is not found");
		}
	}

	protected void loadFields(JsonValue root) {

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
	public void update(InventoryOwner<?> owner, int dt) {
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
	protected void onUpdate(InventoryOwner<?> owner) {

	}

	protected boolean onUseEnd() {
		return false;
	}

	public void renderAnimation() {

	}

	public String getID() {
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

	public static Item fromID(String id) {
		if (id.isEmpty()) {
			return null;
		}

		return Items.ITEM_CACHE.get(id);
	}

	public int getMaxInStack() {
		return 999;
	}
}
