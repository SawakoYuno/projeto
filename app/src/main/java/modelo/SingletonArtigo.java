package modelo;



import android.app.DownloadManager;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import amsi.dei.estg.ipleiria.pt.projeto.R;
import listeners.ArtigoListener;
import utils.ArtigoJsonParser;


public class SingletonArtigo implements ArtigoListener {
    private static SingletonArtigo INSTANCE = null;
    private static ArtigoDBHelper dbHelper = null;

    //---------URL API ARTIGO-----------
    private String mUrlAPIArtigos = "http://10.0.2.2:8888/artigo";
    //----------------------------------

    private static RequestQueue volleyQueue = null;

    private ArtigoListener artigoListener;

    private List<Artigo> artigos;



    public static synchronized SingletonArtigo getInstance(Context context) {
        if( INSTANCE == null)
        {
            INSTANCE = new SingletonArtigo(context);
            volleyQueue = Volley.newRequestQueue(context);
        }

        return INSTANCE;
    }


    private SingletonArtigo(Context context) {

        artigos = new ArrayList<>();


        dbHelper = new ArtigoDBHelper(context);
        artigos = dbHelper.getAllArtigosDB();

    }



    public void lerBD()
    {
        artigos = dbHelper.getAllArtigosDB();
    }

    public List<Artigo> getArtigo() {
        return dbHelper.getAllArtigosDB();
    }


    public boolean adicionarArtigoBD(Artigo artigo)
    {
        long result = dbHelper.adicionarArtigoBD(artigo);

        return result > 0;
    }

    public void adicionarArtigosBD(List<Artigo> listaArtigos)
    {
        for (Artigo artigo: listaArtigos)
        {
            dbHelper.adicionarArtigoBD(artigo);
        }

        //return true;
    }

    public void getAllArtigoAPI(final Context context)
    {
        //, boolean isConnected
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, mUrlAPIArtigos, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        artigos = ArtigoJsonParser.parserJsonArtigo(response,context);

                        adicionarArtigosBD(artigos);

                        artigoListener.onRefreshListaArtigos(artigos);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Erro ao fazer o pedido JSonArray!!");
                    }
                }
        );
        // Adding JsonObject request to request queue
        volleyQueue.add(jsonArrayRequest);

    }

    public void atualizarArtigoAPI(final int id, final Artigo artigo, final Context context)
    {
        StringRequest putRequest = new StringRequest(Request.Method.PUT, mUrlAPIArtigos /*+ "/" + idlivro*/,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);

                        artigoListener.onUpdateListaArtigosBD(ArtigoJsonParser.parserJsonArtigo(response,context),2);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String> ();
                params.put("token", "AMSI-TOKEN");
                params.put("id", artigo.getId().toString());
                params.put("id_tipo_ementa", artigo.getId_tipo_ementa().toString());
                params.put("nome", artigo.getNome());
                params.put("detalhes", artigo.getDetalhes());
                params.put("preco", artigo.getPreco().toString());
                params.put("quantidade", artigo.getQuantidade().toString());
                params.put("imagem", artigo.getImagem().toString());

                return params;
            }

        };

        volleyQueue.add(putRequest);
    }
    public void setArtigoListener(ArtigoListener artigoListener)
    {
        this.artigoListener = artigoListener;
    }

    @Override
    public void onRefreshListaArtigos(List<Artigo> listaArtigo) {

    }

    @Override
    public void onUpdateListaArtigosBD(Artigo artigo, int operacao) {

        //ArtigoListener.onRefreshListaArtigo(getArtigo());
    }
}

