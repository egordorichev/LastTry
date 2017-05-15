package org.egordorichev.lasttry.language;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.core.Crash;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public final class LocalizationUtil {
	public static void localize(Class clazz, Class ... targetClazz){
		List<Field> localizableFields = new ArrayList<>();

		for (int i = 0; i < clazz.getFields().length; i++){
			Field field = clazz.getFields()[i];

			for (int j = 0; j < targetClazz.length; j++){
				if(Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()) && field.getType() == targetClazz[j]){
					localizableFields.add(field);
					break;
				}
			}
		}

		StringBuilder text = new StringBuilder();

		text.append("\n# ")
				.append(clazz.getPackage().toString().substring(8))
				.append("\n")
				.append("# ")
				.append(clazz.getSimpleName())
				.append(".java")
				.append("\n");

		for (int i = 0; i < localizableFields.size(); i++){
			Field field = localizableFields.get(i);

			text.append("\t").append(field.getName()).append(" = ").append(capitalize(field.getName()).replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2")).append("\n");
		}

		try {
			Files.write(Paths.get(Gdx.files.internal("languages/language_en_US.properties").path()), text.toString().getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			Crash.report(Thread.currentThread(), e);
		}
	}

	private static String capitalize(String string){
		return Character.toUpperCase(string.charAt(0)) + string.substring(1);
	}
}
