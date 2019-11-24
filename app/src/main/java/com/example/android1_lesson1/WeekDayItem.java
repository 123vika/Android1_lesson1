package com.example.android1_lesson1;

public class WeekDayItem {

    private String weekDay;

    int temp;


    public WeekDayItem(String weekDay,int temp){
        this.weekDay = weekDay ;
        this.temp = temp;
    }

    public String getWeekDay (){
        return weekDay;
    }
    public int getTemp (){
        return temp;
    }

}


