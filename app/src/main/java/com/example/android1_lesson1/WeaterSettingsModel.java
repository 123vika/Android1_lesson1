package com.example.android1_lesson1;

public class WeaterSettingsModel {

    String cityName = "Search location!";
    boolean showWind = true;
    boolean showPressure = true;

    private static WeaterSettingsModel instance;

    public static WeaterSettingsModel getInstance(){
        instance = instance == null? new WeaterSettingsModel():instance;
        return instance;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public boolean isShowWind() {
        return showWind;
    }

    public void setShowWind(boolean showWind) {
        this.showWind = showWind;
    }

    public boolean isShowPressure() {
        return showPressure;
    }

    public void setShowPressure(boolean showPressure) {
        this.showPressure = showPressure;
    }
}
