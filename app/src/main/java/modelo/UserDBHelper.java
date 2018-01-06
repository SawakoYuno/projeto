package modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joaquim on 02-01-2018.
 */

public class UserDBHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "projeto";
    private static final String TABLE_NAME = "user";
    private static final int DB_VERSION = 1;

    private static final String ID_USER = "id";
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";


    private SQLiteDatabase sqLiteDatabase;

    public UserDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        sqLiteDatabase = getWritableDatabase();

    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+TABLE_NAME+ " (" +
                ID_USER + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                USERNAME + " INTEGER NOT NULL," +
                EMAIL + " VARCHAR(25) NOT NULL);";

        sqLiteDatabase.execSQL(query);
    }

    //


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1)
        {
            String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
            sqLiteDatabase.execSQL(query);

            //onCreate(sqLiteDatabase);
        }
    }

    public long adicionarUserBD(User user)
    {
        ContentValues item = new ContentValues();
        long idUser;

        item.put(ID_USER, user.getId());
        item.put(USERNAME, user.getUsername());
        item.put(EMAIL, user.getEmail());

        idUser = sqLiteDatabase.insert(TABLE_NAME, null, item);

        return idUser;
    }


    public List<User> getAllUserDB()
    {
        List<User> users = new ArrayList<>();
        Cursor ponteiro = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (ponteiro.moveToFirst())
        {
            do{
                User tempUser = new User(

                        ponteiro.getInt(0),
                        ponteiro.getString(1),
                        ponteiro.getString(2));


                users.add(tempUser);
            }while(ponteiro.moveToNext());
        }
        ponteiro.close();

        return users;
    }


}

