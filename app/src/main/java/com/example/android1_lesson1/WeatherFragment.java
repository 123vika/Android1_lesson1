package com.example.android1_lesson1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.android1_lesson1.model.WeatherModel;

import static com.example.android1_lesson1.R.layout.week_temp;

public class WeatherFragment extends Fragment implements WeatherProviderListener {
    public WeatherFragment(){
    }
    static WeatherFragment make (String strValue , int intValue){
        WeatherFragment fragment = new WeatherFragment();
        return fragment;
    }
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle saveInstanceState){
        return inflater.inflate(week_temp , container, false);
    }
    @Override
    public void onAttach (Context context){
        super.onAttach(context);
    }

    @Override
    public void onResume(){
        super.onResume();
     //   WeatherProvider.getInstance().addListener(this);
        getActivity().findViewById(R.id.presure).
                setVisibility(WeaterSettingsModel.getInstance().isShowWind()?View.VISIBLE:View.GONE);
    }
    @Override
    public void onDetach(){
        super.onDetach();
    }
    @Override
    public void updateWeather(WeatherModel model){

    //    Log.i ( "hhhh" , "hhhh ");
        Log.d("hhhh", "updateW");

       // ((TextView)getActivity().findViewById(R.id.tempTextView2)).setText(Double.toString(model.getMain().getTemp()));
   //     ((TextView)getActivity().findViewById(R.id.pressureTextView)).setText(Integer.toString(model.getMain().getPressure()));
    //    String temp = Double.toString(model.getMain().getTemp()- 273.0)+" C";
    //    ((TextView)getActivity().findViewById(R.id.tempMonTextView)).setText(temp);

    }
    @Override
    public void onPause(){
      //  WeatherProvider.getInstance().removeListener(this);
        super.onPause();
    }
}
