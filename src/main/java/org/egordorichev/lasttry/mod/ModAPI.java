package org.egordorichev.lasttry.mod;

import org.egordorichev.lasttry.entity.*;
import org.egordorichev.lasttry.*;
import org.egordorichev.lasttry.util.Callable;
import org.egordorichev.lasttry.world.World;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

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
	public static Enemy createEnemy(int id) {
		return Enemy.create(id);
	}
	
	/**
	 * @param id enemy id (listed in EntityID class)
	 * @param x enemy spawn X coordinate
	 * @param y enemy spawn Y coordinate
	 * @return new Enemy with given id, spawned on X, Y
	 */
	public static Enemy spawnEnemy(int id, int x, int y) {
		// TODO

		return null;
	}
	
	/**
	 * Adds given enemy class to the enemy register
	 * Now it can be accesed with createEnemy(id) or spawnEnemy(id, x, y)
	 * @param id with witch id the enemy will be defined
	 * @param enemyClass enemy class
	 */
	public static void defineEnemy(int id, Class<? extends Enemy> enemyClass) {
		Enemy.define(id, enemyClass);
	}

	/**
	 * Adds new key listner to the game input
	 * @param key Slick2d Input.Key
	 * @param callable will be called, when key is pressed
	 */
	public static void addKeyBinding(int key, Callable callable) {
		LastTry.input.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(int i, char c) {
				if (i == key) {
					callable.call();
				}
			}

			@Override
			public void keyReleased(int i, char c) {

			}

			@Override
			public void setInput(Input input) {

			}

			@Override
			public boolean isAcceptingInput() {
				return true;
			}

			@Override
			public void inputEnded() {

			}

			@Override
			public void inputStarted() {

			}
		});
	}
}
