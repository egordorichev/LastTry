package org.egordorichev.lasttry.player;

import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.inventory.ItemHolder;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.modifier.Modifier;
import org.egordorichev.lasttry.util.FileReader;
import org.egordorichev.lasttry.util.FileWriter;
import org.egordorichev.lasttry.util.Log;

import java.io.File;
import java.io.IOException;

public class PlayerIO {
	public static final byte VERSION = 4;

	public static void load(String playerName) {
		String fileName = getSaveName(playerName);
		File file = new File(fileName);

		if (!file.exists()) {
			Log.error("Failed to load player save " + playerName + "!");
			LastTry.abort();
		}

		Log.debug("Loading player " + playerName + "...");

		try {
			FileReader stream = new FileReader(fileName);

			int version = stream.readByte();
			boolean isCurrent = version == VERSION;
			boolean isFuture = version > VERSION;

			if (!isCurrent) {
				String tense = isFuture ? "future" : "past";
				Log.error("Failed loading " + tense + " version: " + version + ", current version is: " + VERSION);
				LastTry.abort();
			}

			Globals.setPlayer(new Player(playerName));
			Globals.getPlayer().stats.set(stream.readInt16(), stream.readInt16(), 0, 0);
			Globals.getPlayer().setSpawnPoint(new Vector2(stream.readInt16(), stream.readInt16()));

			for (int i = 0; i < Player.INVENTORY_SIZE; i++) {
				String id = stream.readString();

				if (id != null) {
					ItemHolder holder = Globals.getPlayer().getInventory().getItemInSlot(i);
					holder.setItem(Item.fromID(id));
					holder.setCount(stream.readInt16());

					if (stream.readBoolean()) {
						holder.setModifier(Modifier.fromID(stream.readByte()));
					}
				}
			}

			if (!stream.readBoolean()) {
				Log.error("Verification failed");
				LastTry.abort();
			}

			stream.close();
		} catch (Exception exception) {
			LastTry.handleException(exception);
		}

		Log.debug("Done loading player " + playerName + "!");
	}

	public static void save() {
		File dir = new File("data/players/");

		if (!dir.exists()) {
			dir.mkdir();
		}

		String fileName = getSaveName(Globals.getPlayer().getName());
		File file = new File(fileName);

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException exception) {
				Log.error("Failed to create save file for the player " + Globals.getPlayer().getName() + "!");
				LastTry.abort();
			}
		}

		Log.debug("Saving player " + Globals.getPlayer().getName() + "...");

		try {
			FileWriter stream = new FileWriter(fileName);

			stream.writeByte(VERSION);
			stream.writeInt16((short) Globals.getPlayer().stats.getMaxHP());
			stream.writeInt16((short) Globals.getPlayer().stats.getMaxMana());
			stream.writeInt16((short) Globals.getPlayer().getSpawnPoint().x);
			stream.writeInt16((short) Globals.getPlayer().getSpawnPoint().y);

			for (int i = 0; i < Player.INVENTORY_SIZE; i++) {
				ItemHolder holder = Globals.getPlayer().getInventory().getItemInSlot(i);

				if (holder.getItem() == null) {
					stream.writeString("");
				} else {
					stream.writeString(holder.getItem().getID());
					stream.writeInt16((short) holder.getCount());

					if (holder.getModifier() != null) {
						stream.writeBoolean(true);
						stream.writeByte(holder.getModifier().getID());
					} else {
						stream.writeBoolean(false);
					}
				}
			}

			stream.writeBoolean(true);
			stream.close();
		} catch (Exception exception) {
			LastTry.handleException(exception);
		}

		Log.debug("Done saving player " + Globals.getPlayer().getName() + "!");
	}

	public static Player generate(String name) {
		Player player = new Player(name);

		player.setSpawnPoint(new Vector2(Globals.getWorld().getWidth() / 2, Globals.getWorld().getHeight() - 10));

		player.getInventory().add(new ItemHolder(Item.fromID("lt:copper_shortsword"), 1));
		player.getInventory().add(new ItemHolder(Item.fromID("lt:copper_pickaxe"), 1));
		player.getInventory().add(new ItemHolder(Item.fromID("lt:copper_axe"), 1));

		return player;
	}

	public static boolean saveExists(String name) {
		File file = new File(getSaveName(name));
		return file.exists();
	}

	private static String getSaveName(String playerName) {
		return "data/players/" + playerName + ".plr";
	}
}