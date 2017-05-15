package org.egordorichev.lasttry.ui.chat;

import java.util.Timer;
import java.util.TimerTask;

public class ChatLine {
	public String text;
	private boolean remove;

	public ChatLine(String text) {
		this.text = text;
		this.remove = false;

		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				remove = true;
			}
		}, 10000);
	}

	public boolean shouldBeRemoved() {
		return this.remove;
	}
}