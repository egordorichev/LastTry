package org.egordorichev.lasttry.ui;

public interface UiScreen {
	void onUIOpen();

	void onUIClose();

	boolean isOpen();

	void setOpen(boolean open);
}
