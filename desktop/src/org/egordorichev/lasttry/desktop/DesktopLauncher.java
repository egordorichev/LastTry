package org.egordorichev.lasttry.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.util.Util;

public class DesktopLauncher {
    /** App main */
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = 800;
        config.height = 600;
        config.vSyncEnabled = true;
        config.resizable = false;
        config.addIcon("Icon.png", Files.FileType.Internal);

        if (args.length > 0) {
            List<String> argList = Arrays.asList(args);
            if (argList.contains("-d")) {
                LastTry.release = false;
                if (Util.isWindows()) {
                    // FIXME: exception on linux
                    // Print the stack-trace when the program exits
                    System.setSecurityManager(new ExitDumper());
                }
            }
            if (argList.contains("-wd")) {
                Util.delete(new File("data" + File.separator + "worlds"));
            }
        }

        new LwjglApplication(new LastTry(), config);
    }

    private static class ExitDumper extends SecurityManager {
        @Override
        public void checkExit(int status) {
            Thread.dumpStack();
        }
    }
}