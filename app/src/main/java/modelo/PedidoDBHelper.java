package modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Utilizador on 29/12/2017.
 */

public class PedidoDBHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "projeto";
    private static final String TABLE_NAME = "pedidos";
    private static final int DB_VERSION = 1;

    private static final String ID_PEDIDO = "id";
    private static final String ID_USER = "id_user";
    private static final String ID_MESA = "id_mesa";
    private static final String ID_ESTADO = "id_estado";
    private static final String DATA_PEDIDO = "data_pedido";

    private final SQLiteDatabase sqLiteDatabase;

    public PedidoDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        sqLiteDatabase = getWritableDatabase();

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+TABLE_NAME+ " (" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                ID_USER + " INTEGER NOT NULL," +
                ID_MESA + " INTEGER NOT NULL," +
                ID_PEDIDO + " INTEGER NOT NULL," +
                ID_ESTADO + " INTEGER NOT NULL" +
                DATA_PEDIDO + " DATE;";

        sqLiteDatabase.execSQL(query);
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

    public long adicionarPedidoBD(Pedidos pedidos)
    {
        ContentValues item = new ContentValues();
        long idPedido;

        item.put(ID_PEDIDO, pedidos.getId());
        item.put(ID_USER, pedidos.getId_user());
        item.put(ID_MESA, pedidos.getId_mesa());
        item.put(ID_PEDIDO, pedidos.getData_pedido().toString());
        item.put(ID_ESTADO, pedidos.getId_estado());
        item.put(DATA_PEDIDO, pedidos.getData_pedido().toString());


        idPedido = sqLiteDatabase.insert(TABLE_NAME, null, item);

        return idPedido;
    }

    public boolean guardarPedidoDB(Pedidos pedidos)
    {
        ContentValues item = new ContentValues();

        item.put(ID_PEDIDO, pedidos.getId());
        item.put(ID_USER, pedidos.getId_user());
        item.put(ID_MESA, pedidos.getId_mesa());
        item.put(ID_ESTADO, pedidos.getId_estado());
        item.put(DATA_PEDIDO, pedidos.getData_pedido().toString());

        return sqLiteDatabase.update(TABLE_NAME, item, "id = ?", new String[]{"" + pedidos.getId()}) > 0;
    }

    public boolean removerPedidosDB(long idPedidos)
    {
        return sqLiteDatabase.delete(TABLE_NAME, "id = ?", new String[]{"" + idPedidos}) > 0;
    }


    public List<Pedidos> getAllPedidosDB()
    {
        List<Pedidos> pedidos = new ArrayList<>();
        Cursor ponteiro = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (ponteiro.moveToFirst())
        {
            do{
                Pedidos tempPedidods = new Pedidos(

                        (int) 0,
                        ponteiro.getInt(1),
                        ponteiro.getInt(2),
                        ponteiro.getInt(3),
                        ponteiro.getString(4)); //tipo data

                tempPedidods.setId(ponteiro.getInt(0));

                pedidos.add(tempPedidods);
            }while(ponteiro.moveToNext());
        }
        ponteiro.close();

        return pedidos;
    }
}
