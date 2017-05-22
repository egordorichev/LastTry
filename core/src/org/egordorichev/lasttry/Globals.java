package org.egordorichev.lasttry;

import org.egordorichev.lasttry.entity.EntityManager;
import org.egordorichev.lasttry.entity.player.Player;
import org.egordorichev.lasttry.entity.player.PlayerIO;
import org.egordorichev.lasttry.ui.UiScreen;
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
    private static UiScreen currentScreen;

    public static void dispose() {
        if (player != null) {
            PlayerIO.save();
        }

        if (world != null) {
            WorldIO.save();
        }
    }

    /**
     * Returns the current UiScreen being rendered.
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
}
