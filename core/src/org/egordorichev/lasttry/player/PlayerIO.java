package org.egordorichev.lasttry.player;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.inventory.ItemHolder;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.util.FileReader;
import org.egordorichev.lasttry.util.FileWriter;
import org.egordorichev.lasttry.util.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class PlayerIO {
	private static final Logger logger = LoggerFactory.getLogger(PlayerIO.class);
	public static final byte VERSION = 5;

	/**
	 * Loads player
	 *
	 * @param playerName
	 *            Player name
	 */
	public static void load(String playerName) {
		String fileName = Files.getPlayerSave(playerName);
		File file = new File(fileName);

		if (!file.exists()) {
			logger.error("Failed to load player save " + playerName + "!");
			LastTry.abort();
		}

		logger.debug("Loading player " + playerName + "...");

		try {
			FileReader reader = new FileReader(fileName);

			int version = reader.readByte();
			boolean isCurrent = version == VERSION;
			boolean isFuture = version > VERSION;

			if (!isCurrent) {
				String tense = isFuture ? "future" : "past";
				logger.error("Failed loading " + tense + " version: " + version + ", current version is: " + VERSION);
				LastTry.abort();
			}

			Globals.setPlayer(new Player(playerName));
			Globals.getPlayer().stats.set(reader.readInt16(), reader.readInt16(), 0, 0);

			for (int i = 0; i < Player.INVENTORY_SIZE; i++) {
				String id = reader.readString();

				if (id != null) {
					ItemHolder holder = Globals.getPlayer().getInventory().getItemInSlot(i);
					holder.setItem(Item.fromID(id));
					holder.setCount(reader.readInt16());

					//if (reader.readBoolean()) {
						//holder.setModifier(Modifier.fromID(reader.readByte()));
					//}
				}
			}

<<<<<<< HEAD
			/*if (!reader.readBoolean()) {
				Log.error("Verification failed");
=======
			if (!reader.readBoolean()) {
				logger.error("Verification failed");
>>>>>>> be898a55f45a38c929947702b4a1bc64169fd8e4
				LastTry.abort();
			}*/

			reader.close();
		} catch (Exception exception) {
			LastTry.handleException(exception);
		}

		logger.debug("Done loading player " + playerName + "!");
	}

	/**
	 * Saves current player
	 */
	public static void save() {
		File dir = new File(Files.getPlayersDir());

		if (!dir.exists()) {
			dir.mkdir();
		}

		String fileName = Files.getPlayerSave(Globals.getPlayer().getName());
		File file = new File(fileName);
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
				logger.error("Failed to create save file for the player " + Globals.getPlayer().getName() + "!");
				LastTry.abort();
			}
		}

		logger.debug("Saving player " + Globals.getPlayer().getName() + "...");

		try {
			FileWriter writer = new FileWriter(fileName);
			writer.writeByte(VERSION);
			writer.writeInt16((short) Globals.getPlayer().stats.getMaxHP());
			writer.writeInt16((short) Globals.getPlayer().stats.getMaxMana());

			for (int i = 0; i < Player.INVENTORY_SIZE; i++) {
				ItemHolder holder = Globals.getPlayer().getInventory().getItemInSlot(i);

				if (holder.getItem() == null) {
					writer.writeString("");
				} else {
					writer.writeString(holder.getItem().getID());
					writer.writeInt16((short) holder.getCount());

					/*if (holder.getModifier() != null) {
						writer.writeBoolean(true);
						writer.writeByte(holder.getModifier().getID());
					} else {*/
						writer.writeBoolean(false);
					//}
				}
			}

			writer.writeBoolean(true);
			writer.close();
		} catch (Exception exception) {
			LastTry.handleException(exception);
		}

		logger.debug("Done saving player " + Globals.getPlayer().getName() + "!");
	}

	/**
	 * Generates new player
	 *
	 * @param name
	 *            Player name
	 * @return New player
	 */
	public static Player generate(String name) {
		int x = Globals.getWorld().getWidth() / 2;
		Player player = new Player(name);
		player.getInventory().add(new ItemHolder(Item.fromID("lt:copper_shortsword"), 1));
		player.getInventory().add(new ItemHolder(Item.fromID("lt:copper_pickaxe"), 1));
		player.getInventory().add(new ItemHolder(Item.fromID("lt:copper_axe"), 1));
		return player;
	}

	/**
	 * Player save exists
	 *
	 * @param playerName
	 *            Player name
	 * @return Save exists
	 */
	public static boolean saveExists(String playerName) {
		return new File(Files.getPlayerSave(playerName)).exists();
	}
}