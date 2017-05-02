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
        Log.debug(AI.fromID(this.root.get("ai").asShort()).toString());

        try {
            int hp = Globals.world.flags.isHardmode() ? this.root.get("hp").get(2).asInt()
                : Globals.world.flags.isExpertMode() ?  this.root.get("hp").get(1).asInt() : this.root.get("hp").get(0).asInt();

            int defense  = Globals.world.flags.isHardmode() ? this.root.get("defense").get(2).asInt()
                : Globals.world.flags.isExpertMode() ?  this.root.get("defense").get(1).asInt() : this.root.get("defense").get(0).asInt();

            int damage = Globals.world.flags.isHardmode() ? this.root.get("damage").get(2).asInt()
                : Globals.world.flags.isExpertMode() ?  this.root.get("damage").get(1).asInt() : this.root.get("damage").get(0).asInt();

            Enemy enemy = new Enemy(AI.fromID(this.root.get("ai").asShort()), this.root.name());

            enemy.stats.set(hp, 0, defense, damage);
	        enemy.graphics.load(this.root.get("animation"));

	        for (JsonValue drop : root.get("drop")) {
	        	enemy.drops.add(new Drop(Item.fromID(drop.get("id").asShort()), drop.get("count").get(0).asShort(), drop.get("count").get(1).asShort()));
	        }

	        // todo: kb resist, knock back, hitbox

            return enemy;
        } catch (Exception exception) {
            Log.error("Failed to parse " + this.root.name() + "!");
        }

        return null;
    }
}