package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.util.Assets;
import org.newdawn.slick.Animation;

public class GreenSlime extends Enemy {
	public GreenSlime() {
		super("Green Slime", 28, 0, 12);

		this.image = Assets.greenSlimeTexture;
	//	this.animation.addFrame(this.image.getSubImage(0, 0, 32, 24), 300);
	//	this.animation.addFrame(this.image.getSubImage(32, 0, 30, 24), 300);

		this.rect.width = 32;
		this.rect.height = 24;
	}
}