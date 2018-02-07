package dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Reserva;

/**
 * Created by Utilizador on 05/01/2018.
 */

public class ReservaDBHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "projeto";
    private static final String TABLE_NAME = "reserva";
    private static final int DB_VERSION = 1;

    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String NUMERO_TELEFONE = "numeroTelefone";
    private static final String QUANTI_PESSOAS = "quantidade_pessoas";
    private static final String HORARIO = "horario";
    private static final String ID_MESA = "id_mesa";


    private SQLiteDatabase sqLiteDatabase;

    public ReservaDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        sqLiteDatabase = getWritableDatabase();
        onCreate(sqLiteDatabase);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABLE_NAME+ " (" +
                ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                NOME + " VARCHAR NOT NULL," +
                NUMERO_TELEFONE + " INTEGER(9) NOT NULL," +
                QUANTI_PESSOAS + " INT(3) NOT NULL," +
                HORARIO + " VARCHAR NOT NULL," +
                ID_MESA + " INTEGER);";

        //sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        if (i != i1)
        {
            String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
            sqLiteDatabase.execSQL(query);

            onCreate(sqLiteDatabase);
        }
    }

    public long adicionarReservaBD(Reserva reserva)
    {
        ContentValues item = new ContentValues();
        long idReserva;

        item.put(ID, reserva.getId());
        item.put(NOME, reserva.getNome());
        item.put(NUMERO_TELEFONE, reserva.getNumeroTelefone());
        item.put(QUANTI_PESSOAS, reserva.getQuantidade_pessoas());
        if(reserva.getHorario() != null)
        {
            item.put(HORARIO, reserva.getHorario().toString());
        }
        item.put(ID_MESA, reserva.getId_mesa());


        idReserva = sqLiteDatabase.insert(TABLE_NAME, null, item);

        return idReserva;
    }


    public List<Reserva> getAllReservasDB()
    {
        List<Reserva> reservas = new ArrayList<>();
        Cursor ponteiro = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (ponteiro.moveToFirst())
        {
           do{
               /* SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                Date date = null;
                try {
                    if (ponteiro.getString(4) != null)//só para testar
                        date = dateFormat.parse(ponteiro.getString(4));
                } catch (ParseException e) {
                    e.printStackTrace();
                }*/

                Reserva tempReserva = new Reserva(

                        ponteiro.getInt(0),
                        ponteiro.getString(1),
                        ponteiro.getInt(2),
                        ponteiro.getInt(3),
                        ponteiro.getString(4),
                        //date,
                        ponteiro.getInt(5));

                reservas.add(tempReserva);
            }while(ponteiro.moveToNext());
        }
        ponteiro.close();

        return reservas;
    }

    public boolean guardarReservaDB(Reserva reserva)
    {
        ContentValues item = new ContentValues();

        item.put(ID, reserva.getId());
        item.put(NOME, reserva.getNome());
        item.put(NUMERO_TELEFONE, reserva.getNumeroTelefone());
        item.put(QUANTI_PESSOAS, reserva.getQuantidade_pessoas());
        item.put(HORARIO, reserva.getHorario());
        item.put(ID_MESA, reserva.getId_mesa());

        return sqLiteDatabase.update(TABLE_NAME, item, "id = ?", new String[]{"" + reserva.getId()}) > 0;
    }

}


