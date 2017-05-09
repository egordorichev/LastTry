package org.egordorichev.lasttry.entity.enemy;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.ai.AI;
import org.egordorichev.lasttry.entity.drop.Drop;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.util.Log;

public class EnemyInfo {
    private JsonValue root;

    public EnemyInfo(JsonValue root) {
        this.root = root;
    }

    public Enemy create() {
        try {
        	int defense = 0;
        	int hp = 1;
        	int damage = 1;

	        JsonValue root = this.root;

        	if (this.root.has("copy")) {
		        JsonValue r = Enemies.ENEMY_CACHE.get(this.root.getString("copy")).root;

		        if (r != null) {
        			root = r;
		        }
	        }

		    hp = Globals.world.flags.isHardmode() ? root.get("hp").get(2).asInt()
			    : Globals.world.flags.isExpertMode() ? root.get("hp").get(1).asInt() : root.get("hp").get(0).asInt();

		    defense = Globals.world.flags.isHardmode() ? root.get("defense").get(2).asInt()
			    : Globals.world.flags.isExpertMode() ? root.get("defense").get(1).asInt() : root.get("defense").get(0).asInt();

		    damage = Globals.world.flags.isHardmode() ? root.get("damage").get(2).asInt()
			    : Globals.world.flags.isExpertMode() ? root.get("damage").get(1).asInt() : root.get("damage").get(0).asInt();

	        Enemy enemy = new Enemy(AI.fromID(root.get("ai").asShort()), root.name());
	        enemy.stats.set(hp, 0, defense, damage);

	        if (root.has("animation")) {
		        enemy.graphics.load(root.get("animation"), this.root.has("texture") ? this.root.get("texture").asString() : root.get("texture").asString());
	        }

	        if (root.has("drop")) {
	        	for (JsonValue drop : root.get("drop")) {
			        enemy.drops.add(new Drop(Item.fromID(drop.get("id").asShort()), drop.get("count").get(0).asShort(), drop.get("count").get(1).asShort()));
		        }
	        }

	        // todo: kb resist, knock back, hitbox

            return enemy;
        } catch (Exception exception) {
            Log.error("Failed to parse " + this.root.name() + "!\n Cause:");
            exception.printStackTrace();
        }

        return null;
    }
}