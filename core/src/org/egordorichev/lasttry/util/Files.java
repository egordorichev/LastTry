package org.egordorichev.lasttry.util;

import java.io.File;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;

/**
 * A utility for retrieving common file paths.
 */
public class Files {
	private final static String FS = File.separator;

	/**
	 * Gets the save file for the world by the given name.
	 * 
	 * @param worldName
	 *            Name of the world.
	 * @return
	 */
	public static String getWorldSave(String worldName) {
		return getWorldDir(worldName) + "save.wld";
	}

	/**
	 * Gets the save file for the player by the given name.
	 * 
	 * @param playerName
	 *            Name of the player.
	 * @return
	 */
	public static String getPlayerSave(String playerName) {
		return getPlayersDir() + playerName + ".plr";
	}

	/**
	 * Gets the save directory for a world by the given name.
	 * 
	 * @param worldName
	 *            World save folder.
	 * @return
	 */
	public static String getWorldDir(String worldName) {
		return getWorldsDir() + worldName + FS;
	}

	/**
	 * Gets the save file for chunk in the current world at the given chunk
	 * coordinates.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static String getChunkSave(int x, int y) {
		return getWorldDir(Globals.getWorld().getName()) + x + "." + y + ".cnk";
	}

	/**
	 * Gets the worlds directory.
	 * 
	 * @return
	 */
	public static String getWorldsDir() {
		return getDataDir() + "worlds" + FS;
	}

	/**
	 * Gets the players directory.
	 * 
	 * @return
	 */
	public static String getPlayersDir() {
		return getDataDir() + "players" + FS;
	}

	/**
	 * Gets the mods directory.
	 * 
	 * @return
	 */
	public static String getModsDir() {
		return getDataDir() + "mods" + FS;
	}

	/**
	 * Gets the data directory.
	 * 
	 * @return
	 */
	public static String getDataDir() {
		return getRootDir() + FS + "data" + FS;
	}

	/**
	 * Gets the home directory.
	 * 
	 * @return
	 */
	public static String getRootDir() {
		boolean rel = LastTry.storeRelative;
		String home = rel ? System.getProperty("user.dir") : System.getProperty("user.home");
		if (rel) {
			return home + FS;
		}
		return home + FS + ".LastTry" + FS;
	}
}
