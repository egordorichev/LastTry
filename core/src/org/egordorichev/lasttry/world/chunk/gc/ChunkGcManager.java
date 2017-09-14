package org.egordorichev.lasttry.world.chunk.gc;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.util.Callable;
import org.egordorichev.lasttry.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Responsible for determining intervals between Chunk GC should be run and
 * amount of chunks to be freed.
 */
// todo re-evaluate synchronized methods
// todo on first run, interval should be set to 10 seconds to allow game to load
// previous chunks?
// todo use synchronized block

public class ChunkGcManager {
	private static final Logger logger = LoggerFactory.getLogger(ChunkGcManager.class);
	boolean chunkGcInProgress = false;

	public ChunkGcManager() {
		this.requestFutureChunkGc();
	}

	public synchronized void requestFutureChunkGc() {
		if (!isChunkGcInProgress()) {
			logger.debug("Chunk GC is not in progress");
			int currentlyLoadedChunks = this.getCurrentlyLoadedChunks();
			logger.debug("Loaded chunks is: " + currentlyLoadedChunks);
			ChunkGcCalc.ChunkGCLevel chunkGCLevel = ChunkGcCalc.calcGcLevel(currentlyLoadedChunks);
			this.scheduleChunkGc(chunkGCLevel);
		}
	}

	public synchronized void scheduleChunkGc(ChunkGcCalc.ChunkGCLevel chunkGCLevel) {
		logger.debug("Level of chunk gc to be scheduled is: " + chunkGCLevel.getLevelDescription());
		// int futureTimeSecondsToRunChunkGc =
		// chunkGCLevel.getTimeIntervalBeforeNextAttempt();
		this.scheduleFutureChunkGcThread(chunkGCLevel);
	}

	public synchronized void scheduleFutureChunkGcThread(ChunkGcCalc.ChunkGCLevel chunkGCLevel) {
		Util.futureOneTimeRunInThread(new GCTask(), chunkGCLevel.getTimeIntervalBeforeNextAttempt(), TimeUnit.SECONDS);
	}

	public synchronized void scheduleCustomIntervalChunkGcThread(int timeIntervalDelay) {
		Util.futureOneTimeRunInThread(new GCTask(), timeIntervalDelay, TimeUnit.SECONDS);
	}

	public synchronized int getCurrentlyLoadedChunks() {
		return Globals.getWorld().chunks.getImmutableLoadedChunks().size();
	}

	public synchronized void setChunkGcInProgress(boolean flag) {
		chunkGcInProgress = flag;
	}

	public boolean isChunkGcInProgress() {
		return chunkGcInProgress;
	}

	private static class GCTask extends Callable {
		@Override
		public void call() {
			logger.debug("Chunk GC thread has started, time limit has expired");
			// On wakeup, we run a chunk gc immediately based on a ChunkGC level
			// we receive based on the current loaded chunks level
			ChunkGcCalc.ChunkGCLevel chunkGCLevelForCurrentGc = ChunkGcCalc
					.calcGcLevel(Globals.getWorld().chunks.getImmutableLoadedChunks().size());
			ChunkGc chunkGcThread = new ChunkGc(chunkGCLevelForCurrentGc);
			chunkGcThread.onWakeUp();
		}
	}
}
