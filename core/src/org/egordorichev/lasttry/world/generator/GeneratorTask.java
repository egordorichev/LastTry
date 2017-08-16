package org.egordorichev.lasttry.world.generator;

public abstract class GeneratorTask {
    public abstract void run(WorldGenerator generator);
    
    protected int calculateNeighbors(WorldGenerator generator, boolean[][] terrain, int x, int y) {
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

                if (terrain[i][j]) {
                    neighbors++;
                }
            }
        }

        return neighbors;
    }
}