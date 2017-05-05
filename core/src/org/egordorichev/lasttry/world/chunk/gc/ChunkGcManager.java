package org.egordorichev.lasttry.world.chunk.gc;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.util.Callable;
import org.egordorichev.lasttry.util.Util;

import java.util.concurrent.TimeUnit;

/**
 * Responsible for determining intervals between Chunk GC should be run and amount of chunks to be freed.
 *
 */
public class ChunkGcManager {

    boolean chunkGcInProgress = false;

    ChunkGcManager() {
        this.requestFutureChunkGc();
    }

    public synchronized void setChunkGcInProgress(boolean flag) {
        chunkGcInProgress = flag;
    }

    public boolean isChunkGcInProgress() {
        return chunkGcInProgress;
    }

    public void requestFutureChunkGc(){

        if(!isChunkGcInProgress()){

            int currentlyLoadedChunks = this.getCurrentlyLoadedChunks();

            ChunkGcCalc.ChunkGCLevel chunkGCLevel = ChunkGcCalc.calcGcLevel(currentlyLoadedChunks);

            this.scheduleChunkGc(chunkGCLevel);
        }
    }

    private int getCurrentlyLoadedChunks(){ return Globals.world.chunks.getAmountOfLoadedChunks(); }

    private void scheduleChunkGc(ChunkGcCalc.ChunkGCLevel chunkGCLevel) {

        ChunkGc chunkGcThread = new ChunkGc(chunkGCLevel);

        int futureTimeSecondsToRunChunkGc = chunkGCLevel.getTimeIntervalBeforeNextAttempt();

        Util.futureOneTimeRunInThread(new Callable() {
            @Override
            public void call() {
                chunkGcThread.performChunkGC();
            }
        }, futureTimeSecondsToRunChunkGc, TimeUnit.SECONDS);
    }
}
