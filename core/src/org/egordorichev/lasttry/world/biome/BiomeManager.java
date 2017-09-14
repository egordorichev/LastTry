package org.egordorichev.lasttry.world.biome;

import java.util.Iterator;

public interface BiomeManager {
    void load();
    Biome get(String id);
    Iterator<Biome> iterator();
    Biome getClosest(int temperature, int humidity);

}
