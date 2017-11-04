package org.egordorichev.lasttry.entity.entities.item;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.Component;

/**
 * Handles tile textures
 */
public class TileComponent extends Component {
	/**
	 * The tiles
	 */
	public TextureRegion[][] tiles;

	public TileComponent(Entity entity) {
		super(entity);
	}
}