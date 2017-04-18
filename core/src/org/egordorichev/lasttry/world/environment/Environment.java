package org.egordorichev.lasttry.world.environment;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Callable;
import org.egordorichev.lasttry.util.Util;
import org.egordorichev.lasttry.world.WorldTime;
import org.egordorichev.lasttry.world.biome.Biome;

import java.util.ArrayList;
import java.util.Arrays;

public class Environment {
    /**
     * Block count, displayed on the screen
     */
    public int[] blockCount;
    /**
     * Time in the world
     */
    public WorldTime time;
    /**
     * Events, that currently happen in the world
     */
    public ArrayList<Event> events = new ArrayList<Event>();
    /**
     * Current biome, player is
     */
    private Biome currentBiome = null;
    /**
     * Previous biome
     */
    private Biome lastBiome = null;

    public Environment() {
        this.blockCount = new int[ItemID.count];
        this.time = new WorldTime((byte) 8, (byte) 15);

        Util.runInThread(new Callable() {
            @Override
            public void call() {
                updateBiome();
            }
        }, 1);
    }

    /**
     * Renders current biome and the sky
     */
    public void render() {
        for (int i = 0; i < Gdx.graphics.getWidth() / 48 + 1; i++) {
            LastTry.batch.draw(Graphics.skyTexture, i * 48, 0);
        }

        if (this.currentBiome != null) {
            this.currentBiome.renderBackground();
        }

        if (this.lastBiome != null) {
            this.lastBiome.renderBackground();
        }
    }

    /**
     * Updates biomes and spawns mobs
     *
     * @param dt The milliseconds passed since the last update.
     */
    public void update(int dt) {
	    if (LastTry.world == null) {
		    return;
	    }

        this.time.update();

        if (this.currentBiome != null && !this.currentBiome.fadeInIsDone()) {
            this.currentBiome.fadeIn();
        }

        if (this.lastBiome != null && this.lastBiome != this.currentBiome &&
                !this.lastBiome.fadeOutIsDone()) {

            this.lastBiome.fadeOut();
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

        //LastTry.spawnSystem.calcArea();

        LastTry.spawnSystem.debugEnemiesInActiveArea();
    }

    /**
     * Returns true, if event with given name is happening
     *
     * @param event event to lookup
     * @return if event with given name is happening
     */
    public boolean isEventHappening(Event event) {
        for (Event e : this.events) {
            if (e == event) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return if blood moon happens
     */
    public boolean isBloodMoon() {
        return this.isEventHappening(Event.bloodMoon);
    }

    /**
     * @return if it rains
     */
    public boolean isRaining() {
        return this.isEventHappening(Event.rain);
    }

    /**
     * Try to start an event
     *
     * @param event event to start
     * @return if it is started
     */
    public boolean startEvent(Event event) {
        if (event.start()) {
            this.events.add(event);

            return true;
        }

        return false;
    }

    /**
     * Updates current biome
     */
    private void updateBiome() {
        if (LastTry.world == null) {
            return;
        }

        int windowWidth = Gdx.graphics.getWidth();
        int windowHeight = Gdx.graphics.getHeight();
        int tww = windowWidth / Block.TEX_SIZE;
        int twh = windowHeight / Block.TEX_SIZE;
        int tcx = (int) (LastTry.camera.position.x - windowWidth / 2) / Block.TEX_SIZE;
        int tcy = (int) (LastTry.world.getHeight() - (LastTry.camera.position.y + windowHeight / 2)
                / Block.TEX_SIZE);

        int minY = Math.max(0, tcy - 20);
        int maxY = Math.min(LastTry.world.getHeight() - 1, tcy + twh + 23);
        int minX = Math.max(0, tcx - 20);
        int maxX = Math.min(LastTry.world.getWidth() - 1, tcx + tww + 20);

        Arrays.fill(this.blockCount, 0);

        for (int y = minY; y < maxY; y++) {
            for (int x = minX; x < maxX; x++) {
                this.blockCount[LastTry.world.getBlockID(x, y)] += 1;
            }
        }

        this.lastBiome = this.currentBiome;

        if (this.blockCount[ItemID.ebonstoneBlock] + this.blockCount[ItemID.vileMushroom] >= 200) {
            this.currentBiome = Biome.corruption;
        } else if (this.blockCount[ItemID.crimstoneBlock] + this.blockCount[ItemID.viciousMushroom] >= 200) {
            this.currentBiome = Biome.crimson;
        } else if (this.blockCount[ItemID.ebonsandBlock] >= 1000) {
            this.currentBiome = Biome.corruptDesert;
        } else if (this.blockCount[ItemID.crimsandBlock] >= 1000) {
            this.currentBiome = Biome.crimsonDesert;
        } else if (this.blockCount[ItemID.sandBlock] >= 1000) {
            this.currentBiome = Biome.desert;
        } else {
            this.currentBiome = Biome.forest;
        }
    }

    public Biome getCurrentBiome() {
        return this.currentBiome;
    }

    /**
     * Method returns currently occurring events with a 1 tick delay.
     * Meaning the method does NOT guarantee that all events in the list may be currently occurring.
     * Events may have stopped.  Maximum delay is 1 tick.
     * Update method is called 60 times a second, in update method events are checked.
     *
     * @return currently occuring events, may be wrong due to a 1 tick delay
     */
    public ArrayList<Event> getCurrentEvents() {return this.events;}

}