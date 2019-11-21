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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChangingCity extends AppCompatActivity implements Constants{

    private EditText searchLocation ;
    private static String CITY_NAME = "Search location1";
    private static final String TAG  = "ChangingCity";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;




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

        ArrayList<RecyclerViewCityItem> recycleViewItems = new ArrayList<>();
        recycleViewItems.add (new RecyclerViewCityItem(getResources().getString(R.string.saintPetersburg)));
        recycleViewItems.add (new RecyclerViewCityItem(getResources().getString(R.string.moscow)));
        recycleViewItems.add (new RecyclerViewCityItem(getResources().getString(R.string.london)));
        recycleViewItems.add (new RecyclerViewCityItem(getResources().getString(R.string.paris)));
        recycleViewItems.add (new RecyclerViewCityItem(getResources().getString(R.string.dubai)));
        recycleViewItems.add (new RecyclerViewCityItem(getResources().getString(R.string.newYork)));
        recycleViewItems.add (new RecyclerViewCityItem(getResources().getString(R.string.tokyo)));
        recycleViewItems.add (new RecyclerViewCityItem(getResources().getString(R.string.telAviv)));

      //  recycleViewItems.add (new RecyclerViewCityItem("beer-sheva"));

       // recycleViewItems.add (new RecyclerViewCityItem(getResources().getString(R.string.newYork)));



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        adapter = new CityAdapter(recycleViewItems,this);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void restoreData (Bundle savedInstanceState) {
        Log.i ( TAG , "on11 "+savedInstanceState);

        if (savedInstanceState == null)
            return;
        searchLocation.setText(savedInstanceState.getString(CITY_NAME, "Search location!!"));

        Log.i ( TAG , "jjjjj ");
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



//ListView listView = findViewById(R.id.listView);
//        ArrayList<String> posts = new ArrayList<>();
//        posts.add(getString(R.string.saintPetersburg));
//        posts.add(getString(R.string.moscow));
//        posts.add(getString(R.string.london));
//        posts.add(getString(R.string.paris));
//        posts.add(getString(R.string.dubai));
//        posts.add(getString(R.string.newYork));
//        posts.add(getString(R.string.tokyo));
//        posts.add(getString(R.string.telAviv));
//
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, posts);
//        listView.setAdapter(arrayAdapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                cityName = (String) listView.getItemAtPosition(position);
//
//                Log.i ( TAG , "cityName = "+ cityName);
//
//                 intent = new Intent(ChangingCity.this ,MainActivity.class);
////
////                 intent.putExtra(TEXT,cityName);
////                 intent.putExtra(TEMP,temp);
////                 intent.putExtra(PRESSURE,pressure);
////                 intent.putExtra(WIND_SPEED,windSpeed);
////
////                 startActivity(intent);
//            }
//        });


