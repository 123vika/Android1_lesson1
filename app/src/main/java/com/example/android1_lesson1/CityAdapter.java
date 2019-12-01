package com.example.android1_lesson1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.RecyclerViewViewHolder>
                        implements Constants {

    private ArrayList<RecyclerViewCityItem> arrayList;

    String cityName ;
    int    temp = 5;
    int pressure = 30;
    int windSpeed = 50;

    Context context;
    Intent  intent;

    public CityAdapter(ArrayList<RecyclerViewCityItem> arrayList,Context context){
        this.arrayList = arrayList;
        this.context = context;
        Log.d("adap", "mark4");
    }

    public  class RecyclerViewViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public TextView recyclerCity;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);

            Log.d("adap", "mark5");

            recyclerCity = itemView.findViewById(R.id.cityRecyclerTextView);

            itemView.setOnClickListener(this::onClick);
        }


        @Override
        public void onClick(View v) {

            Log.d("adap", "mark6");

            int position = getAdapterPosition();
            RecyclerViewCityItem recyclerViewCityItem = arrayList.get(position);

           // cityName = recyclerViewCityItem.getRecyclerCity();

            Log.d("adap", "mark6.1 "+recyclerViewCityItem.getRecyclerCity());




            intent = new Intent(context,MainActivity.class);
          //  intent = new Intent(ChangingCity.this ,MainActivity.class);
          //  intent.putExtra("city",RecyclerViewCityItem.getText());

           // context.startActivity(intent);

                 intent.putExtra(TEXT,recyclerViewCityItem.getRecyclerCity());
                 intent.putExtra(TEMP,temp);
                 intent.putExtra(PRESSURE,pressure);
                 intent.putExtra(WIND_SPEED,windSpeed);

                 context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate
                (R.layout.city_recycler_view, viewGroup,false);
        RecyclerViewViewHolder recyclerViewViewHolder = new RecyclerViewViewHolder(view);
        return recyclerViewViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewViewHolder recyclerViewViewHolder, int i) {
        RecyclerViewCityItem recycleViewItem = arrayList.get(i);

        Log.d("adap", "mark7");

        recyclerViewViewHolder.recyclerCity.setText(recycleViewItem.getRecyclerCity());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}