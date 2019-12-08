package com.example.android1_lesson1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.android1_lesson1.model.WeatherModel;


public class MainActivity extends AppCompatActivity implements Constants,WeatherProviderListener{

    private String url = "https://google.com";

    private static final String TAG  = "MainActivity";
    TextView cityTextView;
    TextView tempTextView;
    TextView pressTextView;
    TextView windTextView;

    SensorManager manager;
    SensorEventListener listener;

    WeatherProvider weatherProvider;

    private RecyclerView weekDayView;

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    WeatherProviderListener weatherProviderListener = new WeatherProviderListener() {
        @Override
        public void updateWeather(WeatherModel model) {

            Log.d("hhhh1", "updateW");
            Log.d("hhhh1", "temp "+model.getMain().getTemp());

            Log.d("hhhh1", "temp "+model.getMain().getPressure());
            Log.d("hhhh1", "temp "+model.getWind().getSpeed());

            String temp = model.getMain().getTemp();
            ((TextView) findViewById(R.id.tempTextView2)).setText(temp);

            String press = Double.toString(model.getMain().getPressure());
            ((TextView) findViewById(R.id.pressureTextView2)).setText(press);

            String wind = Double.toString(model.getWind().getSpeed());
            ((TextView) findViewById(R.id.windSpeedTextView2)).setText(wind);
        }
    };

    String cityName;
    int temp;
    int pressure;
    int windSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLog("onCreate");
        setContentView(R.layout.activity_main);

        if (getIntent().getExtras()!=null) {
            cityName = getIntent().getExtras().getString(TEXT); // получить данные из Intent
            temp     = getIntent().getExtras().getInt(TEMP);
            pressure = getIntent().getExtras().getInt(PRESSURE);
            windSpeed = getIntent().getExtras().getInt(WIND_SPEED);
        }
        else {
            //
            // default value
            //
            cityName = "Moscow";
            temp = 0;
            pressure = 0;
            windSpeed = 0;
        }
        Log.i ( TAG , "cityName = "+ cityName);
        Log.i ( TAG , "temp = "+ temp);
        Log.i ( TAG , "pressure = "+ pressure);
        Log.i ( TAG , "windSpeed = "+ windSpeed);

        cityTextView = findViewById(R.id.cityTextView);
        cityTextView.setText(cityName);

        tempTextView =findViewById(R.id.tempTextView2);
        Log.i ( TAG , "11 = "+ tempTextView);
        tempTextView.setText(String.valueOf(temp));

        pressTextView = findViewById(R.id.pressureTextView2);
        Log.i ( TAG , "22  = "+ pressTextView);
        Log.i ( TAG , "pressTextView = "+ pressTextView);
        pressTextView.setText(String.valueOf(pressure));

        windTextView = findViewById(R.id.windSpeedTextView2);
        Log.i ( TAG , "33 = "+ windTextView);
        windTextView.setText(String.valueOf(windSpeed));

        Log.i ( TAG , "cityName call whether = "+ cityName);

        weatherProvider = new WeatherProvider(cityName);
        weatherProvider = WeatherProvider.getInstance(cityName);
        weatherProvider.addListener(weatherProviderListener);
        weatherProvider.setCityName(cityName);

        findViewById(R.id.changeLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChangingCity();
            }
        });

//        ArrayList<WeekDayItem> weekDayItems = new ArrayList<>();
//
//        weekDayItems.add (new WeekDayItem(getString(R.string.monday),5));
//        weekDayItems.add (new WeekDayItem(getString(R.string.tuesday),10));
//        weekDayItems.add (new WeekDayItem(getString(R.string.wednesday),12));
//        weekDayItems.add (new WeekDayItem(getString(R.string.thursday),9));
//        weekDayItems.add (new WeekDayItem(getString(R.string.friday),15));
//        weekDayItems.add (new WeekDayItem(getString(R.string.saturday),13));
//        weekDayItems.add (new WeekDayItem(getString(R.string.sunday),8));
//
//        weekDayView = findViewById(R.id.weekDayRecView);
//        weekDayView.setHasFixedSize(true);
//        adapter = new WeekDayAdapter(weekDayItems,this);
//        layoutManager = new LinearLayoutManager(this);
//
//        weekDayView.setAdapter(adapter);
//        weekDayView.setLayoutManager(layoutManager);
    }

    void openChangingCity (){
        startActivity(new Intent(this,ChangingCity.class));
    }

    public void changeCity(View view) {
        Log.d("Start","city");
        Intent city = new Intent(MainActivity.this, ChangingCity.class);
        startActivity(city);
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadPage();
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);

        Sensor sensorTemp = manager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        Sensor sensorHum = manager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        if ((sensorTemp==null)&&(sensorHum== null))
            return;


      //  if (sensorHum== null)
      //      return;
        listener = new SensorEventListener() {
            @Override

            public void onSensorChanged(SensorEvent sensorEvent) {

                switch (sensorEvent.sensor.getType()){
                    case Sensor.TYPE_AMBIENT_TEMPERATURE:
                        Log.i("SENSORS", "TEMP= " + sensorEvent.values[0]);
                        break;

                    case Sensor.TYPE_RELATIVE_HUMIDITY:
                        Log.i("SENSORS", "HUM= " + sensorEvent.values[0]);
                        break;

                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
        manager.registerListener(listener,sensorTemp, 1000);
        manager.registerListener(listener,sensorHum, 1000);
    }

    private void loadPage() {
        new AsyncTask<String, Integer, String>() {   // AsyncTask < String, Integer, String> task = new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... strings) {
                try {
                    Log.i("THREAD", Thread.currentThread().getName());  // узнаём в каком мы потоке
                    URL uri = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(10000);
                    connection.connect();
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        return in.lines().collect(Collectors.joining("\n"));
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(String result) {
                if (result != null) {
                    ((WebView) findViewById(R.id.page)).loadData(result, "text/html; charset=utf-8", "itf-8");
                }
                super.onPostExecute(result);
            }
        }.execute(url);
    }

    @Override
    protected void onPause(){
            if (listener != null) {
                manager.unregisterListener(listener);
                listener = null;
            }
        super.onPause();
        //showLog("onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
       // showLog("onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
       // showLog("onDestroy");
    }
    private void showLog(String text) {
        Log.d("Start", text);
       // Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
        Log.i(TAG, text);
    }
    @Override
    public void updateWeather(WeatherModel model){

        //    Log.i ( "hhhh" , "hhhh ");
        Log.d("hhhh", "updateW");

        ((TextView) findViewById(R.id.tempTextView)).setText(temp);
    }
}
