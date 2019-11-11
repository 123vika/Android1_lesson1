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

import java.util.ArrayList;

public class ChangingCity extends AppCompatActivity {

    private EditText searchLocation ;
    private static String CITY_NAME = "Search location1";

    private static final String TAG  = "ChangingCity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changing_city);

       // Log.i ( TAG , "onCreate1");

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
        posts.add("Saint Petersburg");
        posts.add("London");
        posts.add("Paris");
        posts.add("Dubai");
        posts.add("New York");
        posts.add("Tokyo");
        posts.add("Tel Aviv");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, posts);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }
    private void restoreData (Bundle savedInstanceState) {
        Log.i ( TAG , "on11 "+savedInstanceState);
        if (savedInstanceState == null)
            return;
       // searchLocation.setText("ddddddddddddddd");
        searchLocation.setText(savedInstanceState.getString(CITY_NAME, "Search location!!"));

        Log.i ( TAG , "jjjjj ");
       // searchLocation.setText(savedInstanceState.getString(CITY_NAME, "Search location"));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CITY_NAME,searchLocation.getText().toString() );
     //   Log.i ( TAG , "on"+savedInstanceState);
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
