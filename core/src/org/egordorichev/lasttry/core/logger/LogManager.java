package org.egordorichev.lasttry.core.logger;

import sun.plugin2.gluegen.runtime.CPU;

import java.util.ArrayList;


public class LogManager {
	enum Logger {
		NORMAL(0), ERROR(1), WORLD(2);

		private final int id;

		Logger(int id) {
			this.id = id;
		}

		public int getValue() {
			return id;
		}
	}

	public static final Log log = new Log(Logger.NORMAL.getValue());
	public static final Log errorLog = new Log(Logger.ERROR.getValue());
	public static final Log worldLog = new Log(Logger.WORLD.getValue());

	private static ArrayList<Log> loggers = new ArrayList<>(Logger.values().length);
	private static boolean isInit;

	public static void init() {
		loggers.add(log);
		loggers.add(errorLog);
		loggers.add(worldLog);

		isInit = true;
	}

	public static void filter(int channel) throws RuntimeException {
		if (!isInit) throw new RuntimeException("logger is not initialized");

		for (int i = 0; i < loggers.size(); i++){
			if(loggers.get(i).getChannel() == channel){
				loggers.get(i).setEnabled(true);
				continue;
			}

			loggers.get(i).setEnabled(false);
		}
	}

	public static void enableAll() {
		for (Log log : loggers) {
			log.setEnabled(true);
		}
	}
}
