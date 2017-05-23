package org.egordorichev.lasttry.world.biome;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

public class BiomeMap {
    /**
     * 
     */
    private final List<Biome> biomes = new ArrayList<>();

    public void register(Biome biome) {
        biomes.add(biome);
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
        for (Biome biome : biomes) {
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
