package org.egordorichev.lasttry.world.generator;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.world.World;
import org.egordorichev.lasttry.world.WorldData;

import java.util.ArrayList;

public class WorldGenerator {
	/** Stores all of the blocks and walls */
	private WorldData data;

	/** World width in blocks */
	private int width;

	/** World height in blocks */
	private int height;

	/** Generation tasks, witch will be runned */
	private ArrayList<GeneratorTask> tasks = new ArrayList<>();

	public WorldGenerator() {
		this.addSurfaceGenerator();
		this.addCavesGenerator();
	}

	/**
	 * Sets block to given ID at given position
	 * @param id block ID
	 * @param x position X coordinate
	 * @param y position Y coordinate
	 */
	public void setBlock(short id, int x, int y) {
		this.data.blocks[x + y * this.width] = id;
	}

	/**
	 * Sets wall to given ID at given position
	 * @param id wall ID
	 * @param x position X coordinate
	 * @param y position Y coordinate
	 */
	public void setWall(short id, int x, int y) {
		this.data.walls[x + y * this.width] = id;
	}

	/**
	 * Returns block ID at given position
	 * @param x position X coordinate
	 * @param y position Y coordinate
	 * @return block ID
	 */
	public short getBlockID(int x, int y) {
		return this.data.blocks[x + y * this.width];
	}

	/**
	 * Returns wall ID at given position
	 * @param x position X coordinate
	 * @param y position Y coordinate
	 * @return wall ID
	 */
	public short getWallID(int x, int y) {
		return this.data.walls[x + y * this.width];
	}

	/**
	 * Adds a new generator task to the list
	 * @param task task, to be added
	 */
	public void addTask(GeneratorTask task) {
		this.tasks.add(task);
	}

	/**
	 * Insert a new generator task at given index
	 * @param task task, to be added
	 * @param index new task index
	 */
	public void insertTask(GeneratorTask task, int index) {
		this.tasks.add(index, task);
	}

	/**
	 * Removes task with given index
	 * @param index task index to remove
	 */
	public void removeTask(int index) {
		this.tasks.remove(index);
	}

	/**
	 * Generates a new world
	 * @param width world width in blocks
	 * @param height world height in blocs
	 * @return new world data
	 */
	public WorldData generate(int width, int height) {
		this.width = width;
		this.height = height;
		this.data = new WorldData(width * height);

		this.runTasks();

		return this.data;
	}

	/**	Adds a new task, witch generates surface */
	private void addSurfaceGenerator() {
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
							generator.setBlock(ItemID.grassBlock, x, y);
						} else if (y > yMax) {
							generator.setBlock(ItemID.dirtBlock, x, y);
							generator.setWall(ItemID.dirtWall, x, y);
						} else {
							generator.setBlock(ItemID.none, x, y);
						}
					}
				}
			}
		});
	}

	/** Adds a new task, witch generates caves */
	private void addCavesGenerator() {
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
						if (generator.getBlockID(x, y) != ItemID.dirtBlock) {
							continue;
						}

						if (!terrain[x][y]) {
							generator.setBlock(ItemID.none, x, y);
						} else {
							int neighbors = this.calculateNeighbors(generator, terrain, x, y);

							if (neighbors != 8) {
								generator.setBlock(ItemID.grassBlock, x, y);
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
	}

	/**	Runs all of the tasks */
	private void runTasks() {
		for (GeneratorTask task : this.tasks) {
			task.run(this);
		}
	}

	/**
	 * Returns world width in blocks
	 * @return world width in blocks
	 */
	public int getWorldWidth() {
		return this.width;
	}

	/**
	 * Returns world height in blocks
	 * @return world height in blocks
	 */
	public int getWorldHeight() {
		return this.height;
	}
}
