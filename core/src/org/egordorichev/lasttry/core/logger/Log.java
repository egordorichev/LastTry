package org.egordorichev.lasttry.core.logger;

import com.sun.corba.se.impl.ior.GenericTaggedComponent;

public class Log {
	class Colors {
		private static final String ANSI_RESET = "\u001B[0m";
		private static final String ANSI_BLACK = "\u001B[30m";
		private static final String ANSI_RED = "\u001B[31m";
		private static final String ANSI_GREEN = "\u001B[32m";
		private static final String ANSI_YELLOW = "\u001B[33m";
		private static final String ANSI_BLUE = "\u001B[34m";
		private static final String ANSI_PURPLE = "\u001B[35m";
		private static final String ANSI_CYAN = "\u001B[36m";
		private static final String ANSI_WHITE = "\u001B[37m";
	}

	public enum Severity{
		DEBUG, INFO, WARNING, ERROR;

		public static String getColor(Severity severity){
			switch (severity){
				case DEBUG: return Colors.ANSI_WHITE;
				case INFO: return Colors.ANSI_BLUE;
				case WARNING: return Colors.ANSI_YELLOW;
				case ERROR: return Colors.ANSI_RED;
			}

			return null;
		}

		public static String getPrefix(Severity severity){
			switch (severity){
				case DEBUG: return "DEBUG: ";
				case INFO: return "INFO: ";
				case WARNING: return "WARNING: ";
				case ERROR: return "ERROR: ";
			}

			return null;
		}
	}

	private int channel;

	private boolean enabled;

	Log(int channel){
		this.channel = channel;
		enabled = true;
	}

	public void log(Severity severity, String message){
		if(!enabled) return;

		printMessage(severity, message);
	}

	public void changeChannel(int channel){
		this.channel = channel;
	}

	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}

	public int getChannel(){
		return channel;
	}

	public boolean isEnabled(){
		return enabled;
	}

	private void printMessage(Severity severity, String message){
		System.out.print(Severity.getColor(severity) + " ");
		printPath(Thread.currentThread().getStackTrace());
		System.out.println(" " + Severity.getPrefix(severity) + message + Colors.ANSI_RESET);
	}


	private void printPath(StackTraceElement[] stackTraceElements) {
		System.out.print(stackTraceElements[3].getClassName());
	}
}




