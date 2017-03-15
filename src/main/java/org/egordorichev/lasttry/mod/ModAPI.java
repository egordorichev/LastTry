package org.egordorichev.lasttry.mod;

import org.egordorichev.lasttry.entity.*;
import org.egordorichev.lasttry.*;
import org.egordorichev.lasttry.util.Callable;
import org.egordorichev.lasttry.world.World;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

public class ModAPI { // TODO: add more
	public static Player getPlayer() {
		return LastTry.player;
	}

	public static World getWorld() {
		return LastTry.world;
	}

	public static Enemy createEnemy(int id) {
		return Enemy.create(id);
	}
	
	public static Enemy spawnEnemy(int id, int x, int y) {
		return LastTry.world.spawnEnemy(id, x, y);
	}
	
	// TODO: add methods for creating new enemies

	public static void addKeyBinding(int key, Callable callable) {
		LastTry.input.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(int i, char c) {
				if(i == key) {
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
