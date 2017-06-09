package org.egordorichev.lasttry.item.block;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.block.helpers.BlockHelper;
import org.egordorichev.lasttry.world.chunk.Chunk;
import org.egordorichev.lasttry.world.components.WorldLightingComponent;

public class ChunkLoader extends Block {
	public ChunkLoader(String id) {
		super(id, false);
	}

	@Override
	public void die(int x, int y) {
		super.die(x, y);
		Chunk chunk = Globals.getWorld().chunks.getFor(x, y);
		chunk.setUnloadable(false);
	}

	@Override
	public void place(int x, int y) {
		super.place(x, y);
		Chunk chunk = Globals.getWorld().chunks.getFor(x, y);
		chunk.setUnloadable(true);
	}

	@Override
	public void renderBlock(int x, int y, byte binary) {
		byte hp = Globals.getWorld().blocks.getHP(x, y);

		float light  = 1f;

		// Update light leven
		if (!LastTry.noLight){
			light = (0f + Globals.getWorld().blocks.getLight(x, y)) / ( WorldLightingComponent.MAX_LIGHT );
		}

		Graphics.batch.setColor(light, light, light, 1f);
		Graphics.batch.draw(this.tiles[0][0], x * SIZE, y * SIZE);

		hp = BlockHelper.plain.getHP(hp);

		if (this.renderCracks() && hp < Block.MAX_HP) {
			Graphics.batch.draw(Graphics.tileCracks[Block.MAX_HP - hp], x * Block.SIZE, y * Block.SIZE);
		}

		Graphics.batch.setColor(1f,1f,1f,1f);
	}
}
