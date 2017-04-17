package org.egordorichev.lasttry.entity.enemy.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.drop.Drop;
import org.egordorichev.lasttry.entity.enemy.Enemy;
import org.egordorichev.lasttry.graphics.Animation;
import org.egordorichev.lasttry.graphics.AnimationFrame;
import org.egordorichev.lasttry.item.Items;
import org.egordorichev.lasttry.util.Rectangle;

public class Slime extends Enemy {
    protected int nextJump;
    protected boolean canJump;

    public Slime(short id, int hp, int defense, int damage, Texture texture) {
        super(id, hp, defense, damage);

        this.drops.add(new Drop(Items.gel, Drop.Chance.ALWAYS, 1, 4));

        this.ai.setMax(90);
        this.updateJumpDelay();
        this.texture = texture;

        Animation jumpAnimation = new Animation(false);

        jumpAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 32, 0, 32, 24), 0));

        Animation idleAnimation = new Animation(true);

        idleAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 0, 32, 24), 10));
        idleAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 32, 0, 32, 24), 10));

        this.animations[State.JUMPING.getId()] = jumpAnimation;
        this.animations[State.MOVING.getId()] = jumpAnimation;
        this.animations[State.IDLE.getId()] = idleAnimation;
        this.animations[State.DEAD.getId()] = null;
        this.animations[State.FALLING.getId()] = jumpAnimation;
        this.animations[State.FLYING.getId()] = null;

        this.renderBounds = new Rectangle(0, 0, 32, 24);
        this.hitbox = new Rectangle(this.renderBounds.x + 3, this.renderBounds.y + 3, this.renderBounds.width - 6,
                this.renderBounds.height - 3);
    }

    @Override
    public void updateAI() {
        super.updateAI();
        // New cycle -> allow jumping again
        if (this.currentAi == 0 && this.velocity.y == 0) {
            canJump = true;
        }

        // If the current AI tick is the jump tick...
        if (canJump && this.currentAi == nextJump) {
            // Set direction so that slime faces the player
            int dir = Float.compare(LastTry.player.getX(), this.getX());
            if (dir < 0) {
                this.direction = Direction.LEFT;
            } else if (dir > 0) {
                this.direction = Direction.RIGHT;
            }
            // Jump and set next jump delay.
            this.jump();
            this.updateJumpDelay();

        }


        // If the slime is jumping / falling try and fall to the player.
        // If they are on the ground they will not move.
        // If the direction to the player changes mid-jump the slime will not be
        // able to change directions.
        if (this.state == State.JUMPING || this.state == State.FALLING) {
            this.move(this.direction);
        }
    }

    /**
     * Update the time of the next jump.
     */
    private void updateJumpDelay() {
        this.canJump = false;
        this.nextJump = (int) ((this.maxAi / 2) + (Math.random() * this.maxAi / 2));
    }
}