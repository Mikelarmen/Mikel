package com.example.rememberingreminding;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void set(String key,String value) {
        prefs.edit().putString(key, value).commit();
    }

    public String get(String key) {
        String value = prefs.getString(key,"");
        return value;
    }
}
