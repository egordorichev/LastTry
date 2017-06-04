package org.egordorichev.lasttry;

public class Args {
	public static String world = "test";
	public static String player = "test";
	public static int seed = 512;

	private static int i;
	private static String arg;
	private static String[] arguments;

	public static void parse(String[] args) throws Exception {
		arguments = args;

		for (i = 0; i < args.length; i++) {
			arg = args[i];

			switch (arg) {
				case "-d":
					LastTry.release = false;
				break;
				case "-dw":
					// todo
				break;
				case "-dp":
					// todo
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
			}
		}
	}

	private static void checkForArgument(String error) throws Exception {
		if (arguments.length - 1 == i) {
			throw new Exception(error);
		}
	}
}