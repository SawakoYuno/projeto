package utils;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import modelo.Artigo;
import modelo.User;

/**
 * Created by Joaquim on 02-01-2018.
 */

public class UserJsonParser {

    public static ArrayList<User> parserJsonUser(JSONArray response, Context context) {

        ArrayList<User> tempListaUser = new ArrayList<User>();

        try {
            for (int i = 0; i < response.length(); i++) {

                JSONObject user = (JSONObject) response.get(i);

                int idUser = user.getInt("id");
                String  username = user.getString("username");
                String email = user.getString("email");


                User auxUser = new User(idUser, username, email);
                tempListaUser.add(auxUser);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tempListaUser;








    }
}
