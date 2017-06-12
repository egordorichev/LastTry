package org.egordorichev.lasttry.entity.components;

import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Callable;
import org.egordorichev.lasttry.util.Rectangle;
import org.egordorichev.lasttry.util.Util;

public class PhysicsComponent extends EntityComponent {
    public enum Direction {
        LEFT, RIGHT
    }

    protected static final float STOP_VELOCITY = 0.2F;
    protected static final float STEP_HEIGHT = 1.05F;

    protected Vector2 position = new Vector2();
    protected Vector2 size = new Vector2();
    protected Vector2 velocity = new Vector2();
    protected Rectangle hitbox;
    protected Direction direction = Direction.RIGHT;
    protected float speed = 1.0f;
    protected boolean solid = true;
    /**
     * Boolean indiciating if the entity is in the midst of stepping <i>(And
     * thus requires different logic checks to ensure they smoothly step through
     * a block rather than teleport on top of it)</i>
     */
    private boolean isStepping;
    /**
     * Boolean indicating if the entity is to use smooth stepping. <br>
     * If set to true movements such as stepping up blocks will be smoothed.<br>
     * If set to false, stepping will be instant and faster, but look more
     * jarring.
     */
    private boolean useSmoothMoves = true;
	/**
	 * Called on ground hit
	 */
	private Callable onGroundHit = new Callable() {
	    @Override
	    public void call() {

	    }
    };

    public PhysicsComponent(Entity entity) {
        super(entity);
    }

    public PhysicsComponent() {
        this.hitbox = new Rectangle(0, 0, 0, 0);
    }

    @Override
    public void setEntity(Entity entity) {
        super.setEntity(entity);

        this.size = new Vector2(32, 48); // TODO: get the size
        this.hitbox = new Rectangle(3, 3, this.size.x - 6, this.size.y - 3);
    }

    public void update(int dt) {
        if (!this.entity.isActive()) {
            return;
        }
        this.updateXVelocity();
        this.updateYVelocity();
        this.pushOutOfBlocks(1);
    }

    /**
     * Pushes the player out of blocks.
     * 
     * @param i
     *            Higher values = more fine tune pushes.
     * @return If the player has been pushed.
     */
    private boolean pushOutOfBlocks(int i) {
        Rectangle box = this.hitbox.copy().offset(this.position);
        float offsetSize = Block.SIZE;
        boolean pushed = false;
        if (collides(box)) {
            float cut = (float) (4 * Math.sqrt(i * 2));
            float checkOffset = offsetSize / i;
            float positionOffset = offsetSize / cut;
            float dy = 0, dx = 0;
            if (!collides(box.offset(0, checkOffset))) {
                dy += positionOffset;
                pushed = true;
            } else if (!collides(box.offset(0, -checkOffset))) {
                dy -= positionOffset;
                pushed = true;
            } else if (!collides(box.offset(checkOffset, 0))) {
                dx += positionOffset;
                pushed = true;
            } else if (!collides(box.offset(-checkOffset, 0))) {
                dx -= positionOffset;
                pushed = true;
            } else if (!collides(box.offset(checkOffset, checkOffset))) {
                dx += positionOffset;
                dy += positionOffset;
                pushed = true;
            } else if (!collides(box.offset(checkOffset, -checkOffset))) {
                dx += positionOffset;
                dy -= positionOffset;
                pushed = true;
            } else if (!collides(box.offset(-checkOffset, checkOffset))) {
                dx -= positionOffset;
                dy += positionOffset;
                pushed = true;
            } else if (!collides(box.offset(-checkOffset, -checkOffset))) {
                dx -= positionOffset;
                dy -= positionOffset;
                pushed = true;
            }
            if (this.useSmoothMoves && this.isStepping) {
                if (dy > 0) {
                    dy = 0;
                }
                if (dx != 0) {
                    dx = 0;
                }
            }
            this.position.add(dx, dy);
        }

        return pushed;
    }

    public void jump() {

    }

    public void move(Direction direction) {

    }

    private void updateXVelocity() {
        // Non-solids skip adjustment and collision checks
        if (this.solid && this.velocity.x != 0) {
            // this.pushOutOfBlocks(Util.random(1, 5));
            Rectangle originalHitbox = this.hitbox.copy().offset(this.position);
            boolean collidesOriginal = collides(originalHitbox);
            // If stepping, handle smoothed movement
            if (this.useSmoothMoves && this.isStepping) {
                int dir = this.velocity.x > 0 ? 1 : -1;
                Rectangle head = this.hitbox.copy().offset(this.position);
                head.y += Block.SIZE;
                head.height = Block.SIZE;
                // If currently in a step and the next expected position of the
                // head is not occupied by a block, keep moving diagonally.
                if (collidesOriginal && !collides(head.offset(dir * Block.SIZE * 0.9f, Block.SIZE * 0.9f))) {
                    // Move diagonally.
                    this.position.y += Math.abs(this.velocity.x) / 2;
                } else {
                    this.isStepping = false;
                    this.position.y += 2;
                    this.position.x -= dir * 2;
                    this.pushOutOfBlocks(1);
                }
            } else if (collidesOriginal) {
                this.pushOutOfBlocks(Util.random(1, 6));
            }
            Rectangle newHitbox = originalHitbox.copy().offset(this.velocity.x, 0);
            // If collide, do step logic
            // Else, move normally
            if (collides(newHitbox)) {
                // Test if can step
                float step = Block.SIZE * STEP_HEIGHT;
                if (collides(newHitbox.offset(0, step))) {
                    // Step will collide, set horizontal velocity
                    // so they will walk only up to the wall but no further.
                    float offset = this.velocity.x > 0 ? Block.SIZE : -Block.SIZE;
                    float distToCollision = Globals.getWorld().distToHorizontalCollision(originalHitbox,
                            this.velocity.x);
                    if (distToCollision != 0) {
                        this.velocity.x = distToCollision - offset;
                    } else if (!this.isStepping) {
                        this.velocity.x = 0;
                    }
                } else {
                    // Step will succeed.
                    if (this.useSmoothMoves) {
                        // Step into the block and enable stepping logic.
                        this.position.y += 4;
                        this.velocity.x *= 0.9f;
                        this.isStepping = true;
                    } else {
                        // Teleport on top of the block.
                        this.velocity.x /= 2;
                        this.position.y += step;
                    }

                }
            } else if (!this.isStepping) {
                // Prevent wall clipping with high speeds
                float distToCollision = Globals.getWorld().distToHorizontalCollision(originalHitbox, this.velocity.x);
                if (Math.abs(distToCollision) < Math.abs(this.velocity.x)) {
                    this.velocity.x = distToCollision;
                }
            }
        }
        this.position.x += this.velocity.x;

        // Slow down horizontal velocity
        this.velocity.x *= 0.8;
        if (Math.abs(this.velocity.x) < STOP_VELOCITY) {
            this.velocity.x = 0;
        }
    }

    private void updateYVelocity() {
        if (this.solid) {
            // Apply gravity
            this.velocity.y -= 0.4f;
        }

        float lastYVelocity = this.velocity.y;

        // Non-solids skip adjustment and collision checks
        if (this.solid && this.velocity.y != 0) {
            Rectangle boxO = this.hitbox.copy().offset(this.position);
            boolean falling = this.velocity.y < 0;
            Rectangle boxV = boxO.copy().offset(0, this.velocity.y);
            // If collides, on reset vertical motion
            // Else move normally.
            if (collides(boxV)) {
                if (falling) {
                    // Hits ground
                    float offset = this.velocity.y > 0 ? Block.SIZE : -Block.SIZE;
                    float distToCollision = Globals.getWorld().distToVerticalCollision(boxO, this.velocity.y);

                    if (distToCollision < -0.1f || distToCollision > 0.1f) {
	                    if (this.velocity.y != -0.4f) {
		                    this.onGroundHit.call();
	                    }
                    }

                    if (distToCollision != 0) {
                        // there is some space to move, so move
                        this.velocity.y = distToCollision - offset;
                    } else {
                        // Already colliding, stay on the ground.
	                    this.velocity.y = 0;
                    }
                } else {
                    // Hits ceiling
                    float speed = -0.5f;
                    if (collides(boxO.copy().offset(0, speed))) {
                        this.velocity.y = 0;
                    } else {
                        this.velocity.y = speed;
                    }
                }
            } else {
                // Prevent floor clipping with high speeds
                float distToCollision = Globals.getWorld().distToVerticalCollision(boxO, this.velocity.y);
                if (Math.abs(distToCollision) < Math.abs(this.velocity.y)) {
                    this.velocity.y = distToCollision;
                }
            }
        }
        this.position.y += this.velocity.y;
    }

    /**
     * Shorthand for
     * 
     * <pre>
     * Globals.getWorld().isColliding(rect)
     * </pre>
     * 
     * @param rect
     * @return
     */
    private boolean collides(Rectangle rect) {
        return Globals.getWorld().isColliding(rect);
    }

    public void setGridPosition(float gridX, float gridY) {
        this.position.x = gridX * Block.SIZE;
        this.position.y = gridY * Block.SIZE;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
    }

    public void setSize(float width, float height) {
        this.size.x = width;
        this.size.y = height;
    }

    public void setWidth(float width) {
        this.size.x = width;
    }

    public void setHeight(float height) {
        this.size.y = height;
    }

    public float getWidth() {
        return this.size.x;
    }

    public float getHeight() {
        return this.size.y;
    }

    public boolean isFlipped() {
        return this.direction == Direction.LEFT;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public Vector2 getSize() {
        return this.size;
    }

    public int getGridX() {
        return (int) (this.position.x + 0.5) / Block.SIZE;
    }

    public int getGridY() {
        return (int) (this.position.y + 0.5) / Block.SIZE;
    }

    public Vector2 getGridPosition() {
        return new Vector2(getGridX(), getGridY());
    }

    public float getX() {
        return this.position.x;
    }

    public float getY() {
        return this.position.y;
    }

    public float getCenterX() {
        return this.position.x + this.size.x / 2;
    }

    public float getCenterY() {
        return this.position.y + this.size.y / 2;
    }

    public Rectangle getHitbox() {
        return new Rectangle(this.getX() + this.hitbox.x, this.getY() + this.hitbox.y, this.hitbox.width,
                this.hitbox.height);
    }

    public Direction getDirection() {
        return this.direction;
    }

    public Vector2 getVelocity() {
        return this.velocity;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

	public void setOnGroundHit(Callable onGroundHit) {
		this.onGroundHit = onGroundHit;
	}
}