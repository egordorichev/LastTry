package org.egordorichev.lasttry;

import org.egordorichev.lasttry.util.CallableWithError;
import org.egordorichev.lasttry.util.Files;
import org.egordorichev.lasttry.util.Util;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

public class Args {
	private static String world = "test";
	private static String player = "test";
	private static int seed = 512;

	/**
	 * Current argument index
	 */
	private static int i;
	/**
	 * Arguments
	 */
	private static String[] arguments;
	/**
	 * Argument map
	 */
	private static HashMap<String, Arg> argMap = new HashMap<>();
	/**
	 * App config
	 */
	public static Object conf;

	static {
		argMap.put("-h", new Arg("Prints help dialog", new CallableWithError() {
			@Override
			public void call() {
				System.out.println("LastTry " + LastTry.version.toString());
				System.out.println("Usage:");

				Iterator<?> it = argMap.entrySet().iterator();

				while (it.hasNext()) {
					HashMap.Entry<?,?> pair = (HashMap.Entry<?,?>) it.next();
					System.out.println("\t" + pair.getKey() + "\t\t" + ((Arg) pair.getValue()).getDescription());
					it.remove();
				}

				System.exit(0);
			}
		}));

		argMap.put("-d", new Arg("Enables debug mode", new CallableWithError() {
			@Override
			public void call() {
				LastTry.release = false;
			}
		}));

		
		argMap.put("-rfs", new Arg("Relative file storage mode", new CallableWithError() {
			@Override
			public void call() {
				LastTry.storeRelative = true;
			}
		}));

		argMap.put("-dw", new Arg("Deletes all worlds", new CallableWithError() {
			@Override
			public void call() {
				Util.delete(new File(Files.getWorldsDir()));
			}
		}));

		argMap.put("-dp", new Arg("Deletes all players", new CallableWithError() {
			@Override
			public void call() {
				Util.delete(new File(Files.getPlayersDir()));
			}
		}));

		argMap.put("-s", new Arg("Sets random seed", new CallableWithError() {
			@Override
			public void call() throws Exception {
				checkForArgument("Expected seed after -s");

				try {
					seed = Integer.valueOf(arguments[++i]);
					LastTry.random.setSeed(seed);
				} catch (Exception exception) {
					throw new Exception("Seed is not a valid number");
				}
			}
		}));

		argMap.put("-w", new Arg("Sets world to load", new CallableWithError() {
			@Override
			public void call() throws Exception {
				checkForArgument("Expected world name after -w");
				world = arguments[++i];
			}
		}));

		argMap.put("-p", new Arg("Sets player to load", new CallableWithError() {
			@Override
			public void call() throws Exception {
				checkForArgument("Expected player name after -p");
				player = arguments[++i];
			}
		}));

		argMap.put("-nl", new Arg("Disables lighting", new CallableWithError() {
			@Override
			public void call() {
				LastTry.noLight = true;
			}
		}));

		argMap.put("-f", new Arg("Enables fullscreen", new CallableWithError() {
			@Override
			public void call() {
				set(conf, "fullscreen", true);
			}
		}));
	}

	/**
	 * Parses given arguments
	 *
	 * @param args   arguments, received by main()
	 * @param config App config
	 * @throws Exception Exception, containing a parse error
	 */
	public static void parse(String[] args, Object config) throws Exception {
		arguments = args;
		conf = config;

		for (String arg : args) {
			Arg argRegistry = argMap.get(arg);

			if (argRegistry != null) {
				argRegistry.call();
			} else {
				System.err.println("Unknown argument: " + arg + ". Run with -h to get more info.");
				System.exit(0);
			}
		}

		if (!LastTry.release && Util.isWindows()) {
			/*
			 * THIS IS TEMPORARY
			 * The issue is that ExitDumper crashes the game on Windows 10
			 */
			//System.setSecurityManager(new ExitDumper());
		}
	}

	private static void set(Object instance, String field, boolean value) {
		/*
		 * THIS IS TEMPORARY
		 * The issue is AFAIK the core gradle doesn't load the following:
		 *
		 * com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
		 *
		 * So do we update the gradle file or change the way its passed?
		 */

		try {
			instance.getClass().getDeclaredField(field).set(instance, value);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks, that there is one more argument, otherwise, throws an Exception
	 *
	 * @param error Error message for the Exception
	 * @throws Exception Thrown if there is no more arguments
	 */
	private static void checkForArgument(String error) throws Exception {
		if (arguments.length - 1 == i) {
			throw new Exception(error);
		}
	}

	/**
	 * Used for dumping thread
	 */
	private static class ExitDumper extends SecurityManager {
		@Override
		public void checkExit(int status) {
			Thread.dumpStack();
		}
	}

	private static class Arg {
		/**
		 * Argument description
		 */
		private String description;
		/**
		 * Argument function
		 */
		private CallableWithError function;

		public Arg(String description, CallableWithError function) {
			this.description = description;
			this.function = function;
		}

		/**
		 * Calls argument
		 */
		public void call() {
			try {
				this.function.call();
			} catch (Exception exception) {
				exception.printStackTrace();
				LastTry.abort();
			}
		}

		/**
		 * @return Argument description
		 */
		public String getDescription() {
			return this.description;
		}
	}
}