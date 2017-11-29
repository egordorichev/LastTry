package org.egordorichev.lasttry.entity.entities.world;

import org.egordorichev.lasttry.entity.component.Component;
import org.egordorichev.lasttry.entity.component.SizeComponent;
import org.egordorichev.lasttry.entity.entities.world.chunk.Chunk;

import java.util.ArrayList;

/**
 * Handles chunks for world
 */
public class WorldChunksComponent extends Component {
	/**
	 * Handles chunks
	 */
	public Chunk[] chunks;
	/**
	 * List of loaded chunks
	 */
	public ArrayList<Chunk> loaded;

	/**
	 * Inits the component
	 */
	public void init() {
		SizeComponent info = this.entity.getComponent(SizeComponent.class);

		this.chunks = new Chunk[(int) (info.width * info.height)];
		this.loaded = new ArrayList<>();
	}
}