package org.egordorichev.lasttry.world.gen;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.ItemID;

import java.util.ArrayList;

public class WorldGenerator {
	private int tiles[][];
	private int width;
	private int height;
	private ArrayList<GeneratorTask> tasks = new ArrayList<>();

	public void addTask(GeneratorTask task) {
		this.tasks.add(task);
	}

	public void insertTask(GeneratorTask task, int index) {
		this.tasks.add(index, task);
	}

	public void removeTask(int index) {
		this.tasks.remove(index);
	}

	public int[][] generate(int width, int height) { // Must be power of two
		this.width = width;
		this.height = height;
		this.tiles = new int[this.width][this.height];

		this.tasks.add(0, new GeneratorTask() {
			@Override
			public void run(WorldGenerator generator) {
				int width = generator.getWorldWidth();
				int height = generator.getWorldHeight();

				double displace = height / 4;
				double roughness = 0.45;
				double[] points = new double[width + 1];
				double power = Math.pow(2, Math.ceil(Math.log(width) / (Math.log(2))));

				points[0] = 200 + ((displace) * LastTry.random.nextDouble() * 2) - displace;
				points[(int) power] = points[0];

				displace *= roughness;

				for(int i = 1; i < power; i *= 2) {
					for(int j = (int) (power / i) / 2; j < power; j+= power / i){
						points[j] = ((points[j - (int) (power / i) / 2] + points[j + (int) (power / i) / 2]) / 2);
						points[j] += ((displace) * LastTry.random.nextDouble() * 2) - displace;
					}

					displace *= roughness;
				}

				for (int x = 0; x < width; x++) {
					int yMax = (int) points[x];

					for (int y = 0; y < height; y++) {
						if (y == yMax) {
							generator.setTile(ItemID.grassBlock, x, y);
						} else if (y > yMax) {
							generator.setTile(ItemID.dirtBlock, x, y);
						} else {
							generator.setTile(ItemID.none, x, y);
						}
					}
				}
			}
		});

		for(GeneratorTask task : this.tasks) {
			task.run(this);
		}

		return this.tiles;
	}

	public void setTile(int tileID, int x, int y) {
		this.tiles[x][y] = tileID; // TODO: check bounds?
	}

	public int getWorldWidth() {
		return this.width;
	}

	public int getWorldHeight() {
		return this.height;
	}
}
