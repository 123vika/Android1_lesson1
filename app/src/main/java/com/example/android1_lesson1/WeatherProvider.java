package com.example.android1_lesson1;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.android1_lesson1.model.WeatherModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class WeatherProvider {

    private Set<WeatherProviderListener> listeners;
    Handler handler = new Handler() ;
    private Timer timer = null;
    private static WeatherProvider instanse = null;

    private  final Object lockCityName = new Object();

    String cityName;

    WeatherFragment weatherFragment = new WeatherFragment();

    {
        Log.i ( "WeatherProvider" , "WeatherProvider");
    }

  //  public WeatherProvider(String cityName) {
    //    this.cityName = cityName;
    //}

    // Log.i ( "hhh" , "cityN");


    public static WeatherProvider getInstance(String cityName){
        instanse = instanse == null? new WeatherProvider(cityName):instanse;
        return instanse;
    }
    public void addListener (WeatherProviderListener listener){
        if (!listeners.contains(listener)){
            listeners.add(listener);
        }
    }

    public void changeCity(String cityName){
        this.cityName = cityName;
       // WeatherModel model = getWeather(cityName);
    }

    public String getCity(){
        synchronized (lockCityName) {
            return this.cityName;
        }
    }

    public void  setCityName(String cityName){
        synchronized (lockCityName) {
            this.cityName = cityName;
           // WeatherModel model = getWeather(cityName);

        }
    }

    public void removeListener(WeatherProviderListener listener){
        if (listeners.contains(listener)){
            listeners.remove(listener);
        }
    }

    public WeatherProvider(String cityName){

        Log.d("wwwww", "wwwww");
        listeners = new HashSet<>();
        this.cityName =cityName;
        startData(cityName);
    }

    private WeatherModel getWeather(String cityName) {

      //  this.cityName = cityName;
        Log.d("wwwww", cityName);

        String uriString = "https://api.openweathermap.org/data/2.5/weather?q=" + getCity() + "&appid=5caf9a8cffa7b650f3c63934598faea2";
        WeatherModel model = null;
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL (uriString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(10000);

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                final String result = in.lines().collect(Collectors.joining("\n"));
                Gson gson = new Gson();
                model = gson.fromJson(result,WeatherModel.class);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return model;
    }

    private void startData(String cityName){

        this.cityName = cityName;

        Log.d("wwwww", "startData cityname "+cityName);

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run (){

                Log.d("wwwww", "onfly cityname "+cityName);

                WeatherModel model = getWeather(cityName);

                Log.d("wwwww", "temp "+model.getMain().getTemp());


                if (model== null)
                    return;

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("wwwww", "startData1");

                        for (WeatherProviderListener listener : listeners) {
                            Log.d("wwwww", "temp "+model.getMain().getTemp());
                            listener.updateWeather(model);
                            Log.d("wwwww", "inside for cityname"+cityName);
                           // weatherFragment.updateWeather(model);
                        }
                    }
                });
            }
        },2000,10000);
    }
    void stop(){
        if (timer != null)timer.cancel();
        listeners.clear();
    }
}
