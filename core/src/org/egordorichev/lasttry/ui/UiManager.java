package org.egordorichev.lasttry.ui;

import java.util.ArrayList;
import java.util.List;

public class UiManager {
    private List<UiComponent> components = new ArrayList<>();

    public void render() {
        for (int i = this.components.size() - 1; i >= 0; i--) {
            this.components.get(i).render();
        }
    }

    public void add(UiComponent component) {
        this.components.add(component);
    }
}
