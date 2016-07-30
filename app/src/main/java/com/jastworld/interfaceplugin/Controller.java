package com.jastworld.interfaceplugin;

import android.util.Log;

/**
 * Created by Tayo on 7/27/2016.
 */
public class Controller {
    public static String mapSelect;
    public static String floorSelect;
    public static String startPoint;
    public static String endPoint;
    public String TAG = "ERROR";



    public void setMapSelect(String mapChoice){
        mapSelect=mapChoice;
    }

    public String getMapSelect(){
        if(mapSelect!=null){
        return mapSelect;
        }else
        {
            String error= "No Map Selected";
            Log.e(TAG, error);
            return error;

        }
    }

    public void setFloorSelect(String floorChoice){
        floorSelect=floorChoice;
    }

    public String getFloorSelect(){
        if(floorSelect!=null){
            return floorSelect;
        }else
        {
            String error= "No Floor Selected";
            Log.e(TAG, error);
            return error;
        }

    }
    public void setStartPoint(String startChoice){
        startPoint=startChoice;
    }

    public String getStartPoint(){
        if(startPoint!=null){
            return startPoint;
        }else
        {
            String error= "No Start Point Selected";
            Log.e(TAG, error);
            return error;
        }

    }

    public void setEndPoint(String endChoice){
        endPoint=endChoice;
    }

    public String getEndPoint(){
        if(endPoint!=null){
            return endPoint;
        }else
        {
            String error= "No End Point Selected";
            Log.e(TAG, error);
            return error;

        }

    }
}
