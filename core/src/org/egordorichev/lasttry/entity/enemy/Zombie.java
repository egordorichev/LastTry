package org.egordorichev.lasttry.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.Direction;
import org.egordorichev.lasttry.entity.Drop;
import org.egordorichev.lasttry.entity.EnemyID;
import org.egordorichev.lasttry.graphics.Animation;
import org.egordorichev.lasttry.graphics.AnimationFrame;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.Items;
import org.egordorichev.lasttry.util.Rectangle;

/**
 * Zombie enemy added, currently uses the same AI as slime.
 * //TODO Improve Zombie AI
 */
public class Zombie extends Enemy {

    /**
     * The next AI tick to jump at.
     */
    protected int nextJump;
    /**
     * Marker for if the zombie has jumped this AI cycle.
     */
    protected boolean canJump;

    public Zombie() {
        super(EnemyID.zombie, 100, 2, 25, 2);

        this.drops.add(new Drop(Items.copperCoin, 25, 25));

        this.maxAi = 90;
        this.updateJumpDelay();
        this.texture = Assets.getTexture(Textures.zombie);

        Animation walkAnimation = new Animation(false);
        walkAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 0, 34, 48), 10));
        walkAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 48, 34, 48), 10));
        walkAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 96, 34, 48), 10));


        Animation idleAnimation = new Animation(true);
        idleAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 0, 34, 48), 10));

        Animation jumpAnimation = new Animation(false);
        jumpAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 0, 34, 48), 10));
        jumpAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 48, 34, 48), 10));
        jumpAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 96, 34, 48), 10));

        this.animations[State.JUMPING.getId()] = jumpAnimation;
        this.animations[State.MOVING.getId()] = walkAnimation;
        this.animations[State.IDLE.getId()] = idleAnimation;
        this.animations[State.DEAD.getId()] = null;
        this.animations[State.FALLING.getId()] = jumpAnimation;
        this.animations[State.FLYING.getId()] = null;

        this.renderBounds = new Rectangle(0, 0, 34, 48);
        this.hitbox = new Rectangle(this.renderBounds.x + 3, this.renderBounds.y + 3, this.renderBounds.width - 6,
                this.renderBounds.height - 3);
    }

    @Override
    public void updateAI() {
        super.updateAI();

        //TODO Should walk and follow the player, to attack the player.
        jumpBehaviour();

    }

    /**
     * Jump behaviour AI, copied from Slime monster.
     */
    private void jumpBehaviour()
    {
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


        // If the zombie is jumping / falling try and fall to the player.
        // If they are on the ground they will not move.
        // If the direction to the player changes mid-jump the slime will not be
        // able to change directions.
        if (this.state == State.JUMPING || this.state == State.FALLING) {
            this.move(this.direction);
        }
    }

    //TODO Improve and reimplement
    private void walkAction()
    {
        //TODO walk to the player
        // Set direction so that slime faces the player
        int dir = Float.compare(LastTry.player.getX(), this.getX());
        if (dir < 0) {
            this.direction = Direction.LEFT;
        } else if (dir > 0) {
            this.direction = Direction.RIGHT;
        }
        // Jump and set next jump delay.
        this.move(this.direction, 1);
    }

    /**
     * Update the time of the next jump.
     */
    private void updateJumpDelay() {
        this.canJump = false;
        this.nextJump = (int) ((this.maxAi / 2) + (Math.random() * this.maxAi / 2));
    }
}
