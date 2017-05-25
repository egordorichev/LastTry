package org.egordorichev.lasttry.world.chunk.gc;

import org.egordorichev.lasttry.Globals;

import static org.egordorichev.lasttry.world.chunk.gc.ChunkGcCalc.ChunkGCLevel.S6;

//Static methods responsible for calculating the appropriate Chunk gc level
public class ChunkGcCalc {

    //todo Right now minimum loaded chunks is being activated when loaded chunks size is at least '3' not just '2'
    public final static int MINIMUMLOADEDCHUNKS = 2;

    //Enums representing the 6 possible levels of a Chunk GC
    public enum ChunkGCLevel {

        S6(ChunkGcLevelConstants.ChunkGcLevelBounds.S6, 15, 5),
        S5(ChunkGcLevelConstants.ChunkGcLevelBounds.S5, 20, 6),
        S4(ChunkGcLevelConstants.ChunkGcLevelBounds.S4, 30, 10),
        S3(ChunkGcLevelConstants.ChunkGcLevelBounds.S3, 60, 10),
        S2(ChunkGcLevelConstants.ChunkGcLevelBounds.S2, 90, 5),
        S1(ChunkGcLevelConstants.ChunkGcLevelBounds.S1, 120, 5),
        S0(ChunkGcLevelConstants.ChunkGcLevelBounds.S0, 120, 1),
	    //DEV(ChunkGcLevelConstants.ChunkGcLevelBounds.DEV, 0, 100),
	    //Amount of Chunks not enough for Chunks GC, Chunk GC will be inactive
        SLEEP(ChunkGcLevelConstants.ChunkGcLevelBounds.SLEEP, 120, 0);

        private ChunkGcLevelConstants.ChunkGcLevelBounds  chunkGcLevelDesc;
        //Time Interval before the next GC process should be attempted again
        private int timeIntervalBeforeNextAttempt;
        private int chunksToFree;

        ChunkGCLevel(ChunkGcLevelConstants.ChunkGcLevelBounds chunkDesc, int timeIntervalBeforeNextAttempt, int chunksToFree) {
            this.chunkGcLevelDesc = chunkDesc;
            this.timeIntervalBeforeNextAttempt = timeIntervalBeforeNextAttempt;
            this.chunksToFree = chunksToFree;
        }

        public ChunkGcLevelConstants.ChunkGcLevelBounds getLevelDescription() {
            return this.chunkGcLevelDesc;
        }

        public int getTimeIntervalBeforeNextAttempt() {
            return this.timeIntervalBeforeNextAttempt;
        }

        public int getChunksToFree() {
            return this.chunksToFree;
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

        return getAppropriateLevel(filledPercentOfChunks);

    }

    private static synchronized ChunkGCLevel getAppropriateLevel(int filledPercentOfChunks) {

        ChunkGCLevel[] availGcLevels = ChunkGCLevel.values();

        ChunkGCLevel appropriateLevel = null;

        for(ChunkGCLevel chunkGCLevel: availGcLevels)
        {
            int higherBoundTriggerPercent = chunkGCLevel.getLevelDescription().getTriggerPercentageHigherBounds();
            int lowerBoundTriggerPercent = chunkGCLevel.getLevelDescription().getTriggerPercentageLowerBounds();

            //todo right now if percentage of chunks is 100, it is not caught here but caught higher up
            if(filledPercentOfChunks<higherBoundTriggerPercent&&filledPercentOfChunks>=lowerBoundTriggerPercent){
                appropriateLevel = chunkGCLevel;
            }
        }

        return appropriateLevel;
    }


    //Calculates percentage of chunks that are filled out of max chunks for world
    private static int getFilledChunksPercent() {
        double maxChunksSize = Globals.getWorld().getSize().getMaxChunks();

        double loadedChunksSize = Globals.getWorld().getChunks().getImmutableLoadedChunks().size();

        if(loadedChunksSize<=MINIMUMLOADEDCHUNKS){
            return 0;
        }

        double loadedChunksOfTotalFraction = loadedChunksSize/maxChunksSize;

        double loadedChunksOfTotalPercentage = loadedChunksOfTotalFraction * 100;

        return (int)loadedChunksOfTotalPercentage;
    }

}
