package org.egordorichev.lasttry.world.biome;

public class SpawnInfo {
    /**
     * How often mobs spawn
     */
    public int spawnRate;
    /**
     * Max mobs
     */
    public int spawnMax;

    public SpawnInfo(int spawnRate, int spawnMax) {
        this.spawnRate = spawnRate;
        this.spawnMax = spawnMax;
    }
}