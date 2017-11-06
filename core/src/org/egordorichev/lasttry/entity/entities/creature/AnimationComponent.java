package org.egordorichev.lasttry.entity.entities.creature;

import org.egordorichev.lasttry.entity.component.Component;
import org.egordorichev.lasttry.graphics.animation.Animation;

import java.util.HashMap;

/**
 * Handles and loads animations
 */
public class AnimationComponent extends Component {
	/**
	 * List of the animations
	 */
	public HashMap<String, Animation> animations = new HashMap<>();
	/**
	 * Current playing animation
	 */
	public Animation current;
}