package org.egordorichev.lasttry.logic.player.component;

import org.terasology.entitysystem.core.Component;

public class PlayerComponent implements Component {
    @Override
    public Class<? extends Component> getType() {
        return this.getClass();
    }
}
