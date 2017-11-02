package org.egordorichev.lasttry.entity.item;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.entity.Entity;

/**
 * Represents an item in the game
 */
public class Item extends Entity {
	/**
	 * Item ID
	 */
	protected String id;
	/**
	 * Item description
	 */
	protected String description;
	/**
	 * Item icon
	 */
	protected TextureRegion icon;

	public Item(String id) {
		this.id = id;
	}

	/**
	 * Sets field according to the json asset
	 *
	 * @param asset Json asset
	 */
	public void loadFields(JsonValue asset) {

	}

	/**
	 * Sets item icon
	 *
	 * @param icon New icon
	 */
	public void setIcon(TextureRegion icon) {
		this.icon = icon;
	}

	/**
	 * @return Item ID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @return Item description
	 */
	public String getDescription() {
		return this.description;
	}
}