package org.egordorichev.lasttry.world.generator;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.world.World;
import java.util.ArrayList;
import java.util.List;

public class WorldGenerator {
	public World world;
	private List<GeneratorTask> tasks = new ArrayList<>();

	public WorldGenerator(String name, World.Size size, int flags, int seed) {
		this.world = new World(name, size, flags, seed);
		Globals.world = this.world;
		this.tasks.add(new TaskTerrainGen());
		this.tasks.add(new TaskCaveGenSimplex());
		//this.tasks.add(new TaskFoilageGen());
	}

	public void addTask(GeneratorTask task) {
		this.tasks.add(task);
	}

	public void insertTask(GeneratorTask task, int index) {
		this.tasks.add(index, task);
	}

	public void removeTask(int index) {
		this.tasks.remove(index);
	}

	public World generate() {
		this.tasks.forEach(t -> t.run(this));
		return this.world;
	}

	public int getWorldWidth() {
		return this.world.getWidth();
	}

	public int getWorldHeight() {
		return this.world.getHeight();
	}

    public int getHighest(int x) {
        for (int y = getWorldHeight(); y > 0; y--){
            if (this.world.blocks.getID(x, y) != ItemID.none){
                return y;
            }
        }
        return 0;
    }
}
