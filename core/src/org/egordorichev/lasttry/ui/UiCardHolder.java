package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.math.Rectangle;

public class UiCardHolder extends UiPanel {
    private static final int SPACING = 10;
    private static final int CARD_HEIGHT = 64;
    private UiCard lastCard;

    public UiCardHolder(Rectangle rectangle, Origin origin) {
        super(rectangle, origin);
    }

    public UiCardHolder(Rectangle rectangle) {
        this(rectangle, Origin.TOP_LEFT);
    }

    public void add(UiCard component) {
        super.add(component);

        if (this.lastCard != null) {
            component.setPosition(this.lastCard.getX(), (int) this.lastCard.getRect().y
                    + this.lastCard.getHeight() + SPACING);
        } else {
            component.setPosition(this.getX() + SPACING, this.getClickY() + SPACING);
        }

        component.setSize(this.getWidth() - SPACING * 2, CARD_HEIGHT);
        component.addComponents();

        this.lastCard = component;
    }

    @Override
    protected void update() {

    }
}