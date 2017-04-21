package org.egordorichev.lasttry.util;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.world.spawn.SpawnSystem;

import java.lang.reflect.Array;
import java.util.*;

public class Debug {
    private boolean enabled;
    private int uniqueCounter;
    //LinkedHashMap guarantees insertion order of elements, is kept.
    private Map<Integer, GenericContainer.UniqueTypePair> messagesToBePrinted = new LinkedHashMap<>();
    public Debug() {
        this.enabled = false;
    }

    public void render() {
        if (!this.enabled) {
            return;
        }

	    Assets.f22.draw(LastTry.batch, String.valueOf(Gdx.graphics.getFramesPerSecond()), 10, 30);
	    Assets.f22.draw(LastTry.batch, "X: " + String.format("%d", LastTry.player.physics.getGridX())
            + " Y: " + String.format("%d", LastTry.player.physics.getGridY()), 40, 30);
	    Assets.f22.draw(LastTry.batch, "Spawn weight of active enemies: "+LastTry.spawnSystem.getSpawnWeightOfCurrentlyActiveEnemies(), 10, 50);
	    Assets.f22.draw(LastTry.batch, "Remaining spawn weight of biome: "+LastTry.spawnSystem.getRemainingSpawnWeightOfBiome(), 10, 70);
	    Assets.f22.draw(LastTry.batch, "Enemies in active area: "+LastTry.spawnSystem.getEnemiesInActiveAreaCount(), 10, 90);
	    //TODO Heavy call, should be rethought and removed
	    Assets.f22.draw(LastTry.batch, "Total amount of enemies: "+LastTry.entityManager.retrieveEnemyEntities().size(), 10, 110);


	    if(messagesToBePrinted.keySet().size()==0){
	        return;
        }

        //Iterators are positions before the first element.
        //A linked hash map is used, therefore we can guarantee the insertion order data is kept
        int currentMessageKey = messagesToBePrinted.keySet().iterator().next();

	    //Pair containing the String message and the amount of ticks the message should be displayed for.
        GenericContainer.UniqueTypePair<String, Integer> currentMessagePair = messagesToBePrinted.get(currentMessageKey);

        String message = currentMessagePair.getValue1();
        int gameTicksCounter = currentMessagePair.getValue2();

        //If game ticks counter equals 0, means no more ticks should be allocated to rendering the message
        if(gameTicksCounter==0){
            messagesToBePrinted.remove(currentMessageKey);
            return;
        }

        Assets.f22.draw(LastTry.batch, message, 10, 130);

        //Decrement counter as it has been displayed for 1 tick
        gameTicksCounter--;

        //Update the pair with the new game ticks counter.
        currentMessagePair.setValue2(gameTicksCounter);

    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }

    public void toggle() {
        this.enabled = !this.enabled;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void print(String message) {

        //If tab debug counter is false, we do not add a print message as then the debug tab will only show old messages
        if(enabled==false){
            return;
        }

        //Create a pair containing the message and the amount of ticks the message should be displayed for. 120 ticks - 2 seconds.
        GenericContainer.UniqueTypePair<String, Integer> messagePairDetails = new GenericContainer.UniqueTypePair<>(message, 60);

        //Counter is used as a unique Id
        messagesToBePrinted.put(uniqueCounter++, messagePairDetails);
    }
}