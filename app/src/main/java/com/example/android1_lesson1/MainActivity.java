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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG  = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(),"onCreate",Toast.LENGTH_LONG).show();
        Log.i ( TAG , "onCreate");
        setContentView(R.layout.activity_main);

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
        Log.d("Start","onStart");
        Toast.makeText(getApplicationContext(),"onStart",Toast.LENGTH_LONG).show();
        Log.i(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Start","onResume");
        Toast.makeText(getApplicationContext(),"onResume",Toast.LENGTH_LONG).show();
        Log.i(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Start","onPause");
        Toast.makeText(getApplicationContext(),"onPause",Toast.LENGTH_LONG).show();
        Log.i(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Start","onStop");
        Toast.makeText(getApplicationContext(),"onStop",Toast.LENGTH_LONG).show();
        Log.i(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Start","onDestroy");
        Toast.makeText(getApplicationContext(),"onDestroy",Toast.LENGTH_LONG).show();
        Log.i(TAG,"onDestroy");
    }


}
