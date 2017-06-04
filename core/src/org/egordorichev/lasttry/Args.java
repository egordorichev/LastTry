package org.egordorichev.lasttry;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.egordorichev.lasttry.util.Util;

import java.io.File;

public class Args {
	public static String world = "test";
	public static String player = "test";
	public static int seed = 512;

	private static int i;
	private static String arg;
	private static String[] arguments;

	public static void parse(String[] args, LwjglApplicationConfiguration config) throws Exception {
		arguments = args;

		/*
		if (args.length > 0) {
            List<String> argList = Arrays.asList(args);
            if (argList.contains("-d")) {
                LastTry.release = false;
                if (Util.isWindows()) {
                    System.setSecurityManager(new ExitDumper());
                }
            }
            if (argList.contains("-wd")) {
                Util.delete(new File("data" + File.separator + "worlds"));
            }
            if (argList.contains("-f")) {
                config.fullscreen = true;
            }if (argList.contains("-nl")) {
                LastTry.noLight = true;
            }
        }
		 */

		for (i = 0; i < args.length; i++) {
			arg = args[i];

			switch (arg) {
				case "-d":
					LastTry.release = false;

					if (Util.isWindows()) {
						System.setSecurityManager(new ExitDumper());
					}
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
				case "-nl":
					LastTry.noLight = true;
				break;
				case "-f":
					config.fullscreen = true;
				break;
			}
		}
	}

	private static void checkForArgument(String error) throws Exception {
		if (arguments.length - 1 == i) {
			throw new Exception(error);
		}
	}

	private static class ExitDumper extends SecurityManager {
		@Override
		public void checkExit(int status) {
			Thread.dumpStack();
		}
	}
}