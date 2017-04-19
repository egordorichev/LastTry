package org.egordorichev.lasttry.entity.enemy;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.core.Bootstrap;
import org.egordorichev.lasttry.entity.EnemyID;
import org.egordorichev.lasttry.entity.enemy.enemies.*;
import org.egordorichev.lasttry.util.Log;

import java.util.HashMap;

public class Enemies {
	public static HashMap<Short, Class<? extends Enemy>> ENEMY_CACHE = new HashMap<>();

	static {
		if (!Bootstrap.isLoaded()) {
			Log.error("Trying to access enemies class before bootstrap");
		} else {
			define(EnemyID.greenSlime, GreenSlime.class);
			define(EnemyID.blueSlime, BlueSlime.class);
			define(EnemyID.eyeOfCthulhu, EyeOfCthulhu.class);
			define(EnemyID.zombie, Zombie.class);
		}
	}


	public static void define(short id, Class<? extends Enemy> enemy) {
		if (ENEMY_CACHE.containsKey(id)) {
			Log.error("Enemy with id " + id + " is already defined!");
		} else {
			Log.debug("Defined [" + id + "] as " + enemy.getSimpleName());
			ENEMY_CACHE.put(id, enemy);
		}
	}

	public static Enemy create(short id) {
		try {
			Class<? extends Enemy> aClass = ENEMY_CACHE.get(id);

			if (aClass != null) {
				return aClass.newInstance();
			} else {
				Log.warn("Enemy with id " + id + " is not found");
				return null;
			}
		} catch (Exception exception) {
			LastTry.handleException(exception);
			return null;
		}
	}
}
