package org.egordorichev.lasttry.world.spawn;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import javafx.util.Pair;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.AdvancedRectangle;
import org.egordorichev.lasttry.util.Rectangle;
import org.egordorichev.lasttry.world.biome.Biome;
import org.egordorichev.lasttry.world.environment.Environment;
import org.egordorichev.lasttry.world.environment.Event;
import org.w3c.dom.ls.LSException;

import java.util.ArrayList;

/**
 * Spawn system that will spawn monsters in the gameworld based on certain rules.
 *
 * Created by LogoTie on 17/04/2017.
 */
public class SpawnSystem {

    private Biome biome;

    private int spawnRate, maxSpawns;

    private boolean day = false;

    public void update(){

        //Get user biome
        biome = LastTry.environment.getCurrentBiome();

        //Retrieve spawn rate & max spawns
        spawnRate = biome.getSpawnRate();
        maxSpawns = biome.getSpawnMax();
    }

    private void calculateSpawnRate(int spawnRate){

        //Get active events
        ArrayList<Event> activeEvents = LastTry.environment.getCurrentEvents();

        spawnRate = calcSpawnRateBasedOnEvents(activeEvents, spawnRate);

        spawnRate = calcSpawnRateBasedOnItems(spawnRate);

        spawnRate = calcSpawnRateBasedOnTime(spawnRate);

        int activeMonstersSpawnRate;
    }


    private int getTotalSpawnRateOfActiveEnemies(){return 0;}

    public void calcArea(){

        float xOfPLayer = LastTry.player.getCenterX();
        float yOfPlayer = LastTry.player.getCenterY();

        AdvancedRectangle advancedRectangle = new AdvancedRectangle(xOfPLayer, yOfPlayer, 6);

        if(advancedRectangle.allSidesInBoundary()){
            LastTry.debug("All sides in boundary");
            //advancedRectangle.debugSetItemsOnPoints();
        }
    }


    public void calculateNonSpawnSafePlayerArea(){
        //TODO Implement
    }


    private int calcSpawnRateBasedOnEvents(ArrayList<Event> activeEvents, int spawnRate){
        //TODO Implement spawn info in events
        return 0;
    }

    private int calcSpawnRateBasedOnTime(int spawnRate){
        //TODO Implement spawn info based on day
        return 0;
    }

    private int calcSpawnRateBasedOnItems(int spawnRate){
        //TODO Implement spawn rate altering based on items in environment
        return spawnRate;
    }

}
