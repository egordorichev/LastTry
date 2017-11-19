package org.egordorichev.lasttry.entity.entities.item.tile;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.entities.item.Item;
import org.egordorichev.lasttry.entity.entities.item.ItemUseComponent;
import org.egordorichev.lasttry.entity.entities.item.TileComponent;
import org.egordorichev.lasttry.graphics.Graphics;

/**
 * Block, but in the BG
 */
public class Wall extends Item {
	public Wall(String id) {
		super(id);

		this.addComponent(TileComponent.class);

		this.getComponent(ItemUseComponent.class).autoUse = true;
	}

	/**
	 * Renders the wall
	 *
	 * @param x Wall X
	 * @param y Wall Y
	 */
	public void render(short x, short y) {
		Graphics.batch.draw(this.getComponent(TileComponent.class).tiles[0][0], x * Block.SIZE, y * Block.SIZE);
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
			Assets.getTexture("walls/" + this.getComponent(IdComponent.class).id.replace(':', '_')
				+ "_tiles").split(Block.SIZE, Block.SIZE);
	}
}