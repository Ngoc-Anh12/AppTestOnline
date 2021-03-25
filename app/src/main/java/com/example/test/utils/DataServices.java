package com.example.test.utils;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
public class DataServices {

    private static final String SAVE_FINGER = "SAVE_FINGER";
    private static final String ACCESSTOKEN_KEY = "ACCESSTOKEN";
    private SharedPreferences sharedPreferences;
    private static DataServices instance = null;
    SharedPreferences.Editor editor = null;

    public static DataServices getInstance(Context context ){
        if (instance == null){
            instance = new DataServices();

            instance.init (context);

        }
        return instance;
    }
    void init(Context conn){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(conn);
        editor = sharedPreferences.edit();
    }
    public void storeToken(String accessToken){

        editor.putString(ACCESSTOKEN_KEY,accessToken);
       // editor.putString(REFRESHTOKEN_KEY,refreshToken);

    }
    public String getToken(){
        try {
            return sharedPreferences.getString(ACCESSTOKEN_KEY, null);
        }catch (Exception e){

        }
        return null;
    }
    public void save(){
        editor.apply();
    }
}
