package org.egordorichev.lasttry.ui;

import org.egordorichev.lasttry.Globals;

public interface UiToggleScreen extends UiScreen {
    default void toggle() {
        if (isOpen()){
            // Close the current screen
            Globals.setCurrentScreen(null);
        } else {
            // Set the screen to this
            Globals.setCurrentScreen(this);
        }
    }
}
