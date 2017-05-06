package org.egordorichev.lasttry.world.chunk.gc;

import org.egordorichev.lasttry.Globals;

import static org.egordorichev.lasttry.world.chunk.gc.ChunkGcCalc.ChunkGCLevel.S6;

//Static methods responsible for calculating the appropriate Chunk gc level
public class ChunkGcCalc {

    public final static int MINIMUMLOADEDCHUNKS = 2;

    //Enums representing the 6 possible levels of a Chunk GC
    public enum ChunkGCLevel{

        S6("Level 6 - 15 sec interval, 5 chunk release", 90, 100, 15, 5),
        S5("Level 5 - 20 sec interval, 6 chunk release", 80, 90, 20, 6),
        S4("Level 4 - 30 sec interval, 10 chunk release", 75, 80, 30, 10),
        S3("Level 3 - 60 sec interval, 10 chunk release", 50, 75, 60, 10),
        S2("Level 2 - 90 sec interval, 5 chunk release", 25, 50, 90, 5),
        S1("Level 1 - 120 sec interval, 5 chunk release", 15, 25, 120, 5),
        S0("Level 0 - 120 sec interval, 1 chunk release", 5, 15, 120, 1),
        //Amount of Chunks not enough for Chunks GC, Chunk GC will be inactive
        SLEEP("Sleep - 120 sec interval, 0 chunk release", 0, 5, 120, 0);

        private String levelDescription;
        //Time Interval before the next GC process should be attempted again
        private int timeIntervalBeforeNextAttempt;
        private int chunksToFree;
        private int triggerPercentageHigherBounds;
        private int triggerPercentageLowerBounds;

        ChunkGCLevel(String levelDescription, int triggerPercentageLowerBounds, int triggerPercentageHigherBounds, int timeIntervalBeforeNextAttempt, int chunksToFree) {
            this.levelDescription = levelDescription;
            this.triggerPercentageHigherBounds = triggerPercentageHigherBounds;
            this.triggerPercentageLowerBounds = triggerPercentageLowerBounds;
            this.timeIntervalBeforeNextAttempt = timeIntervalBeforeNextAttempt;
            this.chunksToFree = chunksToFree;
        }

        public String getLevelDescription() {
            return this.levelDescription;
        }

        public int getTimeIntervalBeforeNextAttempt() {
            return this.timeIntervalBeforeNextAttempt;
        }

        public int getChunksToFree() {
            return this.chunksToFree;
        }

        public int getTriggerPercentageHigherBounds() {
            return this.triggerPercentageHigherBounds;
        }

        public int getTriggerPercentageLowerBounds() {
            return this.triggerPercentageLowerBounds;
        }
    }

    //Returns appropriate chunk gc level based on amount of loaded chunks in memory
    public static synchronized ChunkGCLevel calcGcLevel(int currentlyLoadedChunks) {

        int filledPercentOfChunks = getFilledChunksPercent();

        //todo rethink this, must be a better exception to throw
        if(filledPercentOfChunks>100){
            throw new IllegalArgumentException("Filled percentage of chunks, is greater than 100");
        }

        //When percentage of chunks is full, immediately return S6;
        if(filledPercentOfChunks==100){
            return S6;
        }


        ChunkGCLevel[] availGcLevels = ChunkGCLevel.values();

        ChunkGCLevel appropriateLevel = null;

        for(ChunkGCLevel chunkGCLevel: availGcLevels)
        {
            int higherBoundTriggerPercent = chunkGCLevel.getTriggerPercentageHigherBounds();
            int lowerBoundTriggerPercent = chunkGCLevel.getTriggerPercentageLowerBounds();

            //todo right now if percentage of chunks is 100, it is not caught here but caught higher up
            if(filledPercentOfChunks<higherBoundTriggerPercent&&filledPercentOfChunks>=lowerBoundTriggerPercent){
                appropriateLevel = chunkGCLevel;
            }
        }

        return appropriateLevel;
    }

    //Calculates percentage of chunks that are filled out of max chunks for world
    private static int getFilledChunksPercent() {
        double maxChunksSize = Globals.world.getSize().getMaxChunks();

        double loadedChunksSize = Globals.world.chunks.getImmutableLoadedChunks().size();

        if(loadedChunksSize<=MINIMUMLOADEDCHUNKS){
            return 0;
        }

        double loadedChunksOfTotalFraction = loadedChunksSize/maxChunksSize;

        double loadedChunksOfTotalPercentage = loadedChunksOfTotalFraction * 100;

        return (int)loadedChunksOfTotalPercentage;
    }

}
