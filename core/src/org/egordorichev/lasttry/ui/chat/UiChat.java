package org.egordorichev.lasttry.ui.chat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.enemy.Enemies;
import org.egordorichev.lasttry.entity.enemy.Enemy;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.state.GamePlayState;
import org.egordorichev.lasttry.ui.UiPanel;
import org.egordorichev.lasttry.ui.UiTextInput;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UiChat extends UiPanel {
	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;

	private boolean open = false;
	private UiTextInput input;
	private ArrayList<ChatLine> lines = new ArrayList<ChatLine>();
	private ArrayList<ChatCommand> commands = new ArrayList<ChatCommand>();

	public UiChat() {
		super(new Rectangle(10, 0, WIDTH, HEIGHT), Origin.BOTTOM_LEFT);
		
		this.commands.add(new ChatCommand("/help") {
			@Override
			public void call(String[] args) {
				print("Please, visit dev chat for more.");
			}
		});
		
		this.commands.add(new ChatCommand("/give") {
			@Override
			public void call(String[] args) {
				if (args.length != 2 && args.length != 3) {
					print("/give [item id] (count)");
				} else {
					Item item = Item.fromID(Integer.valueOf(args[1]));
					int count = args.length == 2 ? 1 : Integer.valueOf(args[2]);

					if (item == null) {
						print("Unknown item");
					} else {
						Globals.player.inventory.add(new ItemHolder(item, count));
					}
				}			
			}
		});

		this.commands.add(new ChatCommand("/spawn") {
			@Override
			public void call(String[] args) {
				if (args.length != 2 && args.length != 3) {
					print("/spawn [enemy name] (count)");
				} else {
					String name = args[1].replace("\"", "");
					Enemy enemy = Enemies.create(name);
					int count = args.length == 2 ? 1 : Integer.valueOf(args[2]);

					if (enemy == null) {
						print("Unknown enemy");
					} else {
						for (int i = 0; i < count; i++) {
							Globals.entityManager.spawnEnemy(name, (int) Globals.player.physics.getX(), (int) Globals.player.physics.getY());
						}
					}
				}
			}
		});

		this.commands.add(new ChatCommand("/chunks") {
			@Override
			public void call(String[] args) {
				if (args.length == 1) {
					print("/chunks [gc / list]");
				} else {
					switch (args[1]) {
						case "gc":
							Globals.chunkGcManager.scheduleCustomIntervalChunkGcThread(0);
							print("Running instant chunk GC...");
						break;
						case "list":
							print(Globals.chunkGcManager.getCurrentlyLoadedChunks() + " chunks is loaded, maximum is: " + Globals.world.getSize().getMaxChunks());
						break;
						default: print("/chunks [gc / list]");
					}
				}
			}
		});
	}

	@Override
	public void addComponents() {
		this.input = new UiTextInput(new Rectangle(10, 20, 400, 20), Origin.BOTTOM_LEFT) {
			@Override
			public void onEnter() {
				eval(getText());
				clear();
			}
		};

		this.input.setFont(Assets.f18);
		this.input.setIgnoreInput(true);

		this.add(this.input);
	}

	@Override
	public void render() {
		if (this.open) {
			super.render();
		}

		for (int i = this.lines.size() - 1; i >= 0; i--) {
			ChatLine line = this.lines.get(i);

			if (line.shouldBeRemoved()) {
				this.lines.remove(i);
			}

			Assets.f18.draw(Graphics.batch, line.text, 10, 40 + i * 20);
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			this.close();
		}
	}

	public void eval(String text) {
		String[] args = this.split(text.trim());

		for (ChatCommand command : this.commands) {
			if (command.getName().equals(args[0])) {
				command.call(args);
				return;
			}
		}
			
		this.print("Unknown command: " + args[0]);
	}

	private String[] split(String string) {
		ArrayList<String> list = new ArrayList<String>();
		Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(string);

		while (m.find()) {
			list.add(m.group(1));
		}

		return list.toArray(new String[list.size()]);
	}

	public void print(String text) {
		this.lines.add(0, new ChatLine(text));
	}

	public void open() {
		this.open = true;
		this.input.setIgnoreInput(false);
		this.input.type("/");

		GamePlayState.stop();
	}

	public void close() {
		this.open = false;
		this.input.setIgnoreInput(true);
		this.input.clear();

		GamePlayState.play();
	}

	public boolean isOpen() {
		return this.open;
	}
}