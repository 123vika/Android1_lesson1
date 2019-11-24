package com.example.android1_lesson1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.content.Intent;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Pattern;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

public class ChangingCity extends AppCompatActivity  implements Constants    {

    private TextInputEditText inputCityName;
    private static String CITY_NAME = "Search location1";
    private static final String TAG  = "ChangingCity";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changing_city);

        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBack();
            }
        });


        Pattern patternCityName = Pattern.compile("^[-A-Za-z ]+$");
        inputCityName = findViewById(R.id.input_city_name);
        inputCityName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus)
                    return;
                TextView textView = (TextView) view;
                if (patternCityName.matcher(textView.getText().toString()).matches()) {
                    ((TextView) view).setError(null);
                } else {
                    ((TextView) view).setError("This is not city");
                }
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


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        adapter = new CityAdapter(recycleViewItems,this);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);


        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayout.VERTICAL);
        itemDecoration.setDrawable(getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(itemDecoration);
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


