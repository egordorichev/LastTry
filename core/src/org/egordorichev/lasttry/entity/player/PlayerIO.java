package org.egordorichev.lasttry.entity.player;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.modifier.Modifier;
import org.egordorichev.lasttry.util.FileReader;
import org.egordorichev.lasttry.util.FileWriter;
import org.egordorichev.lasttry.util.Log;
import java.io.File;
import java.io.IOException;

public class PlayerIO {
	public static final int VERSION = 0;

	public static void load(String playerName) {
		String fileName = getSaveName(playerName);
		File file = new File(fileName);

		if (!file.exists()) {
			Log.error("Failed to load player save " + playerName + "!");
			LastTry.abort();
		}

		try {
			FileReader stream = new FileReader(fileName);

			int version = stream.readInt32();

			if (version > VERSION) {
				Log.error("Trying to load unknown player.");
				LastTry.abort();
			} else if (version < VERSION) {
				Log.error("Trying to load old player.");
				LastTry.abort();
			}

			LastTry.player = new Player(playerName);

			for (int i = 0; i < Player.INVENTORY_SIZE; i++) {
				short id = stream.readInt16();

				if (id != 0) {
					ItemHolder holder = LastTry.player.inventory.getItemHolder(i);
					holder.setItem(Item.fromID(id));
					holder.setCount(stream.readInt32());

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
	}

	public static void save() {
		String fileName = getSaveName(LastTry.player.getName());
		File file = new File(fileName);

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException exception) {
				Log.error("Failed to create save file for the player " + LastTry.player.getName() + "!");
				LastTry.abort();
			}
		}

		try {
			FileWriter stream = new FileWriter(fileName);
			stream.writeInt32(VERSION);

			for (int i = 0; i < Player.INVENTORY_SIZE; i++) {
				ItemHolder holder = LastTry.player.inventory.getItemHolder(i);

				if (holder.getItem() == null) {
					stream.writeInt16(ItemID.none);
				} else {
					stream.writeInt16(holder.getItem().getID());
					stream.writeInt16((short) holder.getCount());

					if (holder.getModifier() != null) {
						stream.writeByte(holder.getModifier().getID());
					}
				}
			}

			stream.writeBoolean(true);
			stream.close();
		} catch (Exception exception) {
			LastTry.handleException(exception);
		}
	}

	public static Player generate(String name) {
		return new Player(name); // TODO
	}

	private static String getSaveName(String playerName) {
		return "players/" + playerName + ".plr";
	}
}