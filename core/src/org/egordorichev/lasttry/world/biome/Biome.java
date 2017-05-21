package org.egordorichev.lasttry.world.biome;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

    public Biome(String name, SpawnInfo spawnInfo, TextureRegion backgroundTextureRegion) {
        this.name = name;
        this.spawnInfo = spawnInfo;
        animation = new BiomeAnimationComponent(this, backgroundTextureRegion);
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
