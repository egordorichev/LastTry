package org.egordorichev.lasttry.mod;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.state.MenuState;
import org.egordorichev.lasttry.util.Util;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModLoader {
    /**
     * List of loaded mods
     */
    private List<Mod> mods = new ArrayList<>();

    /**
     * Lookups and loads all mods from "assets/mods" directory
     */
    public void load() {
        File modDirectory = new File("mods/");

        if(!Util.fileExists(modDirectory.getPath())){
			LastTry.logDebug("There is no mod directory so one is being created!");
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
     *
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
                JarEntry jarEntry = (JarEntry) enumeration.nextElement();

                if (jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class")) {
                    continue;
                }

                String className = jarEntry.getName().substring(0, jarEntry.getName().length() - 6);
                className = className.replace('/', '.');

                Class<?> aClass = urlClassLoader.loadClass(className);

                if (Mod.class.isAssignableFrom(aClass) && aClass != Mod.class) {
                    Mod mod = (Mod) aClass.newInstance();
                    mod.onLoad();

                    this.mods.add(mod);
                }
            }
        } catch (Exception exception) {
            LastTry.handleException(exception);
            LastTry.log.warn("Failed to load " + file.getAbsolutePath().replace('/', '.') + " mod");
        }
    }

    /**
     * Unloads all mods
     */
    public void unload() {
        for (Mod mod : this.mods) {
            mod.onUnload();
        }
    }

    /**
     * @param name mod name to lookup
     * @return mod with given name, or null, if it is not found
     */
    public Mod getMod(String name) {
        // TODO: Instead of a iteration use a name lookup map.

        for (Mod mod : this.mods) {
            if (mod.getName().equals(name)) {
                return mod;
            }
        }

        return null;
    }
}
