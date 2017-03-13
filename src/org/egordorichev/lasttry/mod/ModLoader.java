package org.egordorichev.lasttry.mod;

import org.egordorichev.lasttry.LastTry;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModLoader {
	private ArrayList<Mod> mods;
	
	public ModLoader() {
		mods = new ArrayList<>();
	}

	public void load() {
		File[] mods = new File("assets/mods").listFiles();

		for(int i = 0; i < mods.length; i++) {
			if(!mods[i].getName().endsWith(".jar")) {
				continue;
			}

			try {
				JarFile jarFile = new JarFile(mods[i]);
				Enumeration enumeration = jarFile.entries();
				URL[] urls = new URL[]{
						new URL("jar:file:" + mods[i].getAbsolutePath() + "!/")
				};

				URLClassLoader urlClassLoader = URLClassLoader.newInstance(urls);

				while(enumeration.hasMoreElements()) {
					JarEntry jarEntry = (JarEntry) enumeration.nextElement();

					if(jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class")) {
						continue;
					}

					String className = jarEntry.getName().substring(0, jarEntry.getName().length() - 6);
					className = className.replace('/', '.');

					Class aClass = urlClassLoader.loadClass(className);

					if(Mod.class.isAssignableFrom(aClass) && aClass != LuaMod.class && aClass != Mod.class) {
						Mod mod = (Mod) aClass.newInstance();
						mod.onLoad();

						this.mods.add(mod);
					}
				}
			} catch(Exception exception) {
				LastTry.handleException(exception);
			}
		}
	}

	public Mod getMod(String name) {
		for(Mod mod : this.mods) {
			if(mod.getName().equals(name)) {
				return mod;
			}
		}
		
		return null;
	}
}
