package com.example.android1_lesson1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.content.Intent;
import java.util.ArrayList;

public class ChangingCity extends AppCompatActivity implements Constants{

    private EditText searchLocation ;
    private static String CITY_NAME = "Search location1";
    private static final String TAG  = "ChangingCity";
    String cityName ;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changing_city);

        searchLocation = findViewById(R.id.searchLocation);

        restoreData(savedInstanceState);

        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBack();
            }
        });

        ListView listView = findViewById(R.id.listView);
        ArrayList<String> posts = new ArrayList<>();
        posts.add(getString(R.string.saintPetersburg));
        posts.add(getString(R.string.moscow));
        posts.add(getString(R.string.london));
        posts.add(getString(R.string.paris));
        posts.add(getString(R.string.dubai));
        posts.add(getString(R.string.newYork));
        posts.add(getString(R.string.tokyo));
        posts.add(getString(R.string.telAviv));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, posts);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cityName = (String) listView.getItemAtPosition(position);

                Log.i ( TAG , "cityName = "+ cityName);

                 intent = new Intent(ChangingCity.this ,MainActivity.class);

                 intent.putExtra(TEXT,cityName);
                 intent.putExtra(TEMP,5);

                 startActivity(intent);
            }
        });
    }

    private void restoreData (Bundle savedInstanceState) {
        Log.i ( TAG , "on11 "+savedInstanceState);
        
        if (savedInstanceState == null)
            return;
        searchLocation.setText(savedInstanceState.getString(CITY_NAME, "Search location!!"));

        Log.i ( TAG , "jjjjj ");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CITY_NAME,searchLocation.getText().toString() );
    }

    void onBack(){

        //TODO: save state
        finish();
    }

    @Override
    public void onBackPressed() {
        // TODO : save state
        super.onBackPressed();
    }
}

// интарная ....

// if (instance == null)
//     instance = new WeaterSettingsModel ();
//        тоже самое
//  instance =(instance ==null? new WeaterSettingsModel () : instance);