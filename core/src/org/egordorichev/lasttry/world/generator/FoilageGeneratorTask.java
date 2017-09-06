package org.egordorichev.lasttry.world.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FoilageGeneratorTask extends GeneratorTask {
    private static final Logger logger = LoggerFactory.getLogger(FoilageGeneratorTask.class);
    @Override
    public void run(WorldGenerator generator) {
        logger.info("Generating foilage");

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
