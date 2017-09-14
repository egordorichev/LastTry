package org.egordorichev.lasttry.world.generator;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.world.World;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class WorldGenerator {
	public World world;
	private List<GeneratorTask> tasks = new ArrayList<>();

	public WorldGenerator(String name, World.Size size, int flags, int seed) {
		this.world = new World(name, size, flags, seed);
		Globals.setWorld(this.world);
		this.tasks.add(new SurfaceGenerationSimplexTask());
		this.tasks.add(new CaveGeneratorSimplexTask());
		// this.tasks.add(new FoilageGeneratorTask());
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
		while (this.tasks.size() > 0) {
			this.tasks.remove(0).run(this);
		}
		int w = this.world.getWidth() / 2;
		this.world.setSpawnPoint(new Vector2(w, this.world.getHighest(w)));
		return this.world;
	}

	public int getWorldWidth() {
		return this.world.getWidth();
	}

	public int getWorldHeight() {
		return this.world.getHeight();
	}

	public int getHighest(int x) {
		return world.getHighest(x);
	}
}
