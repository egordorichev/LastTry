package org.egordorichev.lasttry.mod;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.core.Crash;
import org.egordorichev.lasttry.util.Log;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModLoader {
    /**
     * HashMap of loaded mops.
     * Key - String, Mod name.
     * Value - Mod, Mod object.
     */
    private HashMap<String, Mod> modHashMap = new HashMap<>();

    /** Lookups and loads all mods from "mods" directory */
    public void load() {
		File modDirectory = new File("mods/");

		if (!modDirectory.mkdir()) {
			try {
				modDirectory.createNewFile();
			} catch (Exception exception) {
				Crash.report(Thread.currentThread(), exception);
			}
		} else {
			Log.info("There's no mods directory so one will be created!");
		}

		File[] mods = modDirectory.listFiles();

        for (int i = 0; i < mods.length; i++) {
            if (!mods[i].getName().endsWith(".jar")) {
                continue;
            }

            this.loadMod(mods[i]);
        }
    }

    /**
     * Loads single mod
     * @param file mod file
     */
    private void loadMod(File file) {
        try (JarFile jarFile = new JarFile(file)) {
            URL[] urls = new URL[]{
            		new URL("jar:file:" + file.getAbsolutePath() + "!/")
            };

            URLClassLoader urlClassLoader = URLClassLoader.newInstance(urls);
            Enumeration<JarEntry> enumeration = jarFile.entries();

            while (enumeration.hasMoreElements()) {
                JarEntry jarEntry = enumeration.nextElement();

                if (jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class")) {
                    continue;
                }

                String className = jarEntry.getName().substring(0, jarEntry.getName().length() - 6);
                className = className.replace('/', '.');

                Class<?> aClass = urlClassLoader.loadClass(className);

                if (Mod.class.isAssignableFrom(aClass) && aClass != Mod.class) {
                    Mod mod = (Mod) aClass.newInstance();
                    mod.onLoad();

                    this.modHashMap.put(mod.getName(), mod);
                }
            }
        } catch (Exception exception) {
            LastTry.handleException(exception);
	        Log.info("Failed to load " + file.getAbsolutePath().replace('/', '.') + " mod");
        }
    }

    /** Unloads all mods */
    public void unload() {
        this.modHashMap.entrySet().forEach(modHashMapKey -> {
        	modHashMap.get(modHashMapKey).onUnload();
        });

        this.modHashMap.clear();
    }

    /**
     * @param name mod name to lookup
     * @return mod with given name, or null, if it is not found
     */
    public Mod getMod(final String name) {
        return modHashMap.get(name);
    }
}
