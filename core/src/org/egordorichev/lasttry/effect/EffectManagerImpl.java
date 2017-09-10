package org.egordorichev.lasttry.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class EffectManagerImpl implements EffectManager {
    private static final Logger logger = LoggerFactory.getLogger(EffectManagerImpl.class);
    /**
     * Items storage
     */
    public static HashMap<String, Effect> EFFECT_CACHE = new HashMap<>();

    /**
     * Loads items
     */
    public void load() {

        try {
            JsonReader jsonReader = new JsonReader();
            JsonValue root = jsonReader.parse(Gdx.files.internal("data/effects.json"));

            for (JsonValue effect : root) {
                try {
                    EFFECT_CACHE.put(effect.name(), Effect.load(effect));
                } catch (Exception exception) {
                    logger.error(exception.getMessage());
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            logger.error("Failed to load effects");
        }
    }
}
