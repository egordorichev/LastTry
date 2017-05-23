package org.egordorichev.lasttry.world.generator;

import org.egordorichev.lasttry.item.ItemID;

public class TaskCaveGenSimplex extends GeneratorTask {

    @Override
    public void run(WorldGenerator generator) {
        int antiCaveBiasHeight = 820;
        int octaves = 4;
        float roughness = 0.5f;
        float scale = 1f / 20f;
        float cut = 0.0f;
        boolean[][] solidMap = new boolean[generator.getWorldWidth()][generator.getWorldHeight()];

        for (int y = 0; y < generator.getWorldHeight(); y++) {
            for (int x = 0; x < generator.getWorldWidth(); x++) {
                // Get noise value at the coordinates
                float f = SimplexNoise.octavedNoise(x, y, octaves, roughness, scale);
                if (y >= antiCaveBiasHeight) {
                    // Average the value with a negative number to lessen the
                    // effect of cave generation above (Y = antiCaveBiasHeight)
                    f -= Math.abs(Math.sin(((float) x) / 30f) / 1.5f);
                    f /= 4;
                }
                // Solidity is determined if the noise (which is [-1,1] -
                // antiCaveBias) is less than a given value. If cut is 0.5f, 75%
                // of the land should end up being solid, 25% will become caves.
                boolean isSolid = f <= cut;
                solidMap[x][y] = isSolid;
            }
        }

        for (int y = 0; y < generator.getWorldHeight(); y++) {
            for (int x = 0; x < generator.getWorldWidth(); x++) {
                // Skip existing air blocks
                if (generator.world.blocks.getID(x, y) == ItemID.none) {
                    continue;
                }
                // Insert caves
                if (!solidMap[x][y]) {
                    generator.world.blocks.set(ItemID.none, x, y);
                } else {
                    // For non caves, apply grass borders.
                    // Cut off any blocks with too few neighbors to prevent
                    // the some of the floating blocks.
                    int neighbors = this.calculateNeighbors(generator, solidMap, x, y);
                    if (neighbors <= 3) {
                        generator.world.blocks.set(ItemID.none, x, y);
                    } else if (neighbors != 8) {
                        generator.world.blocks.set(ItemID.grassBlock, x, y);
                    }
                }
            }
        }
    }

    private int calculateNeighbors(WorldGenerator generator, boolean[][] solidMap, int x, int y) {
        int neighbors = 0;
        int w = generator.getWorldWidth();
        int h = generator.getWorldHeight();
        for (int j = y - 1; j < y + 2; j++) {
            for (int i = x - 1; i < x + 2; i++) {
                if (i == x && j == y) {
                    continue;
                }

                if (i < 0 || j < 0 || i >= w || j >= h) {
                    neighbors++;
                    continue;
                }

                if (solidMap[i][j]) {
                    neighbors++;
                }
            }
        }

        return neighbors;
    }

}
