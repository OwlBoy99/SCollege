package com.example.scollege;
import android.content.Context;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.view.ScaleGestureDetector;

public class preferences {
    private static final String DATA_BTN ="status_BTN",
        DATA_AS = "as";
    private static SharedPreferences getSharedReferences(Context context){
        return  PreferenceManager.getDefaultSharedPreferences(context);
    }
    public static void setDataAs(Context context,String data){
        SharedPreferences.Editor editor =getSharedReferences(context).edit();
        editor.putString(DATA_AS,data);
        editor.apply();
    }

    public static String getDataAs(Context context){
        return getSharedReferences(context).getString(DATA_AS,"");
    }

    public static void setDataBtn(Context context,boolean status){
        SharedPreferences.Editor editor =getSharedReferences(context).edit();
        editor.putBoolean(DATA_BTN,status);
        editor.apply();
    }

    public static boolean getDataBtn(Context context){
        return getSharedReferences(context).getBoolean(DATA_BTN,false);
    }

    public static void clearData(Context context){
        SharedPreferences.Editor editor =getSharedReferences(context).edit();
        editor.remove(DATA_AS);
        editor.remove(DATA_BTN);
        editor.apply();
    }

}
