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
		super(IdComponent.class, DescriptionComponent.class, TextureComponent.class, ItemUseComponent.class);

		this.getComponent(IdComponent.class).id = id;
	}

	/**
	 * Sets field according to the json asset
	 *
	 * @param asset Json asset
	 */
	public void loadFields(JsonValue asset) {

	}

	/**
	 * Updates item use time
	 *
	 * @param delta Time, since the last frame
	 */
	public void update(float delta) {
		ItemUseComponent data = this.getComponent(ItemUseComponent.class);

		if (data.currentTime > 0.0f) {
			data.currentTime -= delta;

			if (data.currentTime < 0.0f) {
				data.currentTime = 0.0f;
			}
		}
	}

	/**
	 * Uses the item
	 *
	 * @return Item should be removed from inventory
	 */
	public boolean use() {
		ItemUseComponent data = this.getComponent(ItemUseComponent.class);

		if (data.currentTime == 0.0f) {
			data.currentTime = data.useTime;
			return this.onUse();
		}

		return false;
	}

	/**
	 * The actual item use function
	 *
	 * @return Item should be removed from inventory
	 */
	protected boolean onUse() {
		return false;
	}
}