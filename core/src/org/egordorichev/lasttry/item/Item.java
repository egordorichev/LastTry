package org.egordorichev.lasttry.item;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.inventory.InventoryOwner;
import org.egordorichev.lasttry.item.items.Tool;
import org.egordorichev.lasttry.item.items.ToolPower;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	 * remaining. <br>
	 * TODO: 50 IS A TEMPORARY VALUE. This value needs to be assigned somewhere
	 * properly.
	 */
	protected int useDelayMax = 50;

	public Item(String id) {
		if (Items.ITEM_CACHE.containsKey(id)) {
			throw new RuntimeException("Item with id " + id + " already exists.");
		}

		Items.ITEM_CACHE.put(id, this);

		this.id = id;
		this.name = Language.text.get(this.id);
		this.texture = Assets.getTexture(this.id);
	}

	/**
	 * Loads item from given json root
	 *
	 * @param root
	 *            Item root
	 * @return New item
	 * @throws Exception
	 *             Exception containing a error message
	 */
	public static Item load(JsonValue root) throws Exception {
		String className = root.getString("type", "org.egordorichev.lasttry.item.Item");

		try {
			System.out.println(root.name);
			Item instance = createInstance(root, className);
			// TODO: Do this properly.
			// this is done for quick testing
			if (instance instanceof Tool) {
				Tool tool = ((Tool) instance);
				if (root.has("speed")) {
					tool.useDelayMax = root.getInt("speed");
				}
				if (root.has("damage")) {
					tool.baseDamage = root.getInt("damage");
				}
				if (root.has("power")) {
					int[] pow = root.get("power").asIntArray();
					tool.power = new ToolPower(pow[0], pow[1], pow[2]);
				}
			}
			return instance;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new Exception("Failed to parse " + root.name());
		}
	}

	/**
	 * Creates Item class and initialization it
	 *
	 * @param root
	 *            Item root
	 * @param className
	 *            Class name to create (like org.egordorichev.lasttry.item.Item)
	 * @return New Item instance
	 * @throws Exception
	 *             Exception containing error message
	 */
	public static Item createInstance(JsonValue root, String className) throws Exception {
		try {
			Class<?> itemClass = Class.forName(className);

			Class<?>[] types = { String.class };
			Constructor<?> constructor = itemClass.getConstructor(types);

			Object[] parameters = { root.name() };

			Item item = (Item) constructor.newInstance(parameters);
			item.loadFields(root);

			return item;
		} catch (ClassNotFoundException exception) {
			throw new Exception("Class " + className + " is not found");
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	/**
	 * Loads fields from root
	 *
	 * @param root
	 */
	protected void loadFields(JsonValue root) {

	}

	/**
	 * @return Can the item be used (can depend on night / day and other)
	 */
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

	/**
	 * Callback, called when the item is used
	 *
	 * @return If item needs to be removed from inventory
	 */
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

	/**
	 * Callback, called when the item stopes to being used
	 */
	protected void onUseEnd() {

	}

	/**
	 * Renders item animation
	 */
	public void renderAnimation() {

	}

	/**
	 * Determines if the current texture can be connected to the texture of the
	 * item indicated by the given item ID.
	 * 
	 * @param itemID
	 * @return
	 */
	protected boolean canConnect(String itemID) {
		if (itemID == null) {
			return false;
		}
		Item i1 = Item.fromID(itemID);
		if (i1 == null) {
			return false;
		}
		return canConnect(i1);
	}

	/**
	 * Checks if the current block has a texture that connects to the other
	 * item.
	 * 
	 * @param other
	 * @return
	 */
	protected boolean canConnect(Item other) {
		if (other == null) {
			return false;
		}
		return true;// this.id.equals(other.id);
	}

	/**
	 * @return Item ID
	 */
	public String getID() {
		return this.id;
	}

	/**
	 * @return Item rarity
	 */
	public Rarity getRarity() {
		return this.rarity;
	}

	/**
	 * @return Item name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return Item texture
	 */
	public TextureRegion getTextureRegion() {
		return this.texture;
	}

	/**
	 * @return If the item can be used
	 */
	public boolean canBeUsed() {
		return true;
	}

	/**
	 * @return Item will keep being used, while holding the mouse button
	 */
	public boolean isAutoUse() {
		return false;
	}

	/**
	 * @return Item is ready for another use
	 */
	public boolean isReady() {
		return this.useDelay == 0;
	}

	/**
	 * Returns Item with given id or null, if it is not found
	 * 
	 * @param id
	 *            Item id
	 * @return Item with given id or null, if it is not found
	 */
	public static Item fromID(String id) {
		if (id == null) {
			return null;
		}

		return Items.ITEM_CACHE.get(id);
	}

	/**
	 * @return Max items in one stack
	 */
	public int getMaxInStack() {
		return 999;
	}
}
