package org.egordorichev.lasttry.world.chunk;

import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.FileReader;
import org.egordorichev.lasttry.util.FileWriter;
import org.egordorichev.lasttry.util.Log;

import java.io.File;
import java.io.IOException;

public class ChunkIO {
	public static final int VERSION = 0;

	//Grid co ordinates
	public static Chunk load(int x, int y) {
		String fileName = getSaveName(x, y);
		File file = new File(fileName);

		if (!file.exists()) {
			Log.debug("Chunk does not exist, generating a new one...");
			return generate(x, y);
		}

		Log.debug("Loading chunk " + x + ":" + y + "...");

		try {
			FileReader stream = new FileReader(fileName);

			int version = stream.readInt32();

			if (version > VERSION) {
				Log.error("Trying to load unknown chunk.");
				LastTry.abort();
			} else if (version < VERSION) {
				Log.error("Trying to load old chunk.");
				LastTry.abort();
			}

			ChunkData data = new ChunkData();

			for (short cy = 0; cy < Chunk.SIZE; cy++) {
				for (short cx = 0; cx < Chunk.SIZE; cx++) {
					int index = cx + cy * Chunk.SIZE;

					data.blocks[index] = stream.readInt16();
					data.blocksHealth[index] = stream.readByte();
					data.walls[index] = stream.readInt16();
					data.wallsHealth[index] = stream.readByte();
				}
			}

			if (!stream.readBoolean()) {
				Log.error("Verification failed!");
				LastTry.abort();
			}

			stream.close();
			Log.debug("Done loading chunk " + x + ":" + y + "!");

			return new Chunk(data, new Vector2(x, y));
		} catch (Exception exception) {
			LastTry.handleException(exception);
			LastTry.abort();
			return null;
		}
	}

	public static Chunk generate(int x, int y) { // TODO
		new Thread(new Runnable() {
			@Override
			public void run() {
				Log.debug("Generating chunk " + x + ":" + y + "...");
				ChunkData data = new ChunkData();

				for (int y = 0; y < Chunk.SIZE; y++) {
					for (int x = 0; x < Chunk.SIZE; x++) {
						data.blocks[x + y * Chunk.SIZE] = ItemID.dirtBlock;
						data.blocksHealth[x + y * Chunk.SIZE] = Block.MAX_HP;
					}
				}

				LastTry.world.chunks.set(new Chunk(data, new Vector2(x, y)), x, y);
				Log.debug("Done generating chunk " + x + ":" + y + "!");
			}
		}).start();

		return new EmptyChunk(new Vector2(x, y));
	}

	public static void save(int x, int y) {
		String fileName = getSaveName(x, y);
		File file = new File(fileName);

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch(IOException exception) {
				Log.error("Could not create a save file for chunk " + x + ":" + y + ".");
				LastTry.abort();
			}
		}

		Chunk chunk = LastTry.world.chunks.get(x, y);
		Log.debug("Saving chunk " + x + ":" + y + "...");

		try {
			FileWriter stream = new FileWriter(fileName);
			ChunkData data = chunk.getData();

			stream.writeInt32(VERSION);

			for (short cy = 0; cy < Chunk.SIZE; cy++) {
				for (short cx = 0; cx < Chunk.SIZE; cx++) {
					int index = cx + cy * Chunk.SIZE;

					stream.writeInt16(data.blocks[index]);
					stream.writeByte(data.blocksHealth[index]);
					stream.writeInt16(data.walls[index]);
					stream.writeByte(data.wallsHealth[index]);
				}
			}

			stream.writeBoolean(true);
			stream.close();
		} catch (Exception exception) {
			LastTry.handleException(exception);
			LastTry.abort();
		}
	}

	private static String getSaveName(int x, int y) {
		return "worlds/" + LastTry.world.getName() + "/" + x + ":" + y + ".cnk";
	}
}