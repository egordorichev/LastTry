package org.egordorichev.lasttry.ui.chat;

public class ChatCommand {
	private String name;

	public ChatCommand(String name) {
		this.name = name;
	}

	public void call(String[] args) {

	}

	public String getName() {
		return this.name;
	}
}