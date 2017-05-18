package org.egordorichev.lasttry.world.environment;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Callable;
import org.egordorichev.lasttry.util.Camera;
import org.egordorichev.lasttry.util.Util;
import org.egordorichev.lasttry.world.WorldTime;
import org.egordorichev.lasttry.world.biome.Biome;

import java.util.ArrayList;
import java.util.Arrays;

public class Environment {
	public int[] blockCount;
	public WorldTime time;
	public ArrayList<Event> events = new ArrayList<Event>();
	public Biome currentBiome;
	public Biome lastBiome;

	public Environment() {
		this.currentBiome = Biome.forest;
		this.currentBiome.animation.fadeInFast();
		this.lastBiome = Biome.forest;

		this.blockCount = new int[ItemID.count];
		this.time = new WorldTime((byte) 8, (byte) 15);

		Util.runInThread(new Callable() {
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
			this.currentBiome.animation.renderBackground();
		}

		if (this.lastBiome != null) {
			this.lastBiome.animation.renderBackground();
		}
	}

	public void update(int dt) {
		if (Globals.world == null) {
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
		if (Globals.world == null) {
			return;
		}

		int windowWidth = Gdx.graphics.getWidth();
		int windowHeight = Gdx.graphics.getHeight();
		int tww = windowWidth / Block.SIZE;
		int twh = windowHeight / Block.SIZE;
		int tcx = (int) (Camera.game.position.x - windowWidth / 2) / Block.SIZE;
		int tcy = (int) ((Camera.game.position.y - windowHeight / 2) / Block.SIZE);

		int minY = Math.max(0, tcy - 2);
		int maxY = Math.min(Globals.world.getHeight() - 1, tcy + twh + 3);
		int minX = Math.max(0, tcx - 2);
		int maxX = Math.min(Globals.world.getWidth() - 1, tcx + tww + 2);

		Arrays.fill(this.blockCount, 0);

		for (int y = minY; y < maxY; y++) {
			for (int x = minX; x < maxX; x++) {
				this.blockCount[Globals.world.blocks.getID(x, y)] += 1;
			}
		}

		this.lastBiome = this.currentBiome;

		if (this.blockCount[ItemID.ebonstoneBlock] + this.blockCount[ItemID.vileMushroom] >= 200) {
			this.currentBiome = Biome.corruption;
		} else if (this.blockCount[ItemID.crimstoneBlock] + this.blockCount[ItemID.viciousMushroom] >= 200) {
			this.currentBiome = Biome.corruption;
		} else if (this.blockCount[ItemID.ebonsandBlock] >= 1000) {
			this.currentBiome = Biome.corruption;
		} else if (this.blockCount[ItemID.crimsandBlock] >= 1000) {
			this.currentBiome = Biome.corruption;
		} else if (this.blockCount[ItemID.sandBlock] >= 1000) {
			this.currentBiome = Biome.corruption;
		} else {
			this.currentBiome = Biome.forest;
		}
	}

	public ArrayList<Event> getCurrentEvents() {
		return this.events;
	}
}