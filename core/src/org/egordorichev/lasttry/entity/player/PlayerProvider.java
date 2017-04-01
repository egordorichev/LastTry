package org.egordorichev.lasttry.entity.player;

import com.badlogic.gdx.graphics.Color;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.util.FileReader;
import org.egordorichev.lasttry.util.FileWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PlayerProvider {
	/** Current supported player version */
	public static int CURRENT_VERSION = 0;

	/**
	 * Returns all players in the "players/" directory
	 * @return all players in the "players/" directory
	 */
	public static PlayerInfo[] getPlayers() {
		File folder = new File("players/");
		File[] files = folder.listFiles();

		List<PlayerInfo> players = new ArrayList<>();

		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				players.add(getPlayerInfo("players/" + files[i].getName()));
			}
		}

		return players.toArray(new PlayerInfo[0]);
	}

	/**
	 * Returns player info from given file name
	 * @param fileName player file
	 * @return player info
	 */
	public static PlayerInfo getPlayerInfo(String fileName) {
		String playerName = fileName.replace("players/", "").replace(".plr", "");

		int version;
		int maxHp;
		int maxMana;
		PlayerType type;

		try {
			FileReader stream = new FileReader(fileName);

			version = stream.readInt32();

			maxHp = stream.readInt16();
			maxMana = stream.readInt16();

			switch (stream.readByte()) {
				case 0: default:
					type = PlayerType.SOFTCORE;
				break;
				case 1:
					type = PlayerType.MEDIUMCORE;
				break;
				case 2:
					type = PlayerType.HARDCORE;
				break;
			}

			stream.close();
		} catch (Exception exception) {
			LastTry.handleException(exception);
			return null;
		}

		return new PlayerInfo(playerName, maxHp, maxMana, type, version, new PlayerRenderInfo(1, Color.GREEN, Color
			.GRAY, Color.CORAL, 1, true)); // TODO: render info
	}

	public static Player load(String playerName) {
		return new Player(new PlayerInfo(playerName, 100, 20, PlayerType.SOFTCORE, CURRENT_VERSION, null)); // TODO
	}

	public static PlayerInfo create(String name, PlayerType type) {
		PlayerInfo player = new PlayerInfo(name, 100, 20, type, CURRENT_VERSION, new PlayerRenderInfo(1, Color.GREEN,
				Color.GRAY, Color.CORAL, 1, true));

		save(player);

		return player;
	}

	public static void save(Player player) {
		// TODO: save(player.getInfo());
	}

	public static void save(PlayerInfo player) {
		try {
			FileWriter stream = new FileWriter(getPath(player.name));

			stream.writeInt32(CURRENT_VERSION);

			stream.writeInt16((short)player.maxHp);
			stream.writeInt16((short) player.maxMana);

			switch (player.type) {
				case SOFTCORE: default:
					stream.writeByte((byte) 0);
				break;
				case MEDIUMCORE:
					stream.writeByte((byte) 1);
				break;
				case HARDCORE:
					stream.writeByte((byte) 3);
				break;
			}

			stream.close();
		} catch (Exception exception) {
			LastTry.handleException(exception);
		}
	}

	public static String getPath(String playerName) {
		return "players/" + playerName + ".plr";
	}
}