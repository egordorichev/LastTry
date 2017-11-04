package org.egordorichev.lasttry.entity.entities.item;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.DescriptionComponent;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.component.TextureComponent;

/**
 * Represents an item in the game
 */
public class Item extends Entity {
	public Item(String id) {
		super(IdComponent.class, DescriptionComponent.class, TextureComponent.class);

		this.getComponent(IdComponent.class).id = id;
	}

	/**
	 * Sets field according to the json asset
	 *
	 * @param asset Json asset
	 */
	public void loadFields(JsonValue asset) {

	}
}