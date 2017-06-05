package org.egordorichev.lasttry.world.components;

import java.awt.Rectangle;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Camera;
import org.egordorichev.lasttry.util.Util;
import org.egordorichev.lasttry.world.World;

public class WorldLightingComponent implements Component {
    private static final int MAX_LIGHT = 16;
    private final World world;

    public WorldLightingComponent(World world) {
        this.world = world;
    }

    /**
     * Setup the light values for the entire map.
     */
    public void init() {
        for (int y = 0; y < Globals.getWorld().getHeight(); y++) {
            for (int x = 0; x < Globals.getWorld().getWidth(); x++) {
                boolean hasBlock = world.getBlockID(x, y) != ItemID.none;
                byte light = MAX_LIGHT;

                if (hasBlock) {
                    light -= calculateNeighbors(x, y);
                }

                world.setLight(x, y, light);
            }
        }
    }

    /**
     * Update blocks only near the player.
     */
    @Override
    public void update(int dt) {
        // TODO: Better system, have prebacked light from init() then update the
        // blocks as neccesary + a radius around them, regardless of how far
        // they are from the player.
        // It shouldn't be too bad of a performance hit and will make lighting
        // more consistent.
        //
        //
        // Rect containing range of blocks on screen.
        Rectangle blocksRect = Camera.getBlocksOnScreen();
        // Expand beyond camera so the player doesn't run into non-updated areas
        try {
            Util.expand(blocksRect, (int) (Globals.player.physics.getVelocity().len() * 10));
        } catch (Exception e) {
            Util.expand(blocksRect, 20);
        }
        // Calculate light for blocks on the screen
        for (int y = blocksRect.y; y < blocksRect.y + blocksRect.height; y++) {
            for (int x = blocksRect.x; x < blocksRect.x + blocksRect.width; x++) {
                boolean hasBlock = world.getBlockID(x, y) != ItemID.none;
                boolean hasWall = world.getWallID(x, y) != ItemID.none;
                byte light = MAX_LIGHT;
                if (hasBlock) {
                    light -= calculateNeighbors(x, y);
                }

                world.setLight(x, y, light);
            }
        }
    }

    @Override
    public void render() {
        // Iterate blocks on screen, draw overlay shadow
        Rectangle blocksRect = Camera.getBlocksOnScreen();
        for (int y = blocksRect.y; y < blocksRect.y + blocksRect.height; y++) {
            for (int x = blocksRect.x; x < blocksRect.x + blocksRect.width; x++) {
                renderBlock(x, y, world.getLight(x, y));
            }
        }
    }

    private int calculateNeighbors(int x, int y) {
        float neighbors = 0;
        int w = world.getWidth();
        int h = world.getHeight();
        for (int j = y - 1; j < y + 2; j++) {
            for (int i = x - 1; i < x + 2; i++) {
                if (i == x && j == y) {
                    continue;
                }

                if (i < 0 || j < 0 || i >= w || j >= h) {
                    neighbors++;
                    continue;
                }

                if (world.getBlockID(i, j) != ItemID.none) {
                    neighbors++;
                }
                if (world.getWallID(i, j) != ItemID.none) {
                    neighbors += 0.5f;
                }
            }
        }

        return (int) neighbors;
    }

    public void renderBlock(int x, int y, float value) {
        float alpha = 1F - (value / MAX_LIGHT);
        // Log.debug("A:" +alpha);
        Graphics.batch.setColor(1, 1, 1, alpha);
        Graphics.batch.draw(Assets.getTexture("Darkness"), x * Block.SIZE, y * Block.SIZE);
        Graphics.batch.setColor(1, 1, 1, 1);
    }
}
