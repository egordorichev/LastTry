package org.egordorichev.lasttry.world.biome;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class BiomeManagerImpl implements BiomeManager {
    private static final Logger logger = LoggerFactory.getLogger(BiomeManagerImpl.class);

    /**
     * Max temperature
     */
    public static final byte TEMP_MAX = 100;
    /**
     * Max humidity
     */
    public static final byte HUMD_MAX = 100;

    /**
     * All biomes are stored here
     */
    public static final List<Biome> BIOME_CACHE = new ArrayList<>();

    /**
     * Loads items
     */
    public void load() {

        try {
            JsonReader jsonReader = new JsonReader();
            JsonValue root = jsonReader.parse(Gdx.files.internal("data/biomes.json"));

            for (JsonValue value : root) {
                try {
                    Biome biome = new Biome(value.name());
                    biome.loadFields(value);
                    logger.debug("Loaded biome: " + biome.getID() + " - Cached[" + (!biome.ignoreCache) + "]");
                    if (!biome.ignoreCache) {
                        BIOME_CACHE.add(biome);
                    }
                } catch (Exception exception) {
                    logger.error("Failed to parse " + value.name());
                    exception.printStackTrace();
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            logger.error("Failed to load biomes");
        }

        BIOME_CACHE.sort(new Comparator<Biome>() {
            @Override
            public int compare(Biome biome1, Biome biome2) {
                if (biome1.getLevel() < biome2.getLevel()) {
                    return 1;
                } else if (biome1.getLevel() > biome2.getLevel()) {
                    return -1;
                }

                return 0;
            }
        });
    }

    /**
     * Returns biomes with given id
     *
     * @param id
     */
    public Biome get(String id) {
        for (Biome biome : BIOME_CACHE) {
            if (biome.getID().equals(id)) {
                return biome;
            }
        }

        return null;
    }

    @Override
    public Iterator<Biome> iterator() {
        return BIOME_CACHE.iterator();
    }

    /**
     * Return the closest biome to the given coordinates.
     *
     * @param temperature
     * @param humidity
     * @return
     */
    public Biome getClosest(int temperature, int humidity) {
        Biome closest = null;
        float closestDist = -1;

        for (Biome biome : BIOME_CACHE) {
            Vector2 v = new Vector2(temperature, humidity);
            float dist = v.dst2(biome.getBiomeVector());

            if (closestDist < 0 || closestDist > dist) {
                closest = biome;
                closestDist = dist;
            }
        }

        return closest;
    }
}
