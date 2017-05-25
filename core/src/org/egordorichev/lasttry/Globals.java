package org.egordorichev.lasttry;

import org.egordorichev.lasttry.entity.EntityManager;
import org.egordorichev.lasttry.entity.player.Player;
import org.egordorichev.lasttry.entity.player.PlayerIO;
import org.egordorichev.lasttry.ui.UiScreen;
import org.egordorichev.lasttry.ui.chat.UiChat;
import org.egordorichev.lasttry.world.World;
import org.egordorichev.lasttry.world.WorldIO;
import org.egordorichev.lasttry.world.biome.BiomeMap;
import org.egordorichev.lasttry.world.chunk.gc.ChunkGcManager;
import org.egordorichev.lasttry.world.environment.Environment;
import org.egordorichev.lasttry.world.spawn.SpawnSystem;

import com.badlogic.gdx.math.Vector2;

public class Globals {
    /**
     * Used in world generation. Associates a biome to coordinates on a graph.
     * Coordinates are in temperature and humidity, both ranging from 1-100.
     */
    public static final BiomeMap biomeMap = new BiomeMap();
    /**
     * The current world.
     */
    private static World world;
    /**
     * The player instance.
     */
    public static Player player;
    public static Environment environment;
    public static SpawnSystem spawnSystem;
    public static Vector2 resolution;
    /**
     * The entity manager. Handles rendering, updating, item-dropping, etc. of
     * all loaded entities.
     */
    public static EntityManager entityManager;
    /**
     * The chunk garbage collector.
     */
    public static ChunkGcManager chunkGcManager;
    /**
     * The chat UI.
     */
    public static UiChat chat;
    /**
     * The currently displayed screen. If the current screen is null, then the
     * player is in-game with no overlay menus.
     */
    private static UiScreen currentScreen;

    /**
     * Clean up and save player and world data.
     */
    public static void dispose() {
        if (player != null) {
            PlayerIO.save();
        }

        if (getWorld() != null) {
            WorldIO.save();
        }
    }

    /**
     * Returns the current UiScreen being rendered. If the current screen is
     * null, then the player is in-game with no overlay menus.
     * 
     * @return
     */
    public static UiScreen getCurrentScreen() {
        return currentScreen;
    }

    /**
     * Sets the current UiScreen. Handles the open values of the current and
     * to-be current screens.
     * 
     * @param newScreen
     *            Screen to set.
     */
    public static void setCurrentScreen(UiScreen newScreen) {
        // Close the current screen
        if (Globals.currentScreen != null) {
            Globals.currentScreen.setOpen(false);
            Globals.currentScreen.onUIClose();
        }
        // Open the next screen
        if (newScreen != null) {
            newScreen.setOpen(true);
            newScreen.onUIOpen();
        }
        // Set the next screen
        Globals.currentScreen = newScreen;
    }

    public static World getWorld() {
        return world;
    }

    public static void setWorld(World world) {
        Globals.world = world;
        // TODO: While not a normal vanilla feature of Terraria, I think having
        // multiple dimensions would be cool.
        // Kinda like the Minecraft Mystcraft mod.
    }
}
