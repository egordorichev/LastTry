package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.entity.enemy.Enemy;
import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private List<Entity> entities = new ArrayList<>();
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
        Enemy enemy = Enemy.create(id);
        this.spawn(enemy, x, y);
        return enemy;
    }

    public void remove(Entity entity) {
        this.entities.remove(entity);
    }

    public void markForRemoval(Entity entity) {
        this.clearList.add(entity);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Enemy> retrieveEnemyEntities() {
        List<Enemy> enemyEntities = new ArrayList<>();

        for(Entity entity : entities) {
            if (entity instanceof Enemy) {
                //Check that the object is not marked for removal
                //TODO this contains would be faster if clearList was a HashMap, source: http://stackoverflow.com/questions/559839/big-o-summary-for-java-collections-framework-implementations

	            if(!clearList.contains(entity)) {
                    Enemy enemy = (Enemy) entity;
                    enemyEntities.add(enemy);
                }
            }
        }

        return enemyEntities;
    }
}