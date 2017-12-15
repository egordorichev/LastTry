package org.egordorichev.lasttry.entity.entities.ui.console.commands;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.Players;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.entities.item.Item;
import org.egordorichev.lasttry.entity.entities.item.ItemEntity;
import org.egordorichev.lasttry.entity.entities.item.inventory.InventoryComponent;
import org.egordorichev.lasttry.entity.entities.ui.console.ConsoleCommand;
import org.egordorichev.lasttry.entity.entities.ui.console.UiConsole;

public class GiveCommand extends ConsoleCommand {
	public GiveCommand() {
		super("give", "Gives player an item [item] (count)");
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

		Item item = Assets.items.get(id);

		if (item == null) {
			console.print("Unknown item");
			return;
		}

		if (count == 0) {
			return;
		}

		Entity player = Players.clientPlayer;

		if (player != null) {
			PositionComponent position = player.getComponent(PositionComponent.class);
			Entity entity = new ItemEntity(item, count);
			PositionComponent entityPosition = entity.getComponent(PositionComponent.class);

			entityPosition.x = position.x;
			entityPosition.y = position.y;

			Engine.addEntity(entity);
		}
	}
}