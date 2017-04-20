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

    public BiomeAnimationComponent biomeAnimation;

    protected String name;

    private SpawnInfo spawnInfo;

    public Biome(String name, SpawnInfo spawnInfo, Texture backgroundTexture) {
        this.name = name;
        this.spawnInfo = spawnInfo;
        biomeAnimation = new BiomeAnimationComponent(this, backgroundTexture);
    }

    public static void preload() {
    }

    public void fadeIn() { biomeAnimation.fadeIn(); }

    public void fadeOut() { biomeAnimation.fadeOut();}

    public boolean fadeInIsDone() { return biomeAnimation.fadeInIsDone(); }

    public boolean fadeOutIsDone() { return this.biomeAnimation.fadeOutIsDone(); }

    public String getName() {
        return this.name;
    }

    public void renderBackground() { this.biomeAnimation.renderBackground();}

    public int getSpawnRate()
    {
        return spawnInfo.spawnRate;
    }

    public int getSpawnMax()
    {
        return spawnInfo.spawnMax;
    }
}
