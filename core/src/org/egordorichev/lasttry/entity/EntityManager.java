package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.entity.enemy.Enemies;
import org.egordorichev.lasttry.entity.enemy.Enemy;
import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private List<Entity> entities = new ArrayList<>();
    private List<Enemy> enemyEntities = new ArrayList<>();
    private List<Entity> clearList = new ArrayList<>();

    // private List<Gore> gores = new ArrayList<>();

    public void render() {
        // TODO: Only render on-screen entities.
        // Old code for doing so:
        /*
         * int gx = entity.getGridX(); int gy = entity.getGridY(); int w =
		 * entity.getGridWidth(); int h = entity.getGridHeight(); if ((gx > minX
		 * - w && gx < maxX + w) && (gy > minY - h && gy < maxY + h)) {
		 * entity.render(); }
		 */
        for (Entity entity : this.entities) {
            entity.render();
        }
    }

    public void update(int dt) {
        for (Entity entity : this.clearList) {
            this.entities.remove(entity);
        }

        this.clearList.clear();

        for (int i = this.entities.size() - 1; i >= 0; i--) {
	        Entity entity = this.entities.get(i);
            entity.update(dt);

            if (!entity.isActive()) {
                this.entities.remove(i);
            }
        }
    }

    public Entity spawn(Entity entity, int x, int y) {
        entity.spawn(x, y);
        this.entities.add(entity);
        return entity;
    }

    public Enemy spawnEnemy(short id, int x, int y) {
        Enemy enemy = Enemies.create(id);
        this.enemyEntities.add(enemy);
        this.spawn(enemy, x, y);
        return enemy;
    }

    public void remove(Entity entity) {
        this.entities.remove(entity);
    }

    public void markForRemoval(Entity entity) {
        this.clearList.add(entity);
        //Maintaining a second list for quick enemy retrieval.
        if(entity instanceof Enemy){
            enemyEntities.remove(entity);
        }
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public synchronized List<Enemy> getEnemyEntities() { return enemyEntities; }
}