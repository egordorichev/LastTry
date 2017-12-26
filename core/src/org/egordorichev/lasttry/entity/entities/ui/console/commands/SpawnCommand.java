package org.egordorichev.lasttry.entity.entities.ui.console.commands;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.Players;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.entities.creature.Creature;
import org.egordorichev.lasttry.entity.entities.ui.console.ConsoleCommand;
import org.egordorichev.lasttry.entity.entities.ui.console.UiConsole;

/**
 * Spawns mob
 */
public class SpawnCommand extends ConsoleCommand {
	public SpawnCommand() {
		super("spawn", "Spawns a creature [id] (count)");
	}

	@Override
	public void run(UiConsole console, String[] args) {
		if (args.length != 1 && args.length != 2) {
			console.print(this.getDescription());
			return;
		}

		String id = args[0];
		int count = 1;

		if (args.length == 2) {
			try {
				count = Integer.valueOf(args[1]);
			} catch (Exception exception) {
				exception.printStackTrace();
				console.print("Failed to parse args");
				return;
			}
		}

		Creature creature = Assets.creatures.create(id);

		if (creature == null) {
			console.print("Unknown creature");
			return;
		}

		if (count == 0) {
			return;
		}

		Entity player = Players.clientPlayer;

		if (player != null) {
			PositionComponent position = player.getComponent(PositionComponent.class);
			PositionComponent entityPosition = creature.getComponent(PositionComponent.class);

			entityPosition.x = position.x;
			entityPosition.y = position.y;

			Engine.addEntity(creature);
		}
	}
}