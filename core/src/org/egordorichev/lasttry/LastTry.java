package org.egordorichev.lasttry;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.egordorichev.lasttry.entity.EntityManager;
import org.egordorichev.lasttry.entity.player.Player;
import org.egordorichev.lasttry.entity.player.PlayerInfo;
import org.egordorichev.lasttry.input.InputManager;
import org.egordorichev.lasttry.mod.ModLoader;
import org.egordorichev.lasttry.state.SplashState;
import org.egordorichev.lasttry.ui.UiManager;
import org.egordorichev.lasttry.util.Debug;
import org.egordorichev.lasttry.util.Log;
import org.egordorichev.lasttry.world.World;
import org.egordorichev.lasttry.world.WorldInfo;
import org.egordorichev.lasttry.world.WorldProvider;
import org.egordorichev.lasttry.world.environment.Environment;

import java.util.Random;

public class LastTry extends Game {
    /**
     * Random instance
     */
    public static final Random random = new Random();
    /**
     * Camera
     */
    public static OrthographicCamera camera;
    /**
     * UI Camera
     */
    public static OrthographicCamera uiCamera;
    /**
     * Public sprite batch
     */
    public static SpriteBatch batch;
    /**
     * Game viewport
     */
    public static Viewport viewport;
    /**
     * Last Try instance
     */
    public static LastTry instance;

    /**
     * Static log instance
     */
    public static Log log;

    /**
     * Ui manager
     */
    public static UiManager ui;

    /**
     * World instance
     */
    public static World world;

    /**
     * World info
     */
    public static WorldInfo worldInfo;

    /**
     * Player instance
     */
    public static Player player;

    /**
     * Player info
     */
    public static PlayerInfo playerInfo;

    /**
     * Environment instance
     */
    public static Environment environment;

    /**
     * PhysicBody manager instance
     */
    public static EntityManager entityManager;

    /**
     * Mod loader
     */
    public static ModLoader modLoader;

    /**
     * Debug helper
     */
    public static Debug debug;

    /**
     * Used for debug
     */
    public static ShapeRenderer shapeRenderer;

    /**
     * Logs a info-level message
     *
     * @param message message to log
     */
    public static void logDebug(String message) {
        log.debug(message);
    }

    public static void logInfo(String message) {
        log.info(message);
    }

    public static void logWarning(String message) {
        log.warn(message);
    }

    public static void logError(String message) {
        log.error(message);
    }

    /**
     * Returns mouse X coordinate, under the world
     *
     * @return mouse X coordinate, under the world
     */
    public static int getMouseXInWorld() {
        return (int) (player.getCenter().x - Gdx.graphics.getWidth() / 2 + InputManager.getMousePosition().x);
    }

    /**
     * Returns mouse Y coordinate, under the world
     *
     * @return mouse Y coordinate, under the world
     */
    public static int getMouseYInWorld() {
        return (int) (player.getCenter().y - Gdx.graphics.getHeight() / 2 + InputManager.getMousePosition().y);
    }

    /**
     * Handles exception, if it is critical, exits the game
     *
     * @param exception exception to handle
     */
    public static void handleException(Exception exception) {
        log.error(exception.getMessage());
        exception.printStackTrace();

        if (exception instanceof NullPointerException) {
            log.warn("Exiting");
            Gdx.app.exit();
        }

        // TODO: handle other exception types
    }

    /**
     * Creates first-priority instances
     */
    @Override
    public void create() {
        instance = this;
        log = new Log();
        debug = new Debug();
        shapeRenderer = new ShapeRenderer();

        Gdx.input.setInputProcessor(InputManager.multiplexer);
        Gdx.graphics.setTitle(this.getRandomWindowTitle());

        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(width, height);
        camera.setToOrtho(false, width, height);
        uiCamera = new OrthographicCamera(width, height);
        uiCamera.setToOrtho(false, width, height);
        viewport = new FitViewport(width, height);

        batch = new SpriteBatch();

        ui = new UiManager();

        this.setScreen(new SplashState());
    }

    /**
     * Handles window resize
     *
     * @param width  new window width
     * @param height new window height
     */
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        viewport.update(width, height);
        camera.update();
        camera.update();
    }

    /**
     * Renders and updates the game
     */
    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        batch.enableBlending();
        batch.begin();
        super.render();
        batch.end();
    }

    /**
     * Handles game exit
     */
    @Override
    public void dispose() {
        if (world != null) {
            WorldProvider.save(world);
        }
    }

    /**
     * Returns random title for game the window
     *
     * @return random title for game the window
     */
    private String getRandomWindowTitle() {
        return new String[]{"LastTry: Dig Peon, Dig!", "LastTry: Epic Dirt", "LastTry: Hey Guys!",
                "LastTry: Sand is Overpowered", "LastTry: Part 3: The Return of the Guide", "LastTry: A Bunnies Tale",
                "LastTry: Dr. Bones and The Temple of Blood Moon", "LastTry: Slimeassic Park",
                "LastTry: The Grass is Greener on This Side",
                "LastTry: Small Blocks, Not for Children Under the Age of 5", "LastTry: Digger T' Blocks",
                "LastTry: There is No Cow Layer", "LastTry: Suspicous Looking Eyeballs", "LastTry: Purple Grass!",
                "LastTry: Noone Dug Behind!", "LastTry: Shut Up and Dig Gaiden!", "LastTry: Java for ever!"
        }[random.nextInt(17)];
    }
}
