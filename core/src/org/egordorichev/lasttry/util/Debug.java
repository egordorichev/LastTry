package org.egordorichev.lasttry.util;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.world.chunk.Chunk;

import java.util.*;

public class Debug {
	private boolean enabled;
	private int uniqueCounter;
	private Map<Integer, GenericContainer.UniqueTypePair<String, Integer>> messagesToBePrinted = new LinkedHashMap<>();

	public Debug() {
		this.enabled = !LastTry.release;
	}

	public void render() {
		if (!this.enabled) {
			return;
		}

		Assets.f18.draw(Graphics.batch, Gdx.graphics.getFramesPerSecond() + " FPS", 15, 60);
		Assets.f18.draw(Graphics.batch, "X: " + Globals.getPlayer().physics.getGridX() + " Y: " + Globals.getPlayer().physics.getGridY(), 15, 90);
		Assets.f18.draw(Graphics.batch, "Chunk: " + (Globals.getPlayer().physics.getGridX() / Chunk.SIZE)
		   + ":" + (Globals.getPlayer().physics.getGridY() / Chunk.SIZE), 15, 110);
		Assets.f18.draw(Graphics.batch, "Total amount of enemies: " + Globals.entityManager.getCreatureEntities().size(), 15, 130);
		Assets.f18.draw(Graphics.batch, "Current Biome: " + Globals.environment.currentBiome.getID(), 15, 150);
		Assets.f18.draw(Graphics.batch, "Current world time: " + Globals.environment.time.toString(true), 15, 170);

		if (messagesToBePrinted.keySet().size() == 0) {
			return;
		}

		// Iterators are positions before the first element.
		// A linked hash map is used, therefore we can guarantee the insertion order data is kept
		int currentMessageKey = messagesToBePrinted.keySet().iterator().next();

		// Pair containing the String message and the amount of ticks the message should be displayed for.
		GenericContainer.UniqueTypePair<String, Integer> currentMessagePair = messagesToBePrinted.get(currentMessageKey);

		String message = currentMessagePair.getValue1();
		int gameTicksCounter = currentMessagePair.getValue2();

		// If game ticks counter equals 0, means no more ticks should be allocated to rendering the message
		if(gameTicksCounter == 0){
			messagesToBePrinted.remove(currentMessageKey);
			return;
		}

		Assets.f18.draw(Graphics.batch, message, 10, 150);

		// Decrement counter as it has been displayed for 1 tick
		gameTicksCounter--;

		// Update the pair with the new game ticks counter.
		currentMessagePair.setValue2(gameTicksCounter);
	}

	public void enable() {
		if (LastTry.release) {
			return;
		}

		this.enabled = true;
	}

	public void disable() {
		if (LastTry.release) {
			return;
		}

		this.enabled = false;
	}

	public void toggle() {
		if (LastTry.release) {
			return;
		}

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