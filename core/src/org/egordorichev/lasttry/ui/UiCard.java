package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class UiCard extends UiPanel {
	public UiCard(Origin origin) {
		super(new Rectangle(0, 0, 0, 0), origin);
	}

	public UiCard() {
		this(Origin.TOP_LEFT);
	}
	
	// TODO
}
