package org.egordorichev.lasttry.graphics.lighting;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
public class Lighting {
	private static FrameBuffer fbo;

	public static ShaderProgram ambientShader;
	public static ShaderProgram defaultShader;
	public static final float ambientIntensity = .7f;
	public static final Vector3 ambientColor = new Vector3(0.3f, 0.3f, 0.7f);
	public static Texture light;

	public static void init(int width, int height) {

	}

	public static void begin() {

	}

	public static void end() {

	}

	public static void render() {

	}
}