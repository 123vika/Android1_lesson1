package com.example.android1_lesson1;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class WeatherProvider {

    private Set<WeatherProviderListener> listeners;
    Handler handler = new Handler() ;
    private Timer timer = null;
    private static WeatherProvider instanse = null;
    private Retrofit retrofit;
    private OpenWeather weatherApi;

    private  final Object lockCityName = new Object();

    String cityName;

    WeatherFragment weatherFragment = new WeatherFragment();

    {
        Log.i ( "WeatherProvider" , "WeatherProvider1");
    }

 // public WeatherProvider(String cityName) {
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

    public void  setCityName(String cityName) throws Exception {
        synchronized (lockCityName) {
            this.cityName = cityName;
            WeatherModel model = getWeather(cityName);

        }
    }

    public void removeListener(WeatherProviderListener listener){
        if (listeners.contains(listener)){
            listeners.remove(listener);
        }
    }


    //http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=5caf9a8cffa7b650f3c63934598faea2
    //http://api.openweathermap.org/data/2.5/weather

    public WeatherProvider(String cityName){
       this.cityName =cityName;
        Log.d("wwwww", "constructor");
        listeners = new HashSet<>();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherApi = retrofit.create(OpenWeather.class);

        startData(cityName);
    }

    interface OpenWeather{
        @GET
        Call<WeatherModel> getWeather(@Path ("data/2.5/weather") @Query("q")String q,@Query("appid")String key);
    }

    private WeatherModel getWeather(String cityName) throws Exception  {

        Log.d("wwwww", "getWeather1 " + cityName);

    //   Call <WeatherModel> call = weatherApi.;
       Call<WeatherModel> call = weatherApi.getWeather(cityName + ",ru","5caf9a8cffa7b650f3c63934598faea2");

       Log.d("wwwww", "getWeather " + cityName);

       Response<WeatherModel>response = call.execute();
    //   call.enqueue(new Callback<WeatherModel>() {
       //    @Override
       //    public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
       //        Log.d("wwwww", "getWeather onres " + cityName);
       //    }
        //   @Override
        //   public void onFailure(Call<WeatherModel> call, Throwable t) {
         //      Log.d("wwwww", "getWeather onf " + cityName);
         //  }
      // });

      // return null;

       if(response.isSuccessful())
           return response.body();
       else {
          Log.d("wwwww", "getWeather error " + cityName);
           throw new Exception(response.errorBody().string(), null);
         //   throw new Exception("wwww getw error");
        }
    }

    private void startData(String cityName){

        this.cityName = cityName;

        Log.d("wwwww", "startData cityname "+cityName);

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run (){

                Log.d("wwwww", "startData before getweather "+cityName);

                try {
                    WeatherModel model = getWeather(cityName);
                    Log.d("wwwww", "weather model= "+model);
                if (model== null) {
                    Log.d("wwwww", "weather model null "+cityName);
                    return;

                }

                    Log.d("wwwww", "startData 111111 "+cityName);

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        Log.d("wwwww", "startData1");

                        for (WeatherProviderListener listener:listeners){
                               Log.d("wwwww", "temp "+model.getMain().getTemp());
                               listener.updateWeather(model);
                               Log.d("wwwww", "inside for cityname"+cityName);
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                    Log.d("wwwww", "exception"+cityName);
            }
            }

        },2000,10000);
    }
    void stop(){
        if (timer != null)timer.cancel();
        listeners.clear();
    }
}
  // appid=5caf9a8cffa7b650f3c63934598faea2

//Log.d("wwwww", "onfly cityname "+cityName);

//       Log.d("wwwww", "temp "+model.getMain().getTemp());

//      Log.d("wwwww", "inside for cityname"+cityName);
