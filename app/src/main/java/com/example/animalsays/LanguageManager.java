package com.example.animalsays;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import java.util.Locale;


/**
 * Class that manage the current language and the other one that is available
 * using shared preferences and context
 */
public class LanguageManager
{
    private final Context ct;
    private final SharedPreferences sharedPreferences;

    /**
     * Language manager with context as ctx
     * getting the language available from the shared preferences
     * @param ctx
     */
    public LanguageManager(Context ctx)
    {
        ct = ctx;
        sharedPreferences = ct.getSharedPreferences("LANG", Context.MODE_PRIVATE);
    }

    /**
     * setting the language to the code we got
     * @param code
     */
    public void setLang(String code)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lang",code);
        editor.apply();
    }

    /**
     * Update the current code (language) accordingly
     * @param code
     */
    public void updateResource(String code)
    {
        Locale locale = new Locale(code);
        Locale.setDefault(locale);
        Resources resources = ct.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        setLang(code);
    }
}
