package org.egordorichev.lasttry.entity.component;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.entity.Entity;

/**
 * Handles textures
 */
public class TextureComponent extends Component {
	/**
	 * The texture
	 */
	public TextureRegion texture;

	public TextureComponent(Entity entity) {
		super(entity);
	}
}