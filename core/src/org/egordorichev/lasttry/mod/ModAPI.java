package org.egordorichev.lasttry.mod;

import com.badlogic.gdx.InputProcessor;
import org.egordorichev.lasttry.*;
import org.egordorichev.lasttry.entity.Player;
import org.egordorichev.lasttry.entity.enemy.Enemy;
import org.egordorichev.lasttry.util.Callable;
import org.egordorichev.lasttry.util.Util;
import org.egordorichev.lasttry.world.World;

public class ModAPI {
	/**
	 * @return reference to client's player
	 */
	public static Player getPlayer() {
		return LastTry.player;
	}

	/**
	 * @return reference to current world or null, if it is not loaded yet
	 */
	public static World getWorld() {
		return LastTry.world;
	}

	/**
	 * @param id enemy id (listed in EntityID class)
	 * @return new Enemy with given id, or null, if it is not found
	 */
	public static Enemy createEnemy(short id) {
		return Enemy.create(id);
	}
	
	/**
	 * @param id enemy id (listed in EntityID class)
	 * @param x enemy spawn X coordinate
	 * @param y enemy spawn Y coordinate
	 * @return new Enemy with given id, spawned on X, Y
	 */
	public static Enemy spawnEnemy(short id, int x, int y) {
		return LastTry.entityManager.spawnEnemy(id, x, y);
	}
	
	/**
	 * Adds given enemy class to the enemy register
	 * Now it can be accesed with createEnemy(id) or spawnEnemy(id, x, y)
	 * @param id with witch id the enemy will be defined
	 * @param enemyClass enemy class
	 */
	public static void defineEnemy(short id, Class<? extends Enemy> enemyClass) {
		Enemy.define(id, enemyClass);
	}

	/**
	 * Adds new key listner to the game input
	 * @param key Input.Keys
	 * @param callable will be called, when key is pressed
	 */
	public static void addKeyBinding(int key, Callable callable) {
		Util.multiplexer.addProcessor(new InputProcessor() {
			@Override
			public boolean keyDown(int keycode) {
				if (keycode == key) {
					callable.call();
				}

				return false;
			}

			@Override
			public boolean keyUp(int keycode) {
				return false;
			}

			@Override
			public boolean keyTyped(char character) {
				return false;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				return false;
			}

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				return false;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				return false;
			}

			@Override
			public boolean scrolled(int amount) {
				return false;
			}
		});
	}
}