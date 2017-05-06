package org.egordorichev.lasttry.world.chunk.gc;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.util.Callable;
import org.egordorichev.lasttry.util.Log;
import org.egordorichev.lasttry.util.Util;

import java.util.concurrent.TimeUnit;

/**
 * Responsible for determining intervals between Chunk GC should be run and amount of chunks to be freed.
 *
 */
//todo re-evaluate synchronized methods
//todo on first run, interval should be set to 10 seconds to allow game to load previous chunks?
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

    private synchronized int getCurrentlyLoadedChunks(){ return Globals.world.chunks.getImmutableLoadedChunks().size(); }

    private synchronized void scheduleChunkGc(ChunkGcCalc.ChunkGCLevel chunkGCLevel) {

        Log.debug("Level of chunk gc to be scheduled is: "+chunkGCLevel.getLevelDescription());

        int futureTimeSecondsToRunChunkGc = chunkGCLevel.getTimeIntervalBeforeNextAttempt();

        Util.futureOneTimeRunInThread(new Callable() {
            @Override
            public void call() {

                Log.debug("Chunk GC time limit has expired");

                //On wakeup, we run a chunk gc immediately based on a ChunkGC level we receive based on the current loaded chunks level
                ChunkGcCalc.ChunkGCLevel chunkGCLevelForCurrentGc = ChunkGcCalc.calcGcLevel(Globals.world.chunks.getImmutableLoadedChunks().size());

                //If the ChunkGcLevel is sleep, we set a delay for the amount of time and simply request another chunkgc on wakeup
                if(chunkGCLevelForCurrentGc== ChunkGcCalc.ChunkGCLevel.SLEEP){
                    Log.debug("Chunk GC level is sleep");
                    Globals.chunkGcManager.scheduleChunkGc(chunkGCLevelForCurrentGc);
                }else{
                    ChunkGc chunkGcThread = new ChunkGc(chunkGCLevelForCurrentGc);
                    Log.debug("Chunk GC level is: "+chunkGCLevelForCurrentGc.getLevelDescription());
                    chunkGcThread.performChunkGC();
                }
            }
        }, futureTimeSecondsToRunChunkGc, TimeUnit.SECONDS);
    }


}
