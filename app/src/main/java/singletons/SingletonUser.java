package singletons;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import listeners.UserListener;
import modelo.User;
import dbhelper.UserDBHelper;

/**
 * Created by Joaquim on 02-01-2018.
 */

public class SingletonUser {
    private static SingletonUser INSTANCE = null;
    private static UserDBHelper dbHelper = null;

    //---------URL API ARTIGO-----------
    private String mUrlAPIUser = "http://192.168.1.66:8888/user";
    //http://10.0.2.2:8888/
    //http://192.168.1.66:8888/
    //----------------------------------

    private static RequestQueue volleyQueue = null;

    private UserListener UserListener;

    private List<User> users;

    private UserDBHelper userDBHelper;


    private String auth;
    private SharedPreferences preferences;



    public static synchronized SingletonUser getInstance(Context context) {
        if( INSTANCE == null)
        {
            INSTANCE = new SingletonUser(context);
            volleyQueue = Volley.newRequestQueue(context);
        }

        return INSTANCE;
    }

    private SingletonUser(Context context) {

        users = new ArrayList<>();


        dbHelper = new UserDBHelper(context);
        users = dbHelper.getAllUserDB();

        preferences = context.getSharedPreferences("APP_SETTINGS", Context.MODE_PRIVATE);
        auth = preferences.getString("auth", "");


    }
}
