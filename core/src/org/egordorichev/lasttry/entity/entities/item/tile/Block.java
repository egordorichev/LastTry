package org.egordorichev.lasttry.entity.entities.item.tile;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.component.SolidComponent;
import org.egordorichev.lasttry.entity.entities.item.Item;
import org.egordorichev.lasttry.entity.entities.item.TileComponent;

/**
 * The main part of the world
 */
public class Block extends Item {
	public static short SIZE = 16;

	public Block(String id) {
		super(id);

		this.addComponent(TileComponent.class, SolidComponent.class);
	}

	/**
	 * Renders the block
	 *
	 * @param x Block X
	 * @param y Block Y
	 */
	public void render(short x, short y) {
		Globals.batch.draw(this.getComponent(TileComponent.class).tiles[0][0], x * SIZE, y * SIZE);
	}

	/**
	 * Sets field according to the json asset
	 *
	 * @param asset Json asset
	 */
	@Override
	public void loadFields(JsonValue asset) {
		super.loadFields(asset);

		this.getComponent(TileComponent.class).tiles =
			Assets.getTexture("blocks/" + this.getComponent(IdComponent.class).id.replace(':', '_')
			+ "_tiles").split(SIZE, SIZE);

		this.getComponent(SolidComponent.class).solid =
			asset.getBoolean("solid", true);
	}
}