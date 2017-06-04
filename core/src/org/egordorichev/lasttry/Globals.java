package org.egordorichev.lasttry;

import org.egordorichev.lasttry.entity.EntityManager;
import org.egordorichev.lasttry.entity.player.Player;
import org.egordorichev.lasttry.entity.player.PlayerIO;
import org.egordorichev.lasttry.ui.chat.UiChat;
import org.egordorichev.lasttry.world.World;
import org.egordorichev.lasttry.world.WorldIO;
import org.egordorichev.lasttry.world.chunk.gc.ChunkGcManager;
import org.egordorichev.lasttry.world.environment.Environment;
import org.egordorichev.lasttry.world.spawn.SpawnSystem;

public class Globals {
	public static World world;
	public static Player player;
	public static Environment environment;
	public static SpawnSystem spawnSystem;
	public static EntityManager entityManager;
	public static ChunkGcManager chunkGcManager;
	public static UiChat chat;

	public static void dispose() {
		if (player != null){
			PlayerIO.save();
		}

		if (world != null) {
			WorldIO.save();
		}
	}
}
