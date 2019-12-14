package com.example.android1_lesson1;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class PreferenceWrapper {

    private static PreferenceWrapper _instance;
    SharedPreferences.Editor editor;

    static PreferenceWrapper getPreference (Activity activity){
        _instance = _instance == null? new PreferenceWrapper(activity):_instance;
        return _instance;
    }
    String cityName ;
    public String getCityName(){
        return cityName;
    }
    public void setCityName(Activity activity, String cityName){
        this.cityName = cityName;
        activity.getSharedPreferences("city", MODE_PRIVATE).edit().putString("city",cityName);
    }
    public void apply (Activity activity){
        editor.apply();
    }
    PreferenceWrapper(Activity activity){
        cityName = activity.getSharedPreferences("city",MODE_PRIVATE).getString("city",cityName);
        editor = activity.getSharedPreferences("city",MODE_PRIVATE).edit();
    }
}
