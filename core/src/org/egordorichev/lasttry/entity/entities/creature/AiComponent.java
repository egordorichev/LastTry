package org.egordorichev.lasttry.entity.entities.creature;

import org.egordorichev.lasttry.entity.component.Component;
import org.egordorichev.lasttry.entity.entities.creature.ai.Ai;

/**
 * Stores creature AI
 */
public class AiComponent extends Component {
	/**
	 * The AI
	 */
	public Ai ai;
}