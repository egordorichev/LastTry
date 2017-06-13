package org.egordorichev.lasttry.world.chunk;

import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.util.FileReader;
import org.egordorichev.lasttry.util.FileWriter;
import org.egordorichev.lasttry.util.Log;

import java.io.File;
import java.io.IOException;

public class ChunkIO {
	public static final byte VERSION = 4;

	public static Chunk load(int x, int y) {
		String fileName = getSaveName(x, y);
		File file = new File(fileName);

		if (!file.exists()) {
			return generate(x, y);
		}

		Log.debug("Loading chunk " + x + ":" + y + "...");

		try {
			FileReader stream = new FileReader(fileName);

			byte version = stream.readByte();

			if (version > VERSION) {
				Log.error("Trying to load unknown chunk.");
				LastTry.abort();
			} else if (version < VERSION) {
				Log.error("Trying to load old chunk.");
				LastTry.abort();
			}

			ChunkData data = new ChunkData();

			for (short cy = 0; cy < Chunk.SIZE; cy++) {
				byte rle = 0;

				for (short cx = 0; cx < Chunk.SIZE; cx++) {
					int index = cx + cy * Chunk.SIZE;

					if (rle > 0) {
						rle--;

						data.blocks[index] = data.blocks[index - 1];
						data.blocksHealth[index] = stream.readByte();
						data.walls[index] = data.walls[index - 1];
						data.wallsHealth[index] = stream.readByte();
					} else {
						data.blocks[index] = stream.readString();
						data.blocksHealth[index] = stream.readByte();
						data.walls[index] = stream.readString();
						data.wallsHealth[index] = stream.readByte();

						if (stream.readBoolean()) {
							rle = stream.readByte();
							System.out.println(rle);
						}
					}
				}
			}

			boolean unloadable = stream.readBoolean();

			if (!stream.readBoolean()) {
				Log.error("Verification failed!");
				LastTry.abort();
			}

			stream.close();
			Log.debug("Done loading chunk " + x + ":" + y + "!");

			Chunk chunk = new Chunk(data, new Vector2(x, y));
			chunk.setUnloadable(unloadable);

			return chunk;
		} catch (Exception exception) {
			LastTry.handleException(exception);
			LastTry.abort();
			return null;
		}
	}

	public static void save(int x, int y) {
		String fileName = getSaveName(x, y);
		File file = new File(fileName);

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException exception) {
				Log.error("Could not create a save file for chunk " + x + ":" + y + ".");
				LastTry.abort();
			}
		}

		Chunk chunk = Globals.getWorld().chunks.get(x, y);
		Log.debug("Saving chunk " + x + ":" + y + "...");

		try {
			FileWriter stream = new FileWriter(fileName);
			ChunkData data = chunk.getData();

			stream.writeByte(VERSION);

			for (short cy = 0; cy < Chunk.SIZE; cy++) {
				for (short cx = 0; cx < Chunk.SIZE; cx++) {
					int index = cx + cy * Chunk.SIZE;

					stream.writeString(data.blocks[index]);
					stream.writeByte(data.blocksHealth[index]);
					stream.writeString(data.walls[index]);
					stream.writeByte(data.wallsHealth[index]);

					stream.writeBoolean(false); // TODO: RLE!
				}
			}

			stream.writeBoolean(chunk.isUnloadable());
			stream.writeBoolean(true);
			stream.close();
		} catch (Exception exception) {
			LastTry.handleException(exception);
			LastTry.abort();
		}
	}

	public static Chunk generate(int x, int y) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Log.debug("Generating chunk " + x + ":" + y + "...");
				Globals.getWorld().chunks.set(new Chunk(new ChunkData(), new Vector2(x, y)), x, y);
				Log.debug("Done generating chunk " + x + ":" + y + "!");
			}
		}).start();
		return new EmptyChunk(new Vector2(x, y));
	}

	private static String getSaveName(int x, int y) {
		return "data/worlds/" + Globals.getWorld().getName() + "/" + x + "." + y + ".cnk";
	}
}