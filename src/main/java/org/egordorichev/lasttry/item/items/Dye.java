package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.item.Item;
import org.newdawn.slick.Image;
import org.newdawn.slick.opengl.shader.ShaderProgram;

public class Dye extends Item {
	/**
	 * Shader, applyed on other items
	 */
	protected ShaderProgram shader;
	
	public Dye(int id, String name, Image texture) {
		super(id, name, texture);
	}

	// TODO
}
