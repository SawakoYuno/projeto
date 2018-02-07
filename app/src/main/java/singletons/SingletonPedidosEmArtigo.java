package singletons;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

import listeners.PedidosEmArtigoListener;
import modelo.Artigo;
import dbhelper.PedidoEmArtigoDBHelper;
import modelo.Mesa;
import modelo.PedidosEmArtigo;
import utils.PedidosEmArtigoJsonParser;

/**
 * Created by Utilizador on 02/01/2018.
 */

public class SingletonPedidosEmArtigo implements PedidosEmArtigoListener{

    private static SingletonPedidosEmArtigo INSTANCE = null;
    private static PedidoEmArtigoDBHelper dbHelper = null;

    private List<Mesa> mesas;

    //---------URL API PEDIDOS_EM_ARTIGOS-----------
    private String mUrlAPIPedidosEmArtigos = "http://10.0.2.2:8888/pedidos-em-artigos";

    private String mUrlAPIMesa = "http://10.0.2.2:8888/mesas/";
    //http://10.0.2.2:8888/
    //192.168.1.66:8888
    //----------------------------------

    private static RequestQueue volleyQueue = null;

    private PedidosEmArtigoListener pedidosEmArtigoListener;

    private List<PedidosEmArtigo> pedidosEmArtigoList;

    private String auth;
    private SharedPreferences preferences;


   // private static final SingletonPedidosEmArtigo ourInstance = new SingletonPedidosEmArtigo();

    public static synchronized SingletonPedidosEmArtigo getInstance(Context context) {
        if( INSTANCE == null)
        {
            INSTANCE = new SingletonPedidosEmArtigo(context);
            volleyQueue = Volley.newRequestQueue(context);
        }

        return INSTANCE;
    }

    private SingletonPedidosEmArtigo(Context context) {
        pedidosEmArtigoList = new ArrayList<>();

        dbHelper = new PedidoEmArtigoDBHelper(context);
        pedidosEmArtigoList = dbHelper.getAllPedidosEmArtigoDB();

        preferences = context.getSharedPreferences("APP_SETTINGS", Context.MODE_PRIVATE);
        auth = preferences.getString("auth", "");


    }

    public void lerBD()
    {
        pedidosEmArtigoList = dbHelper.getAllPedidosEmArtigoDB();
    }

    public List<PedidosEmArtigo> getPedidosEmArtigoList() {
        return dbHelper.getAllPedidosEmArtigoDB();
    }

    public PedidosEmArtigo pesquisarPedidoEmArtigoID(int id)
    {
        for (PedidosEmArtigo lv:pedidosEmArtigoList) {
            if (lv.getId_pedidos() == id)
                return lv;

        }
        return null;
    }

    public PedidosEmArtigo pesquisarPedidosEmArtigosPosition(int i)
    {
        return pedidosEmArtigoList.get(i);
    }

    public boolean adicionarPedidoEmArtigoBD(PedidosEmArtigo pedidosEmArtigo)
    {
        long result = dbHelper.adicionarPedidoEmArtigoBD(pedidosEmArtigo);

        return result > 0;
    }

    public void adicionarPedidosEmArtigosBD(List<PedidosEmArtigo> listapedidosEmArtigo)
    {
        for (PedidosEmArtigo pedidosEmArtigo: listapedidosEmArtigo)
        {
            dbHelper.adicionarPedidoEmArtigoBD(pedidosEmArtigo);
        }

        //return true;
    }


    public void getAllPedidosEmArtigoAPI(final Context context)
    {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, mUrlAPIPedidosEmArtigos, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        pedidosEmArtigoList = PedidosEmArtigoJsonParser.parserJsonPedidosEmArtigo(response,context);

                        adicionarPedidosEmArtigosBD(pedidosEmArtigoList);
                        Toast.makeText(context, "Fez pedido", Toast.LENGTH_SHORT).show();
                        pedidosEmArtigoListener.onRefreshListaPedidosEmArtigo(pedidosEmArtigoList);
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

    public void adicionarPedidoEmArtigoAPI(final Artigo artigo, final Integer idPedido, final Integer atual, final Integer fim, final Context context) {

        System.out.println("---> Pedido em Artigo : " + idPedido);
        StringRequest postRequest = new StringRequest(Request.Method.POST, mUrlAPIPedidosEmArtigos,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        System.out.println("----> RESPOSTA ADD PedidoEmArtigo: " +response);

                        if (atual == fim)
                            pedidosEmArtigoListener.onUpdateListaPedidosEmArtigoBD(PedidosEmArtigoJsonParser.parserJsonPedidosEmArtigo(response,context),1);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        ) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Accept", "application/json");
                params.put("Authorization", "Basic " + auth);
                return params;
            }

            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_artigo", artigo.getId().toString());
                params.put("id_pedidos", idPedido.toString());
                params.put("obs", "");

                return params;
            }
        };

        volleyQueue.add(postRequest);

    }

    /***********************************************************/
    public void getEstado_MesaAPI(String condicao, final Context context) {
        //, boolean isConnected
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, mUrlAPIMesa + "/id/" + condicao, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Erro ao fazer o pedido JSonArray!!");
                    }
                }

        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Accept", "application/json");
                params.put("Authorization", "Basic " + auth);
                return params;
            }


        };



        // volleyQueue.add(jsonArrayRequest);
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(jsonArrayRequest);

    }

    /***********************************************************/


    public void atualizarPedidoEmArtigoAPI(final int id, final PedidosEmArtigo pedidosEmArtigo, final Context context) {

        StringRequest putRequest = new StringRequest(Request.Method.PUT, mUrlAPIPedidosEmArtigos + "/" + id,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);

                        pedidosEmArtigoListener.onUpdateListaPedidosEmArtigoBD(PedidosEmArtigoJsonParser.parserJsonPedidosEmArtigo(response,context),2);
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
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Accept", "application/json");
                params.put("Authorization", "Basic " + auth);
                return params;
            }


            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String> ();
                params.put("token", "AMSI-TOKEN");
                params.put("id_artigo", pedidosEmArtigo.getId_artigo().toString());
                params.put("id_pedidos", pedidosEmArtigo.getId_pedidos().toString());
                params.put("obs", pedidosEmArtigo.getObs().toString());

                return params;
            }

        };

        volleyQueue.add(putRequest);
    }

    public void eliminarPedidoEmArtigoAPI(final PedidosEmArtigo pedidosEmArtigo,final int id, final Context context) {

        StringRequest dr = new StringRequest(Request.Method.DELETE, mUrlAPIPedidosEmArtigos + "/" + id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);

                        pedidosEmArtigoListener.onUpdateListaPedidosEmArtigoBD(pedidosEmArtigo, 3);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error.
                        Log.d("Error.Response", error.getMessage());
                    }
                }


        );
        volleyQueue.add(dr);
    }

    public void setPedidosEmArtigoListener(PedidosEmArtigoListener pedidosEmArtigoListener)
    {
        this.pedidosEmArtigoListener = pedidosEmArtigoListener;
    }


    @Override
    public void onRefreshListaPedidosEmArtigo(List<PedidosEmArtigo> pedidosEmArtigoList) {

    }

    @Override
    public void onUpdateListaPedidosEmArtigoBD(PedidosEmArtigo pedidosEmArtigo, int operacao) {

        switch (operacao){
            case 1:
                adicionarPedidoEmArtigoBD(pedidosEmArtigo);
                break;

            /*case 3:
                eliminarPedidosEmArtigosDB(pedidosEmArtigo);
                break;*/
        }

        pedidosEmArtigoListener.onRefreshListaPedidosEmArtigo(getPedidosEmArtigoList());
    }
}
