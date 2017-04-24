package org.egordorichev.lasttry.world.components;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Callable;
import org.egordorichev.lasttry.util.Camera;
import org.egordorichev.lasttry.util.Util;
import org.egordorichev.lasttry.world.World;
import org.egordorichev.lasttry.world.chunk.Chunk;
import org.egordorichev.lasttry.world.chunk.ChunkIO;
import org.egordorichev.lasttry.world.chunk.EmptyChunk;

import java.util.ArrayList;

public class WorldChunksComponent extends WorldComponent {
	private Chunk[] chunks;
	private ArrayList<Chunk> loadedChunks = new ArrayList<>();
	private int size;

	public WorldChunksComponent(World world) {
		super(world);

		this.size = world.getWidth() * world.getHeight();
		this.chunks = new Chunk[this.size];

		Util.runInThread(new Callable() {
			@Override
			public void call() {
				updateLogic();
			}
		}, World.UPDATE_DELAY);
	}

	public void update() {

	}

	public void updateLogic() {
		for (int i = 0; i < this.loadedChunks.size(); i++) {
			this.loadedChunks.get(i).update();
		}
	}

	public void render() {
		int x = Camera.getXInBlocks();
		int y = Camera.getYInBlocks();
		int width = Gdx.graphics.getWidth() / Block.SIZE;
		int height = Gdx.graphics.getHeight() / Block.SIZE;

		Chunk tl = this.getFor(x, y + height);
		Chunk tr = this.getFor(x + width, y);
		Chunk bl = this.getFor(x, y);
		Chunk br = this.getFor(x, y + width);

		// TODO: check, if they are the same

		tl.render();
		/*tr.render();
		bl.render();
		br.render(); */
	}

	public void load(int x, int y) {
		int index = this.getIndex(x, y);

		if (!this.isInside(index)) {
			return;
		}

		this.chunks[index] = ChunkIO.load(x, y);
		this.loadedChunks.add(this.chunks[index]);
	}

	public void set(Chunk chunk, int x, int y) {
		int index = this.getIndex(x, y);

		if (!this.isInside(index)) {
			return;
		}

		this.chunks[index] = chunk;
	}

	public boolean isLoaded(int index) {
		if (!this.isInside(index)) {
			return false;
		}

		return this.chunks[index] != null;
	}

	public Chunk get(int x, int y) {
		int index = this.getIndex(x, y);

		if (!this.isInside(index)) {
			return null;
		}

		return this.chunks[index];
	}

	public Chunk getFor(int x, int y) {
		x /= Chunk.SIZE;
		y /= Chunk.SIZE;

		Chunk chunk = this.get(x, y);

		if (chunk == null) {
			return ChunkIO.load(x, y);
		}

		return chunk;
	}

	private boolean isInside(int index) {
		if (index >= this.size || index < 0) {
			return false;
		}

		return true;
	}

	private int getIndex(int x, int y) {
		return x + y * this.world.getWidth();
	}

	public void save() {
		for (int y = 0; y < LastTry.world.getHeight() / Chunk.SIZE; y++) {
			for (int x = 0; x < LastTry.world.getWidth() / Chunk.SIZE; x++) {
				ChunkIO.save(x, y);
			}
		}
	}
}