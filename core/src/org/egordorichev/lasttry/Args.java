package org.egordorichev.lasttry;

import org.egordorichev.lasttry.util.Util;

import java.io.File;

public class Args {
	public static String world = "test";
	public static String player = "test";
	public static int seed = 512;

	/**
	 * Current argument index
	 */
	private static int i;
	/**
	 * Current argument
	 */
	private static String arg;
	/**
	 * Arguments
	 */
	private static String[] arguments;

	/**
	 * Parses given arguments
	 *
	 * @param args   arguments, received by main()
	 * @param config App config
	 * @throws Exception Exception, containing a parse error
	 */
	public static void parse(String[] args, Object config) throws Exception {
		arguments = args;

		boolean exitDump = false;
		for (i = 0; i < args.length; i++) {
			arg = args[i];
			switch (arg) {
				case "-d":
					LastTry.release = false;
					break;
				case "-dw":
					Util.delete(new File("data" + File.separator + "worlds"));
					break;
				case "-dp":
					Util.delete(new File("data" + File.separator + "players"));
					break;
				case "-s":
					checkForArgument("Expected seed after -s");

					try {
						seed = Integer.valueOf(args[++i]);
						LastTry.random.setSeed(seed);
					} catch (Exception exception) {
						throw new Exception("Seed is not a valid number");
					}
					break;
				case "-w":
					checkForArgument("Expected world name after -w");
					world = args[++i];
					break;
				case "-p":
					checkForArgument("Expected player name after -p");
					player = args[++i];
					break;
				case "-el":
					LastTry.noLight = false;
					break;

				case "-nl":
					LastTry.noLight = true;
					break;

				case "-extd":
					exitDump = true;
					break;
				case "-f":
					set(config, "fullscreen", true);
					break;
				default:
					throw new Exception("Unknown arg " + arg);
			}

		}
		if (!LastTry.release && exitDump && Util.isWindows()) {
			System.setSecurityManager(new ExitDumper());
		}
	}

	private static void set(Object instance, String field, boolean value) {
		// THIS IS TEMPORARY
		// The issue is AFAIK the core gradle doesn't load the following:
		//
		// com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
		//
		// So do we update the gradle file or change the way its passed?

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
}