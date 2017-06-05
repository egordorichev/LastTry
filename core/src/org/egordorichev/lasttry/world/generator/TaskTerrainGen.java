package org.egordorichev.lasttry.world.generator;

public class TaskTerrainGen extends GeneratorTask {

    @Override
    public void run(WorldGenerator generator) {
        int width = generator.getWorldWidth();
        int height = generator.getWorldHeight();

        double[] points = new double[width];

        int max = height - 100;
        int min = height - 250;

        for (int i = 0; i < width; i++) {
            points[i] = generator.world.random.nextInt((max - min) + 1) + min;
        }

        for (int j = 0; j < 100; j++) {
            for (int i = 1; i < width - 1; i++) {
                points[i] = (points[i - 1] + points[i + 1]) / 2;
            }
        }

        for (int x = 0; x < width; x++) {
            int yMax = (int) points[x];

            for (int y = 0; y < height; y++) {
                if (y == yMax) {
                    generator.world.blocks.set("lt:grass", x, y);
                } else if (y < yMax) {
                    generator.world.blocks.set("lt:dirt", x, y);
                    generator.world.walls.set("lt:dirt_wall", x, y);
                }
            }
        }
    }
}
