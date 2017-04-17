package org.egordorichev.lasttry.entity.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.entity.components.CreatureGraphicsComponent;
import org.egordorichev.lasttry.entity.components.StateComponent;
import org.egordorichev.lasttry.entity.player.skin.PlayerRenderer;
import org.egordorichev.lasttry.graphics.Animation;
import org.egordorichev.lasttry.graphics.AnimationFrame;

public class PlayerGraphicsComponent extends CreatureGraphicsComponent {
	private Animation[] animations = new Animation[StateComponent.State.values().length];
	private Texture texture;

	public PlayerGraphicsComponent() {
		this.setupAnimations();

		this.texture = PlayerRenderer.generateTexture(((Player) this.entity).info.renderInfo);
	}

	@Override
	public void render() {

	}

	private void setupAnimations() {
		Animation idleAnimation = new Animation(false);
		idleAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 8, 32, 48), 0));

		Animation movingAnimation = new Animation(true);
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 342, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 400, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 456, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 512, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 568, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 624, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 680, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 736, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 792, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 848, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 902, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 960, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 1016, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 1072, 32, 48), 1));

		Animation jumpingAnimation = new Animation(false);
		jumpingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 288, 32, 48), 0));

		Animation flyingAnimation = new Animation(true); // TODO

		Animation deadAnimation = new Animation(false);
		deadAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 0, 0, 0), 0)); // TODO

		Animation actingAnimation = new Animation(true); // TODO

		this.animations[StateComponent.State.IDLE.getId()] = idleAnimation;
		this.animations[StateComponent.State.MOVING.getId()] = movingAnimation;
		this.animations[StateComponent.State.JUMPING.getId()] = jumpingAnimation;
		this.animations[StateComponent.State.FALLING.getId()] = jumpingAnimation; // They are the same
		this.animations[StateComponent.State.FLYING.getId()] = flyingAnimation;
		this.animations[StateComponent.State.DEAD.getId()] = deadAnimation;
		this.animations[StateComponent.State.ACTING.getId()] = actingAnimation;
	}
}