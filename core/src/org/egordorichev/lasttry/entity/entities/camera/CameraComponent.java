package org.egordorichev.lasttry.entity.entities.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.Component;

public class CameraComponent extends Component {
	public OrthographicCamera camera = new OrthographicCamera();

	public CameraComponent(Entity entity) {
		super(entity);
	}
}