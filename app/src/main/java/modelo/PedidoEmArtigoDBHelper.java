package modelo;

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

/**
 * Created by Utilizador on 02/01/2018.
 */

public class PedidoEmArtigoDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "projeto";
    private static final String TABLE_NAME = "pedidos_em_artigo";
    private static final int DB_VERSION = 1;

    private static final String ID_ARTIGO = "id_artigo";
    private static final String ID_PEDIDOS = "id_pedidos";
    private static final String OBS = "obs";

    private SQLiteDatabase sqLiteDatabase;

    public PedidoEmArtigoDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        sqLiteDatabase = getWritableDatabase();
        //onCreate(sqLiteDatabase);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+TABLE_NAME+ " (" +
                ID_ARTIGO + " INTEGER NOT NULL," +
                ID_PEDIDOS + " INTEGER NOT NULL," +
                OBS + " VARCHAR NOT NULL);";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        if (i != i1)
        {
            String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
            sqLiteDatabase.execSQL(query);

           // onCreate(sqLiteDatabase);
        }
    }

    public long adicionarPedidoEmArtigoBD(PedidosEmArtigo pedidosEmArtigo)
    {
        ContentValues item = new ContentValues();

        long idPedidoEmArtigo;

        item.put(ID_ARTIGO, pedidosEmArtigo.getId_artigo());
        item.put(ID_PEDIDOS , pedidosEmArtigo.getId_pedidos());
        item.put(OBS, pedidosEmArtigo.getObs());

        idPedidoEmArtigo = sqLiteDatabase.insert(TABLE_NAME, null, item);

        return idPedidoEmArtigo;
    }

    public boolean guardarPedidoEmArtigoDB(PedidosEmArtigo pedidosEmArtigo)
    {
        ContentValues item = new ContentValues();

        item.put(ID_ARTIGO, pedidosEmArtigo.getId_artigo());
        item.put(ID_PEDIDOS , pedidosEmArtigo.getId_pedidos());
        item.put(OBS, pedidosEmArtigo.getObs());

        ///**********************************************************
        return sqLiteDatabase.update(TABLE_NAME, item, "id_pedidos = ?", new String[]{"" + pedidosEmArtigo.getId_pedidos()}) > 0;
        ///**********************************************************
    }

    public boolean removerPedidosEmArtigosDB(long idPedidoEmArtigo)
    {
        return sqLiteDatabase.delete(TABLE_NAME, "id_pedidos = ?", new String[]{"" + idPedidoEmArtigo}) > 0;
    }

    public List<PedidosEmArtigo> getAllPedidosEmArtigoDB()
    {

        List<PedidosEmArtigo> pedidosEmArtigos = new ArrayList<>();
        Cursor ponteiro = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (ponteiro.moveToFirst())
        {
                PedidosEmArtigo tempPedidosEmArtigo = new PedidosEmArtigo(

                        ponteiro.getInt(0),
                        ponteiro.getInt(1),
                        ponteiro.getString(2));

            tempPedidosEmArtigo.setId_pedidos(ponteiro.getInt(1));

                pedidosEmArtigos.add(tempPedidosEmArtigo);

        }
        ponteiro.close();

        return pedidosEmArtigos;
    }
}
