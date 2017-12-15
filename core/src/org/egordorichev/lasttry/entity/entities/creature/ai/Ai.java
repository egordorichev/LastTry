package org.egordorichev.lasttry.entity.entities.creature.ai;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.entities.creature.Creature;
import org.egordorichev.lasttry.entity.entities.creature.StateComponent;
import org.egordorichev.lasttry.util.log.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Represents entity AI
 */
public class Ai {
	protected HashMap<String, Method> methods = new HashMap<>();

	/**
	 * Updates the AI
	 *
	 * @param entity The entity, that is being updated
	 */
	public void update(Entity entity) {
		StateComponent state = entity.getComponent(StateComponent.class);
		Method method = this.methods.get(state.state);

		if (method != null) {
			try {
				method.invoke(this, (Creature) entity, state.time);
			} catch (InvocationTargetException exception) {
				exception.getCause().printStackTrace();
				Log.error("Failed to call " + state.state + " state");
			} catch (Exception exception) {
				exception.printStackTrace();
				Log.error("Failed to call " + state.state + " state");
			}
		}

		state.time += 1;
	}

	/**
	 * Registers new state
	 *
	 * @param state New state
	 */
	protected void register(String state) {
		try {
			this.methods.put(state, this.getClass().getMethod(state, Creature.class, int.class));
		} catch (Exception exception) {
			exception.printStackTrace();
			Log.error("Failed to register " + state + " state");
		}
	}

	/**
	 * Registers new states
	 *
	 * @param states New states
	 */
	protected void registerAll(String... states) {
		for (String state : states) {
			this.register(state);
		}
	}
}