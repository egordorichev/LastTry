package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.state.MenuInputStateHelper;
import org.egordorichev.lasttry.util.GenericContainer;

import java.util.ArrayList;
import java.util.List;

public class UiPanel extends UiComponent {
    /**
     * Panel children
     **/
    protected List<UiComponent> children = new ArrayList<>();

    public UiPanel(Rectangle rectangle, Origin origin) {
        super(rectangle, origin);
        this.addComponents();
    }

    public UiPanel(Origin origin) {
        super(new Rectangle(0, 0, 0, 0), origin);
        this.addComponents();
    }

    public UiPanel() {
        this(Origin.TOP_LEFT);
    }

    /**
     * Used for overriding from instances
     */
    public void addComponents() {

    }

    /**
     * Adds given component to the panel
     *
     * @param component component to add
     */
    public void add(UiComponent component) {
        this.children.add(component);
    }

    /**
     * Clears the children
     */
    public void clear() {
        this.children.clear();
    }

    /**
     * Render children
     */
    public void render() {
        if (this.hidden) {
            return;
        }

        super.render();

        for (UiComponent component : this.children) {
            component.render();
        }
    }


    /**
     * Show method that accepts a parameter of a Generic Container for input states.
     * To be overriden when a panel, requires parameters in the method.
     *
     * @param inputStateBox Box containing the state of text entered by the user.
     */
    public void show(GenericContainer<MenuInputStateHelper.NameInputStates> inputStateBox) {

    }

}
