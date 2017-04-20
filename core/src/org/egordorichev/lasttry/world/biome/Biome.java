package org.egordorichev.lasttry.world.biome;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.world.biome.components.BiomeAnimationComponent;

public class Biome {
    public static Biome forest = new ForestBiome();
    public static Biome desert = new DesertBiome();
    public static Biome corruption = new CorruptionBiome();
    public static Biome crimson = new CrimsonBiome();
    public static Biome corruptDesert = new CorruptDesertBiome();
    public static Biome crimsonDesert = new CrimsonDesertBiome();

    public BiomeAnimationComponent animation;

    protected String name;

    private SpawnInfo spawnInfo;

    public Biome(String name, SpawnInfo spawnInfo, Texture backgroundTexture) {
        this.name = name;
        this.spawnInfo = spawnInfo;
        animation = new BiomeAnimationComponent(this, backgroundTexture);
    }

    //TODO To be implemented
    public static void preload() { }

    public String getName() {
        return this.name;
    }

    public int getSpawnRate()
    {
        return spawnInfo.spawnRate;
    }

    public int getSpawnMax()
    {
        return spawnInfo.spawnMax;
    }
}
