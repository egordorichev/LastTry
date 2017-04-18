package org.egordorichev.lasttry.world.chunk;

import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.LastTry;
import java.io.FileInputStream;

public class ChunkIO {
	public static Chunk load(int x, int y) {
		try {
			FileInputStream stream = new FileInputStream("assets/worlds/" + LastTry.world.get);
		} catch (Exception exception) {
			LastTry.handleException(exception);
		}

		return new EmptyChunk(new Vector2(x, y));
	}

	public static void save(int x, int y) {

	}
}