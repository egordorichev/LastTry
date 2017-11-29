package org.egordorichev.lasttry.entity.entities.ui.console.commands;

import org.egordorichev.lasttry.entity.entities.ui.console.ConsoleCommand;
import org.egordorichev.lasttry.entity.entities.ui.console.UiConsole;
import org.egordorichev.lasttry.entity.entities.world.World;
import org.egordorichev.lasttry.entity.entities.world.WorldChunksComponent;

public class ChunksCommand extends ConsoleCommand {
	public ChunksCommand() {
		super("chunks", "Displays info about loaded chunks");
	}

	@Override
	public void run(UiConsole console, String[] args) {
		WorldChunksComponent chunks = World.instance.getComponent(WorldChunksComponent.class);
		console.print(chunks.loaded.size() + "/" + chunks.chunks.length + " chunks are loaded");
	}
}