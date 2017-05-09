package org.egordorichev.lasttry.entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.core.Bootstrap;
import org.egordorichev.lasttry.util.Log;
import java.util.HashMap;

public class Enemies {
    public static HashMap<String, EnemyInfo> ENEMY_CACHE = new HashMap<>();

    public static void load() {
        if (!Bootstrap.isLoaded()) {
            Log.error("Trying to access enemies class before bootstrap");
            return;
        }

        ENEMY_CACHE.clear();

        try {
            JsonReader jsonReader = new JsonReader();
            JsonValue root = jsonReader.parse(Gdx.files.internal("enemies/standard.json"));

            for (JsonValue enemy : root) {
	            ENEMY_CACHE.put(enemy.name(), new EnemyInfo(enemy));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            Log.error("Failed to load enemies");
            return;
        }
    }

    public static Enemy create(String name) {
        EnemyInfo enemy = ENEMY_CACHE.get(name);

        if (enemy == null) {
            Log.warn("Enemy with name " + name + " does not exist.");
            return null;
        }

        return enemy.create();
    }
}