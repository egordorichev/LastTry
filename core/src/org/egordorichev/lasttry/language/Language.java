package org.egordorichev.lasttry.language;

import java.util.Locale;
import java.util.ResourceBundle;

public class Language {
    public static ResourceBundle text;
    
    public static void load(Locale locale) {
        text = ResourceBundle.getBundle("language", locale);
    }
}