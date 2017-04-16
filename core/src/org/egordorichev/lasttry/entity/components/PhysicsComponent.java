package org.egordorichev.lasttry.entity.components;

import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Rectangle;

public class PhysicsComponent extends Component {
	private static final float STOP_VELOCITY = 0.2F;
	private static final float STEP_HEIGHT = 1.05F;

	private Entity entity;
	private Vector2 position = new Vector2();
	private Vector2 size = new Vector2();
	private Vector2 velocity = new Vector2();
	private boolean solid;
	private Rectangle hitBox;

	public PhysicsComponent(Entity entity) {
		this.entity = entity;
	}

	public void update(int dt) {

	}

	public void setGridPosition(float gridX, float gridY) {
		this.position.x = gridX * Block.SIZE;
		this.position.y = gridY * Block.SIZE;
	}

	public void setPosition(float x, float y) {
		this.position.x = x;
		this.position.y = y;
	}

	public Vector2 getPosition() {
		return this.position;
	}

	public int getGridX() {
		return (int) this.position.x / Block.SIZE;
	}

	public int getGridY() {
		return (int) this.position.y / Block.SIZE;
	}
}