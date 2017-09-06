package org.egordorichev.lasttry.world.generator;

import org.egordorichev.lasttry.util.Log;

public class FoilageGeneratorTask extends GeneratorTask {

    @Override
    public void run(WorldGenerator generator) {
		Log.info("Generating foilage");

        // TODO: Replace blocks with trees and plants
        // Small plants
        for (int x = 0; x < generator.getWorldWidth(); x += generator.world.random.nextInt(5)) {
            int y = generator.getHighest(x);
            generator.world.blocks.set("lt:ash", x, y);
        }

        // Larger plants
        for (int x = 0; x < generator.getWorldWidth(); x += generator.world.random.nextInt(10)) {
            int y = generator.getHighest(x);
            generator.world.blocks.set("lt:ash", x, y);
        }
    }

}
