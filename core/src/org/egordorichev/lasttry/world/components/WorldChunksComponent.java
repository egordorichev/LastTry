package org.egordorichev.lasttry.world.components;

import org.egordorichev.lasttry.util.Callable;
import org.egordorichev.lasttry.util.Util;
import org.egordorichev.lasttry.world.World;
import org.egordorichev.lasttry.world.chunk.Chunk;
import org.egordorichev.lasttry.world.chunk.ChunkIO;

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
		return this.get(x / Chunk.SIZE, y / Chunk.SIZE);
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
}