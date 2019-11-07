package com.example.android1_lesson1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ChangingCity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changing_city);

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
}
