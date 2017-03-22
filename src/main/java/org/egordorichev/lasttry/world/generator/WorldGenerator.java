package org.egordorichev.lasttry.world.generator;

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

		this.tasks.add(0, new GeneratorTask() { // Terrain
			@Override
			public void run(WorldGenerator generator) {
				int width = generator.getWorldWidth();
				int height = generator.getWorldHeight();

				double[] points = new double[width];

				int max = height - 100;
				int min = height - 250;

				for (int i = 0; i < width; i++) {
					points[i] = LastTry.random.nextInt((max - min) + 1) + min;
				}

				for (int j = 0; j < 100; j++) {
					for (int i = 1; i < width - 1; i++) {
						points[i] = (points[i - 1] + points[i + 1]) / 2;
					}
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

		this.tasks.add(new GeneratorTask() {
			@Override
			public void run(WorldGenerator generator) {
				boolean[][] terrain = new boolean[generator.getWorldWidth()][generator.getWorldHeight()];

				for (int y = 0; y < generator.getWorldHeight(); y++) {
					for (int x = 0; x < generator.getWorldWidth(); x++) {
						terrain[x][y] = LastTry.random.nextBoolean();
					}
				}

				for (int i = 0; i < 8; i++) {
					terrain = this.nextStep(generator, terrain);
				}

				for (int y = 0; y < generator.getWorldHeight(); y++) {
					for (int x = 0; x < generator.getWorldWidth(); x++) {
						if (generator.getTile(x, y) != ItemID.dirtBlock) {
							continue;
						}

						if (!terrain[x][y]) {
							generator.setTile(ItemID.none, x, y);
						} else {
							int neighbors = this.calculateNeighbors(generator, terrain, x, y);

							if (neighbors != 8) {
								generator.setTile(ItemID.grassBlock, x, y);
							}
						}
					}
				}
			}

			private boolean[][] nextStep(WorldGenerator generator, boolean[][] terrain) {
				boolean[][] newTerrain = new boolean[generator.getWorldWidth()][generator.getWorldHeight()];

				for (int y = 0; y < generator.getWorldHeight(); y++) {
					for (int x = 0; x < generator.getWorldWidth(); x++) {
						int neighbors = this.calculateNeighbors(generator, terrain, x, y);

						if (terrain[x][y]) {
							if (neighbors < 3) {
								newTerrain[x][y] = false;
							} else {
								newTerrain[x][y] = true;
							}
						} else {
							if (neighbors > 4) {
								newTerrain[x][y] = true;
							} else {
								newTerrain[x][y] = false;
							}
						}
					}
				}

				return newTerrain;
			}

			private int calculateNeighbors(WorldGenerator generator, boolean[][] terrain, int x, int y) {
				int neighbors = 0;

				for (int j = y - 1; j < y + 2; j++) {
					for (int i = x - 1; i < x + 2; i++) {
						if (i == x && j == y) {
							continue;
						}

						if (i < 0 || j < 0 || i >= generator.getWorldWidth() || j >= generator.getWorldHeight()) {
							neighbors++;
							continue;
						}

						if (terrain[i][j]) {
							neighbors++;
						}
					}
				}

				return neighbors;
			}
		});

		for (GeneratorTask task : this.tasks) {
			task.run(this);
		}

		return this.tiles;
	}

	public void setTile(int tileID, int x, int y) {
		this.tiles[x][y] = tileID; // TODO: check bounds?
	}

	public int getTile(int x, int y) {
		return this.tiles[x][y];
	}

	public int getWorldWidth() {
		return this.width;
	}

	public int getWorldHeight() {
		return this.height;
	}
}
