package org.egordorichev.lasttry.world.chunk;

import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.util.FileReader;
import org.egordorichev.lasttry.util.FileWriter;
import org.egordorichev.lasttry.world.WorldProvider;

import java.io.File;
import java.io.IOException;

public class ChunkProvider {
	public static int CURRENT_VERSION = 0;

	public static Chunk generate(String worldName, int gridX, int gridY) {
		return new Chunk(new ChunkData(), new Vector2(gridX, gridY));
	}

	public static Chunk load(String worldName, int gridX, int gridY) {
		String path = getFilePath(worldName, gridX, gridY);
		File file = new File(path);

		if (!file.exists()) {
			try {
				file.createNewFile();
				return generate(worldName, gridX, gridY);
			} catch (IOException exception) {
				LastTry.handleException(exception);
			}
		}

		try {
			FileReader stream = new FileReader(path);
			int version = stream.readInt32();

			if (version < CURRENT_VERSION) {
				throw new RuntimeException("Trying to load old world");
			} else if (version > CURRENT_VERSION) {
				throw new RuntimeException("Trying to load a world with unknown version");
			}

			ChunkData data = new ChunkData();

			for (int y = 0; y < Chunk.SIZE; y++) {
				for (int x = 0; x < Chunk.SIZE; x++) {
					int index = x + y * Chunk.SIZE;

					data.blocks[index] = stream.readInt16();
					data.blocksHealth[index] = stream.readByte();
					data.walls[index] = stream.readInt16();
					data.wallsHealth[index] = stream.readByte();
				}
			}

			if (!stream.readBoolean()) {
				throw new RuntimeException("Verification failed");
			}

			stream.close();

			return new Chunk(data, new Vector2(gridX, gridY));
		} catch (Exception exception) {
			LastTry.handleException(exception);
		}

		return null;
	}

	public static void save(String worldName, Chunk chunk) {
		File dir = new File(WorldProvider.getFilePath(worldName) + "/");

		if (!dir.exists()) {
			dir.mkdir();
		}

		try {
			FileWriter stream = new FileWriter(getFilePath(worldName, chunk.getGridX(), chunk.getGridY()));
			stream.writeInt32(CURRENT_VERSION);

			for (int y = 0; y < Chunk.SIZE; y++) {
				for (int x = 0; x < Chunk.SIZE; x++) {
					int index = x + y * Chunk.SIZE;

					stream.writeInt32(chunk.data.blocks[index]);
					stream.writeByte(chunk.data.blocksHealth[index]);
					stream.writeInt32(chunk.data.walls[index]);
					stream.writeByte(chunk.data.wallsHealth[index]);
				}
			}

			stream.writeBoolean(true);
			stream.close();
		} catch (Exception exception) {
			LastTry.handleException(exception);
		}
	}

	private static String getFilePath(String worldName, int x, int y) {
		return "worlds/" + worldName + "/" + x + ":" + y + ".cnk";
	}
}