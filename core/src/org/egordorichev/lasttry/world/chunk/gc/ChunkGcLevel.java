package org.egordorichev.lasttry.world.chunk.gc;

/**
 * Created by Admin on 06/05/2017.
 */
public class ChunkGcLevel {

    public enum ChunkGcLevelBounds{
        S6("Level 6 - 15 sec interval, 5 chunk release", 90, 100),
        S5("Level 5 - 20 sec interval, 6 chunk release", 80, 90),
        S4("Level 4 - 30 sec interval, 10 chunk release", 75, 80),
        S3("Level 3 - 60 sec interval, 10 chunk release", 50, 75),
        S2("Level 2 - 90 sec interval, 5 chunk release", 25, 50),
        S1("Level 1 - 120 sec interval, 5 chunk release", 15, 25),
        S0("Level 0 - 120 sec interval, 1 chunk release", 5, 15),
        //Amount of Chunks not enough for Chunks GC, Chunk GC will be inactive
        SLEEP("Sleep - 120 sec interval, 0 chunk release", 0, 5);

        private String levelDescription;
        //Time Interval before the next GC process should be attempted again
        private int triggerPercentageHigherBounds;
        private int triggerPercentageLowerBounds;

        ChunkGcLevelBounds(String levelDescription, int triggerPercentageLowerBounds, int triggerPercentageHigherBounds) {
            this.levelDescription = levelDescription;
            this.triggerPercentageHigherBounds = triggerPercentageHigherBounds;
            this.triggerPercentageLowerBounds = triggerPercentageLowerBounds;

        }

        public String getLevelDescription() {
            return this.levelDescription;
        }

        public int getTriggerPercentageHigherBounds() {
            return this.triggerPercentageHigherBounds;
        }

        public int getTriggerPercentageLowerBounds() {
            return this.triggerPercentageLowerBounds;
        }

    }



}
