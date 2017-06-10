package org.egordorichev.lasttry.world.environment;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Callable;
import org.egordorichev.lasttry.util.Camera;
import org.egordorichev.lasttry.util.Log;
import org.egordorichev.lasttry.util.Util;
import org.egordorichev.lasttry.world.WorldTime;
import org.egordorichev.lasttry.world.biome.Biome;
import org.egordorichev.lasttry.world.biome.Biomes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Environment {
	public HashMap<String, Short> blockCount;
	public WorldTime time;
	public List<Event> events = new ArrayList<>();
	public Biome currentBiome;
	public Biome lastBiome;

	public Environment() {
		this.currentBiome = Biomes.get("lt:forest");
		this.currentBiome.animation.fadeInFast();
		this.lastBiome = Biomes.get("lt:forest");
		this.blockCount = new HashMap<>();
		this.time = new WorldTime((byte) 8, (byte) 15);

		Util.runDelayedThreadSeconds(new Callable() {
			@Override
			public void call() {
				updateBiome();
			}
		}, 1);
	}

	public void render() {
		int time = this.time.getHour() * 60 + this.time.getMinute();
		int height = Gdx.graphics.getHeight();

		for (int i = 0; i < Gdx.graphics.getWidth(); i++) {
			Graphics.batch.draw(Graphics.skyTexture, i, 0, 1, height,
				time, 0, 1, 1024, false, false);
		}

		if (this.currentBiome != null) {
			this.currentBiome.animation.render();
		}

		if (this.lastBiome != null) {
			this.lastBiome.animation.render();
		}
	}

	public void update(int dt) {
		if (Globals.getWorld() == null) {
			return;
		}

		this.time.update();

		if (this.currentBiome != null && !this.currentBiome.animation.fadeInIsDone()) {
			this.currentBiome.animation.fadeIn();
		}

		if (this.lastBiome != null && this.lastBiome != this.currentBiome &&
				!this.lastBiome.animation.fadeOutIsDone()) {

			this.lastBiome.animation.fadeOut();
		}

		for (int i = this.events.size() - 1; i >= 0; i--) {
			Event event = this.events.get(i);

			if (!event.isHappening()) {
				event.end();
				this.events.remove(i);
			} else {
				event.update(dt);
			}
		}

		Globals.spawnSystem.update();
	}

	public boolean isEventHappening(Event event) {
		for (Event e : this.events) {
			if (e == event) {
				return true;
			}
		}

		return false;
	}

	public boolean isBloodMoon() {
		return this.isEventHappening(Event.bloodMoon);
	}

	public boolean isRaining() {
		return this.isEventHappening(Event.rain);
	}

	public boolean startEvent(Event event) {
		if (event.start()) {
			this.events.add(event);

			return true;
		}

		return false;
	}

	private void updateBiome() {
		if (Globals.getWorld() == null) {
			return;
		}

		int windowWidth = Gdx.graphics.getWidth();
		int windowHeight = Gdx.graphics.getHeight();
		int tww = windowWidth / Block.SIZE;
		int twh = windowHeight / Block.SIZE;
		int tcx = (int) (Camera.game.position.x - windowWidth / 2) / Block.SIZE;
		int tcy = (int) ((Camera.game.position.y - windowHeight / 2) / Block.SIZE);

		int minY = Math.max(0, tcy - 2);
		int maxY = Math.min(Globals.getWorld().getHeight() - 1, tcy + twh + 3);
		int minX = Math.max(0, tcx - 2);
		int maxX = Math.min(Globals.getWorld().getWidth() - 1, tcx + tww + 2);

		this.blockCount.clear();

		for (int y = minY; y < maxY; y++) {
			for (int x = minX; x < maxX; x++) {
				String id = Globals.getWorld().blocks.getID(x, y);

				// short count = blockCount.containsKey(id) ? blockCount.get(id) : 0;
				// blockCount.put(id, (short) (count + 1)); // FIXME: store count!
			}
		}

		this.lastBiome = this.currentBiome;

		for (Biome biome : Biomes.BIOME_CACHE) {
			boolean canBeSet = true;
			Log.error("Checking " + biome.getID());

			for (Biome.Holder holder : biome.getRequired()) {
				short count = 0;

				Log.error(biome.getID() + " required : " + holder.count);

				for (String id : holder.items) {
					// count += this.blockCount.get(id); FIXME: ruins all!
				}

				Log.error(biome.getID() + " got count : " + count);

				if (count < holder.count) {
					canBeSet = false;
					break;
				}
			}

			Log.error("Done checking " + biome.getID());

			if (canBeSet) {
				this.currentBiome = biome;
				Log.error("Biome is set to " + biome.getID());
				break;
			}
		}
	}

	public List<Event> getCurrentEvents() {
		return this.events;
	}
}