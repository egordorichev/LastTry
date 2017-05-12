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

	public UiChat() {
		super(new Rectangle(300, 0, WIDTH, HEIGHT), Origin.BOTTOM_RIGHT);
	}

	@Override
	public void addComponents() {
		this.input = new UiTextInput(new Rectangle(300, 20, 400, 20), Origin.BOTTOM_LEFT) {
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

		for (int i = 0; i < this.lines.size(); i++) {
			Assets.f18.draw(Graphics.batch, this.lines.get(i).text, 300, 40 + i * 20);
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			this.close();
		}
	}

	public void eval(String text) {
		String[] parts = this.split(text.trim());

		switch (parts[0]) {
			case "/help": this.print("Please, visit dev chat for more."); break;
			case "/give":
				if (parts.length != 2 && parts.length != 3) {
					this.print("/give [item id] (count)");
				} else {
					Item item = Item.fromID(Integer.valueOf(parts[1]));
					int count = parts.length == 2 ? 1 : Integer.valueOf(parts[2]);

					if (item == null) {
						this.print("Unknown item");
					} else {
						Globals.player.inventory.add(new ItemHolder(item, count));
					}
				}
			break;
			case "/chunks":
				if (parts.length == 1) {
					this.print("/chunks [gc / list]");
				} else {
					switch (parts[1]) {
						case "gc":
							Globals.chunkGcManager.scheduleCustomIntervalChunkGcThread(0);
							this.print("Running instant chunk GC...");
						break;
						case "list":
							this.print(Globals.chunkGcManager.getCurrentlyLoadedChunks() + " chunks is loaded, maximum is: "+Globals.world.getSize().getMaxChunks());
						break;
						default: this.print("/chunks [gc / list]");
					}
				}
			break;
			case "/spawn":
				if (parts.length != 2 && parts.length != 3) {
					this.print("/spawn [enemy name] (count)");
				} else {
					String name = parts[1].replace("\"", "");
					Enemy enemy = Enemies.create(name);
					int count = parts.length == 2 ? 1 : Integer.valueOf(parts[2]);

					if (enemy == null) {
						this.print("Unknown enemy");
					} else {
						for (int i = 0; i < count; i++) {
							Globals.entityManager.spawnEnemy(name, (int) Globals.player.physics.getX(), (int) Globals.player.physics.getY());
						}
					}
				}
			break;
			default: this.print("Unknown command: " + parts[0]); break;
		}
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