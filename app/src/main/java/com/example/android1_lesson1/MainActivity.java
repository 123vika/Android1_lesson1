package com.example.android1_lesson1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Constants{

    private static final String TAG  = "MainActivity";
    TextView cityTextView;
    String cityName;
    int    temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLog("onCreate");
        setContentView(R.layout.activity_main);

        if (getIntent().getExtras()!=null) {
            cityName = getIntent().getExtras().getString(TEXT); // получить данные из Intent
            temp     = getIntent().getExtras().getInt(TEMP);
        }
        else {
            cityName = "Moscow";
        }

        Log.i ( TAG , "cityName = "+ cityName);
        Log.i ( TAG , "temp = "+ temp);

        cityTextView = findViewById(R.id.cityTextView);
        cityTextView.setText(cityName);


        findViewById(R.id.changeLocation).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openChangingCity();
            }
        });
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
        showLog("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showLog("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        showLog("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showLog("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showLog("onDestroy");
    }

    private void showLog(String text) {
        Log.d("Start", text);
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
        Log.i(TAG, text);
    }
}
