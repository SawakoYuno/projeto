package dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import modelo.Artigo;


public class ArtigoDBHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "projeto";
    private static final String TABLE_NAME = "artigos";
    private static final int DB_VERSION = 1;

    private static final String ID_ARTIGO = "id";
    private static final String ID_TIPO_EMENTA_ARTIGO = "id_tipo_artigo";
    private static final String NOME_ARTIGO = "nome";
    private static final String DETALHES_ARTIGO = "detalhes";
    private static final String PRECO_ARTIGO = "preco";
    private static final String QUANTIDADE_ARTIGO = "quantidade";
    private static final String IMAGEM = "imagem_artigo";


    private SQLiteDatabase sqLiteDatabase;

    public ArtigoDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        sqLiteDatabase = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE "+TABLE_NAME+ " (" +
                ID_ARTIGO + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                ID_TIPO_EMENTA_ARTIGO + " INTEGER NOT NULL," +
                NOME_ARTIGO + " VARCHAR(25) NOT NULL," +
                DETALHES_ARTIGO + " VARCHAR(100) NOT NULL," +
                PRECO_ARTIGO + " DECIMAL NOT NULL," +
                QUANTIDADE_ARTIGO + " INTEGER, " +
                IMAGEM + " VARCHAR(200) NOT NULL);";

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

    public long adicionarArtigoBD(Artigo artigo)
    {
        ContentValues item = new ContentValues();
        long idArtigo;

        item.put(ID_ARTIGO, artigo.getId());
        item.put(ID_TIPO_EMENTA_ARTIGO, artigo.getId_tipo_ementa());
        item.put(NOME_ARTIGO, artigo.getNome());
        item.put(DETALHES_ARTIGO, artigo.getDetalhes());
        item.put(PRECO_ARTIGO, artigo.getPreco());
        item.put(QUANTIDADE_ARTIGO, artigo.getQuantidade());
        item.put(IMAGEM, artigo.getImagem());

         idArtigo = sqLiteDatabase.insert(TABLE_NAME, null, item);

        return idArtigo;
    }


        public List<Artigo> getAllArtigosDB()
        {
            List<Artigo> artigos = new ArrayList<>();
            Cursor ponteiro = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);

            if (ponteiro.moveToFirst())
            {
                do{
                    Artigo tempArtigo = new Artigo(

                            ponteiro.getInt(0),
                            ponteiro.getInt(1),
                            ponteiro.getString(2),
                            ponteiro.getString(3),
                            ponteiro.getInt(4),
                            ponteiro.getInt(5),
                            ponteiro.getString(6));

                    artigos.add(tempArtigo);
                }while(ponteiro.moveToNext());
            }
            ponteiro.close();

            return artigos;
        }


}

