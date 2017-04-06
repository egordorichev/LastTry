package org.egordorichev.lasttry.core.crash;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.Version;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

public class Crash {
	private static String ERROR_MESSAGE = "LastTry had stopped running, because it encountered a problem.\n"
		+ "\nPlease, copy and report the entire text to email egordorichev@gmail.com or\n"
		+ "submit an issue https://github.com/egordorichev/LastTry/issues\n\n";

	public static void report(Thread thread, Throwable throwable) {
		System.err.println("LastTry had crashed!\n-------------------");
		System.err.println(ERROR_MESSAGE);

		System.err.println("--- BEGIN ERROR REPORT ---");
		System.err.println("LastTry version: " + Version.full);
		System.err.println("OS: " + System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version"));
		System.err.println("Java version: " +  System.getProperty("java.version") + ", " + System.getProperty("java.vendor"));
		System.err.println("Java VM version: " +  System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor"));
		System.err.println("Java VM flags: " + getJavaVMFlags());
		System.err.println("Memory: " + printMemoryUsage());
		System.err.println("--- END ERROR REPORT ---");
		System.err.flush();
	}

	private static String printMemoryUsage() {
		Runtime runtime = Runtime.getRuntime();
		long i = runtime.maxMemory();
		long j = runtime.totalMemory();
		long k = runtime.freeMemory();
		long l = i / 1024L / 1024L;
		long i1 = j / 1024L / 1024L;
		long j1 = k / 1024L / 1024L;

		return k + " bytes (" + j1 + " MB) / " + j + " bytes (" + i1 + " MB) up to " + i + " bytes (" + l + " MB)";
	}

	private static String getJavaVMFlags() {
		RuntimeMXBean runtimemxbean = ManagementFactory.getRuntimeMXBean();
		List<String> list = runtimemxbean.getInputArguments();
		int i = 0;
		StringBuilder stringbuilder = new StringBuilder();

		for (String s : list) {
			if (s.startsWith("-X")) {
				if (i++ > 0) {
					stringbuilder.append(" ");
				}

				stringbuilder.append(s);
			}
		}

		return String.format("%d total; %s", new Object[] { Integer.valueOf(i), stringbuilder.toString() });
	}
}
