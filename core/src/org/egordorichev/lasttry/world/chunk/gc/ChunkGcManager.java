package org.egordorichev.lasttry.world.chunk.gc;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.util.Callable;
import org.egordorichev.lasttry.util.Log;
import org.egordorichev.lasttry.util.Util;

import java.util.concurrent.TimeUnit;

/**
 * Responsible for determining intervals between Chunk GC should be run and amount of chunks to be freed.
 */
//todo re-evaluate synchronized methods
//todo on first run, interval should be set to 10 seconds to allow game to load previous chunks?
//todo use synchronized block

public class ChunkGcManager {
    boolean chunkGcInProgress = false;

    public ChunkGcManager() {
        this.requestFutureChunkGc();
    }

    public synchronized void setChunkGcInProgress(boolean flag) {
        chunkGcInProgress = flag;
    }

    public boolean isChunkGcInProgress() {
        return chunkGcInProgress;
    }

    public synchronized void requestFutureChunkGc(){

        if(!isChunkGcInProgress()){

            Log.debug("Chunk GC is not in progress");

            int currentlyLoadedChunks = this.getCurrentlyLoadedChunks();

            Log.debug("Loaded chunks is: "+currentlyLoadedChunks);

            ChunkGcCalc.ChunkGCLevel chunkGCLevel = ChunkGcCalc.calcGcLevel(currentlyLoadedChunks);

            this.scheduleChunkGc(chunkGCLevel);
        }
    }

    public synchronized int getCurrentlyLoadedChunks() {
    	return Globals.world.chunks.getImmutableLoadedChunks().size();
    }

    public synchronized void scheduleChunkGc(ChunkGcCalc.ChunkGCLevel chunkGCLevel) {
        Log.debug("Level of chunk gc to be scheduled is: "+chunkGCLevel.getLevelDescription());

        int futureTimeSecondsToRunChunkGc = chunkGCLevel.getTimeIntervalBeforeNextAttempt();
        this.scheduleFutureChunkGcThread(chunkGCLevel);
    }

    public synchronized void scheduleFutureChunkGcThread(ChunkGcCalc.ChunkGCLevel chunkGCLevel) {
        Util.futureOneTimeRunInThread(new Callable() {
            @Override
            public void call() {

                Log.debug("Chunk GC thread has started, time limit has expired");

                    //On wakeup, we run a chunk gc immediately based on a ChunkGC level we receive based on the current loaded chunks level
                    ChunkGcCalc.ChunkGCLevel chunkGCLevelForCurrentGc = ChunkGcCalc.calcGcLevel(Globals.world.chunks.getImmutableLoadedChunks().size());

                    ChunkGc chunkGcThread = new ChunkGc(chunkGCLevelForCurrentGc);
                    chunkGcThread.onWakeUp();


            }
        }, chunkGCLevel.getTimeIntervalBeforeNextAttempt(), TimeUnit.SECONDS);
    }

    public synchronized void scheduleCustomIntervalChunkGcThread(int timeIntervalDelay) {
        Util.futureOneTimeRunInThread(new Callable() {
            @Override
            public void call() {

                Log.debug("Chunk GC thread has started, time limit has expired");

                //On wakeup, we run a chunk gc immediately based on a ChunkGC level we receive based on the current loaded chunks level
                ChunkGcCalc.ChunkGCLevel chunkGCLevelForCurrentGc = ChunkGcCalc.calcGcLevel(Globals.world.chunks.getImmutableLoadedChunks().size());

                ChunkGc chunkGcThread = new ChunkGc(chunkGCLevelForCurrentGc);
                chunkGcThread.onWakeUp();


            }
        }, timeIntervalDelay, TimeUnit.SECONDS);
    }

}
