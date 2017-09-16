package org.egordorichev.lasttry.entitySystem.component;

import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import org.terasology.entitysystem.core.Component;
import org.terasology.entitysystem.core.EntityRef;

public class LocationComponent implements Component {
    public Vector3 location;
    public Quaternion rotation;
    public EntityRef parent;

    @Override
    public Class<? extends Component> getType() {
        return this.getClass();
    }
}
