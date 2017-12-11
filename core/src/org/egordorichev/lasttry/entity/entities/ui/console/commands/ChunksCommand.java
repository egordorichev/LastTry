package org.egordorichev.lasttry.entity.entities.ui.console.commands;

import org.egordorichev.lasttry.entity.entities.ui.console.ConsoleCommand;
import org.egordorichev.lasttry.entity.entities.ui.console.UiConsole;
import org.egordorichev.lasttry.entity.entities.world.ChunksComponent;
import org.egordorichev.lasttry.entity.entities.world.World;

public class ChunksCommand extends ConsoleCommand {
	public ChunksCommand() {
		super("chunks", "Displays info about loaded chunks");
	}

	@Override
	public void run(UiConsole console, String[] args) {
		ChunksComponent chunks = World.instance.getComponent(ChunksComponent.class);
		console.print(chunks.loaded.size() + "/" + chunks.chunks.length + " chunks are loaded");
	}
}