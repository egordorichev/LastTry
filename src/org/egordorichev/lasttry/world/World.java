package org.egordorichev.lasttry.world;

import org.egordorichev.lasttry.Camera;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.Enemy;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.item.Block;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.Wall;
import org.egordorichev.lasttry.util.FileReader;
import org.egordorichev.lasttry.util.FileWriter;
import org.egordorichev.lasttry.world.tile.TileData;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class World {
	private boolean loaded;
	private int width;
	private int height;
	private int version;
	private String name;
	private TileData[] tiles;
	private ArrayList<Enemy> enemies;

	public final int CURENT_VERSION = 0;

	public World(String name) {
		this.loaded = false;
		this.name = name;
		this.enemies = new ArrayList<>();

		Block.preload();
		Wall.preload();

		this.load();
	}

	public void render() {
		int windowWidth = LastTry.getWindowWidth();
		int windowHeight = LastTry.getWindowHeight();
		int tww = windowWidth / Block.size;
		int twh = windowHeight / Block.size;
		int tcx = (int) Camera.getX() / Block.size;
		int tcy = (int) Camera.getY() / Block.size;

		for(int y = Math.max(0, tcy - 2); y < Math.min(this.height - 1, tcy + twh + 3); y++) {
			for(int x = Math.max(0, tcx - 2); x < Math.min(this.width - 1, tcx + tww + 3); x++) {
				TileData tileData = this.getTile(x, y);

				if(tileData.wall != null) {
					tileData.wall.renderWall(tileData, x, y);
				}

				if(tileData.block != null) {
					tileData.block.renderBlock(tileData, x, y);
				}
			}
		}

		for(Enemy enemy : this.enemies) {
			enemy.render();
		}
	}

	public void update(int dt) {
		for(Enemy enemy : this.enemies) {
			enemy.update(dt);
		}
	}

	public Enemy spawnEnemy(String name, int x, int y) {
		Enemy enemy = Enemy.create(name);
		enemy.spawn(x, y);

		this.enemies.add(enemy);

		return enemy;
	}

	public TileData getTile(int x, int y) {
		return this.tiles[x + y * this.width];
	}

	public void setBlock(Block block, int x, int y) {
		TileData data = this.getTile(x, y);

		data.block = block;
		data.blockHp = data.maxHp;
		data.data = 0;
	}

	public void setWall(Wall wall, int x, int y) {
		TileData data = this.getTile(x, y);

		data.wall = wall;
		data.wallHp = data.maxHp;
		data.data = 0;
	}

	public void setData(byte data, int x, int y) {
		TileData tileData = this.getTile(x, y);
		tileData.data = data;
	}

	public byte getData(int x, int y) {
		TileData tileData = this.getTile(x, y);
		return tileData.data;
	}

	public Block getBlock(int x, int y) {
		TileData data = this.getTile(x, y);
		return data.block;
	}

	public Wall getWall(int x, int y) {
		TileData data = this.getTile(x, y);
		return data.wall;
	}

	public int getBlockId(int x, int y) {
		if(!this.isInside(x, y)) {
			return 0;
		}

		TileData data = this.getTile(x, y);

		if(data.block == null) {
			return 0;
		}

		return data.block.getId();
	}

	public int getWallId(int x, int y) {
		if(!this.isInside(x, y)) {
			return 0;
		}

		TileData data = this.getTile(x, y);

		if(data.wall == null) {
			return 0;
		}

		return data.wall.getId();
	}

	public boolean isLoaded() {
		return this.loaded;
	}

	public boolean isInside(int x, int y) {
		return (x >= 0 && x < this.width && y >= 0 && y < this.height);
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public String getName() {
		return this.name;
	}

	public String getFilePath() {
		return "assets/worlds/" + this.name + ".wld";
	}

	private void generate() {
		this.width = 200;
		this.height = 200;
		this.version = this.CURENT_VERSION;

		int totalSize = this.width * this.height;
		this.tiles = new TileData[totalSize];

		Random random = new Random();

		for(int i = 0; i < totalSize; i++) {
			int id = random.nextInt(2);

			this.tiles[i] = new TileData((Block) Item.fromId(id), (Wall) Item.fromId(id == 0 ? 0 : 2));
		}

		this.loaded = true;
	}

	private void load() {
		try {
			FileReader stream = new FileReader(this.getFilePath());

			// Header

			int version = stream.readInt32();

			if(version > this.CURENT_VERSION) {
				throw new RuntimeException("Unsupported version");
			}

			String worldName = stream.readString();

			this.name = worldName;
			this.width = stream.readInt32();
			this.height = stream.readInt32();

			// Tile data

			int totalSize = this.width * this.height;

			this.tiles = new TileData[totalSize];

			for(int i = 0; i < totalSize; i++) {
				this.tiles[i] = new TileData((Block) Item.fromId(stream.readInt32()), (Wall) Item.fromId(stream.readInt32()));

				// TODO: RLE
			}

			if(stream.readBoolean() == false) {
				throw new RuntimeException("Verification failed");
			}

			worldName = stream.readString();

			if(!worldName.equals(this.name)) {
				throw new RuntimeException("Verification failed");
			}

			// Verification
		} catch(FileNotFoundException exception) {
			this.generate();
			this.save();
		}

		this.loaded = true;
	}

	public void save() {
		FileWriter stream = new FileWriter(this.getFilePath());

		// Header

		stream.writeInt32(this.version);
		stream.writeString(this.name);
		stream.writeInt32(this.width);
		stream.writeInt32(this.height);

		// Tile data

		int totalSize = this.width * this.height;

		for(int i = 0; i < totalSize; i++) {
			TileData data = this.tiles[i];

			int blockId = 0;
			int wallId = 0;

			if(data.block != null) {
				blockId = data.block.getId();
			}

			if(data.wall != null) {
				wallId = data.wall.getId();
			}

			stream.writeInt32(blockId);
			stream.writeInt32(wallId);

			// TODO: RLE
		}

		// Verification

		stream.writeBoolean(true);
		stream.writeString(this.name);

		stream.close();
	}
}