package org.egordorichev.lasttry.player.skin;

import com.badlogic.gdx.graphics.Color;

/**
 * Stores player render info
 */
public class PlayerRenderInfo {
	/**
	 * Player hair type
	 */
	public int hairStyle;
	/**
	 * Hair color
	 */
	public Color hairColor;
	/**
	 * Eyes color
	 */
	public Color eyesColor;
	/**
	 * Skin color
	 */
	public Color skinColor;
	/**
	 * Clothes style
	 */
	public int clothesStyle;
	/**
	 * Player is male
	 */
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