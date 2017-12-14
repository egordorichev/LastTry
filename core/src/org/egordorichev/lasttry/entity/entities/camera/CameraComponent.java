package org.egordorichev.lasttry.entity.entities.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import org.egordorichev.lasttry.entity.component.Component;

/**
 * Handles camera
 */
public class CameraComponent extends Component {
	/**
	 * The camera
	 */
	public OrthographicCamera camera;

	public CameraComponent() {
		 this.camera = new OrthographicCamera(320, 180);
	}
}