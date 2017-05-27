package org.egordorichev.lasttry.entity.components;

import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.player.Player;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Log;
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
        Rectangle box = this.hitbox.copy().offset(this.position).offset(0, 0.05f);
        float offsetSize = Block.SIZE;
        boolean pushed = false;
        if (Globals.getWorld().isColliding(box)) {
            float cut = (float) (4 * Math.sqrt(i * 2));
            float checkOffset = offsetSize / i;
            float positionOffset = offsetSize / cut;
            if (!Globals.getWorld().isColliding(box.offset(0, checkOffset))) {
                this.position.y += positionOffset;
                pushed = true;
            } else if (!Globals.getWorld().isColliding(box.offset(0, -checkOffset))) {
                this.position.y -= positionOffset;
                pushed = true;
            } else if (!Globals.getWorld().isColliding(box.offset(checkOffset, 0))) {
                this.position.x += positionOffset;
                pushed = true;
            } else if (!Globals.getWorld().isColliding(box.offset(-checkOffset, 0))) {
                this.position.x -= positionOffset;
                pushed = true;
            } else if (!Globals.getWorld().isColliding(box.offset(checkOffset, checkOffset))) {
                this.position.x += positionOffset;
                this.position.y += positionOffset;
                pushed = true;
            } else if (!Globals.getWorld().isColliding(box.offset(checkOffset, -checkOffset))) {
                this.position.x += positionOffset;
                this.position.y -= positionOffset;
                pushed = true;
            } else if (!Globals.getWorld().isColliding(box.offset(-checkOffset, checkOffset))) {
                this.position.x -= positionOffset;
                this.position.y += positionOffset;
                pushed = true;
            } else if (!Globals.getWorld().isColliding(box.offset(-checkOffset, -checkOffset))) {
                this.position.x -= positionOffset;
                this.position.y -= positionOffset;
                pushed = true;
            }
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
            Rectangle originalHitbox = this.hitbox.copy().offset(this.position);
            Rectangle newHitbox = originalHitbox.copy().offset(this.velocity.x, 0);
            // If collide, do step logic
            // Else, move normally
            if (Globals.getWorld().isColliding(newHitbox)) {
                // Test if can step
                float step = Block.SIZE * STEP_HEIGHT;
                if (Globals.getWorld().isColliding(newHitbox.offset(0, step))) {
                    // Step will collide, set horizontal velocity
                    // so they will walk only up to the wall but no further.
                    float offset = this.velocity.x > 0 ? Block.SIZE : -Block.SIZE;
                    float distToCollision = Globals.getWorld().distToHorizontalCollision(originalHitbox,
                            this.velocity.x);
                    if (distToCollision != 0) {
                        this.velocity.x = distToCollision - offset;
                    } else {
                        this.velocity.x = 0;
                    }
                    this.onBlockCollide();
                } else {
                    // Step will succed.
                    this.velocity.x /= 2;
                    this.position.y += step;
                }
            } else {
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
        // Non-solids skip adjustment and collision checks
        if (this.solid && this.velocity.y != 0) {
            Rectangle originalHitbox = this.hitbox.copy().offset(this.position);
            boolean falling = this.velocity.y < 0;
            Rectangle newHitbox = originalHitbox.copy().offset(0, this.velocity.y);
            // If collides, on reset vertical motion, call onCollide
            // Else move normally.
            if (Globals.getWorld().isColliding(newHitbox)) {
                if (falling) {
                    // Hits ground
                    float offset = this.velocity.y > 0 ? Block.SIZE : -Block.SIZE;
                    float distToCollision = Globals.getWorld().distToVerticalCollision(originalHitbox, this.velocity.y);
                    if (distToCollision != 0) {
                        // there is some space to move, so move
                        this.velocity.y = distToCollision - offset;
                    } else {
                        // Already colliding, stay on the ground.
                        this.velocity.y = 0;
                    }
                    this.onBlockCollide();
                } else {
                    // Hits ceiling
                    float speed = -0.5f;
                    if (Globals.getWorld().isColliding(originalHitbox.copy().offset(0, speed))) {
                        this.velocity.y = 0;
                    } else {
                        this.velocity.y = speed;
                    }
                    this.onBlockCollide();
                }
            } else {
                // Prevent floor clipping with high speeds
                float distToCollision = Globals.getWorld().distToVerticalCollision(originalHitbox, this.velocity.y);
                if (Math.abs(distToCollision) < Math.abs(this.velocity.y)) {
                    this.velocity.y = distToCollision;
                }
            }
        }
        this.position.y += this.velocity.y;
    }

    protected void onBlockCollide() {
        // TODO: callback?
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
        return (int) this.position.x / Block.SIZE;
    }

    public int getGridY() {
        return (int) this.position.y / Block.SIZE;
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
}