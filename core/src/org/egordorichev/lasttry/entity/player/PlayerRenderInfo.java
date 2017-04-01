package org.egordorichev.lasttry.entity.player;

import com.badlogic.gdx.graphics.Color;

public class PlayerRenderInfo {
	public int hairStyle;
	public Color hairColor;
	public Color eyesColor;
	public Color skinColor;
	public int clothesStyle;
	public boolean male;

	public PlayerRenderInfo(int hairStyle, Color hairColor, Color eyesColor,
		Color skinColor, int clothesStyle, boolean male) {

		this.hairStyle = hairStyle;
		this.hairColor = hairColor;
		this.eyesColor = eyesColor;
		this.skinColor = skinColor;
		this.clothesStyle = clothesStyle;
		this.male = male;
	}
}