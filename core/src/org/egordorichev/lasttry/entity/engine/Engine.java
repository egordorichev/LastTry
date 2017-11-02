package org.egordorichev.lasttry.entity.engine;

import org.egordorichev.lasttry.entity.system.systems.*;
import org.egordorichev.lasttry.entity.system.System;

import java.util.ArrayList;

/**
 * Handles systems
 */
public class Engine {
	/**
	 * All systems in the engine
	 */
	private static ArrayList<System> systems = new ArrayList<>();

	/**
	 * Adds all needed systems
	 */
	public static void init() {
		add(new CameraSystem());
		add(new RenderSystem());
	}

	/**
	 * Sends a message, that any system can handle
	 *
	 * @param message Message, to send
	 */
	public static void sendMessage(String message) {
		for (System system : systems) {
			system.handleMessage(message);
		}
	}

	/**
	 * Updates all systems
	 *
	 * @param delta Time, since the last frame
	 */
	public static void update(float delta) {
		for (System system : systems) {
			system.update(delta);
		}
	}

	/**
	 * Adds a system to the engine
	 *
	 * @param system System to add
	 */
	public static void add(System system) {
		systems.add(system);
	}
}