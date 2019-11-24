package com.example.android1_lesson1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeekDayAdapter extends RecyclerView.Adapter<WeekDayAdapter.RecyclerViewViewHolder>
        implements Constants {

    private ArrayList<WeekDayItem> arrayList;

    String cityName ;
    int    temp = 5;
    int pressure = 30;
    int windSpeed = 50;

    Context context;
    Intent intent;



    public WeekDayAdapter(ArrayList<WeekDayItem> arrayList,Context context){
        this.arrayList = arrayList;
        this.context = context;
        Log.d("adap", "mark4");
    }

    public  class RecyclerViewViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public TextView weekDay;
        public TextView weekTemp;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);

            Log.d("adap", "mark5");

            weekDay =  itemView.findViewById(R.id.weekDayTextView);
            weekTemp = itemView.findViewById(R.id.weekTempTextView);


            itemView.setOnClickListener(this::onClick);
        }


        @Override
        public void onClick(View v) {

            Log.d("adap", "mark6");

            int position = getAdapterPosition();
            WeekDayItem weekDayItem = arrayList.get(position);

            // cityName = recyclerViewCityItem.getRecyclerCity();

            Log.d("adap", "mark6.1 "+weekDayItem.getWeekDay());
            Log.d("adap", "mark6.1.1 "+weekDayItem.getTemp());




//            intent = new Intent(context,MainActivity.class);
//                                  //  intent = new Intent(ChangingCity.this ,MainActivity.class);
//                                  //  intent.putExtra("city",RecyclerViewCityItem.getText());
//
//                                  // context.startActivity(intent);
//
//            intent.putExtra(TEXT,recyclerViewCityItem.getRecyclerCity());
//            intent.putExtra(TEMP,temp);
//            intent.putExtra(PRESSURE,pressure);
//            intent.putExtra(WIND_SPEED,windSpeed);
//
//            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate
                (R.layout.week_day_view, viewGroup,false);

        RecyclerViewViewHolder recyclerViewViewHolder = new RecyclerViewViewHolder(view);

        return recyclerViewViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewViewHolder recyclerViewViewHolder, int i) {
        WeekDayItem recycleViewItem = arrayList.get(i);

        Log.d("adap", "mark7"+recycleViewItem.getTemp());
        Log.d("adap", "mark7"+recycleViewItem.getWeekDay());

        Log.d("adap", "mark7"+recyclerViewViewHolder.weekDay);
        Log.d("adap", "mark7"+recyclerViewViewHolder.weekTemp);

        recyclerViewViewHolder.weekDay.setText(recycleViewItem.getWeekDay());
        recyclerViewViewHolder.weekTemp.setText(recycleViewItem.getTemp()+"");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}