package org.egordorichev.lasttry.language;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;
import java.util.Locale;

public class Language {
	public static I18NBundle text;

	public static void load(Locale locale) {
		FileHandle baseFileHandle = Gdx.files.internal("languages/language");
		text = I18NBundle.createBundle(baseFileHandle, locale);
	}
}