package com.example.gamingsmlogin.management;

import java.util.Calendar;

public class DateManagment {

    public  boolean isToday(String dateStr){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        String[] sDate =dateStr.split("\\s+");
        String[] onyDate=sDate[0].split("-");//year mount day
        // Log.e("TAG", year+"=="+Integer.parseInt(onyDate[0])  +" "+ month+"=="+Integer.parseInt(onyDate[1])  +" "+ day+"=="+Integer.parseInt(onyDate[2]) );
        if(year==Integer.parseInt(onyDate[0])  && month==Integer.parseInt(onyDate[1])  && day==Integer.parseInt(onyDate[2])   ){
            return true;
        }else{
            return false;
        }
    }


    public  String whichShow(String dateStr){
        String strShow="";
        String[] sDate =dateStr.split("\\s+");
        if(!isToday(dateStr)){
            strShow=sDate[0];
        }else{
            strShow=sDate[1].substring(0, 5);
        }

        return strShow;

    }
}
