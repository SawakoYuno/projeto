package singletons;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import listeners.PedidoListener;
import dbhelper.PedidoDBHelper;
import modelo.Pedidos;
import utils.PedidoJsonParser;

/**
 * Created by Utilizador on 29/12/2017.
 */

public class SingletonPedido implements PedidoListener{
    private static SingletonPedido INSTANCE = null;
    private static PedidoDBHelper dbHelper = null;

    //---------URL API ARTIGO-----------
    private String mUrlAPIPedidos = "http://10.0.2.2:8888/pedidos";
    //http://10.0.2.2:8888/
    //http://192.168.1.66:8888/
    //----------------------------------

    private static RequestQueue volleyQueue = null;

    private PedidoListener pedidoListener;

    private List<Pedidos> pedidos;

    private String auth;
    private SharedPreferences preferences;


    public static synchronized SingletonPedido getInstance(Context context) {
        if( INSTANCE == null)
        {
            INSTANCE = new SingletonPedido(context);
            volleyQueue = Volley.newRequestQueue(context);
        }

        return INSTANCE;
    }

    private SingletonPedido(Context context) {

        pedidos = new ArrayList<>();


        dbHelper = new PedidoDBHelper(context);
        pedidos = dbHelper.getAllPedidosDB();

        preferences = context.getSharedPreferences("APP_SETTINGS", Context.MODE_PRIVATE);
        auth = preferences.getString("auth", "");


    }

    public void lerDB()
    {
        pedidos = dbHelper.getAllPedidosDB();
    }

    public List<Pedidos> getPedidos() {
        return dbHelper.getAllPedidosDB();
    }

    public boolean adicionarPedidoBD(Pedidos pedidos)
    {
        long result = dbHelper.adicionarPedidoBD(pedidos);

        return result > 0;
    }

    public boolean removerPedidoBD(Pedidos pedido)
    {
        return dbHelper.removerPedidosDB(pedido.getId()) && pedidos.remove(pedido);
    }


    public void adicionarPedidosBD(List<Pedidos> listaPedidos)
    {
        for (Pedidos pedidos: listaPedidos)
        {
            dbHelper.adicionarPedidoBD(pedidos);
        }

        //return true;
    }


    public static Pedidos paraObjeto(JSONObject object, Context context)
    {
        GsonBuilder gson = new GsonBuilder();
        return gson.create().fromJson(object.toString(), Pedidos.class);
    }

    public boolean editarPedidoBD(Pedidos pedido)
    {
        if (dbHelper.guardarPedidoDB(pedido))
        {
            Pedidos novoPedido = null;

            for (Pedidos lv:pedidos) {
                if (lv.getId() == pedido.getId())
                    novoPedido = pedidos.set(pedidos.indexOf(lv), pedido);
            }

            return pedidos.contains(novoPedido);
        }

        return false;
    }


    public void getAllPedidosAPI(final Context context)
    {
        //, boolean isConnected
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, mUrlAPIPedidos, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        pedidos = PedidoJsonParser.parserJsonPedidos(response,context);

                        adicionarPedidosBD(pedidos);

                        pedidoListener.onRefreshListaPedidos(pedidos);
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
        //volleyQueue.add(jsonArrayRequest);

        /*****************************************************/
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(jsonArrayRequest);
        /*****************************************************/

    }

    public void adicionarPedidoAPI(final Pedidos pedidos, final Context context) {

        HashMap<String, String> params = new HashMap<String,String>();
        params.put("id_user", pedidos.getId_user().toString());
        params.put("id_mesa", pedidos.getId_mesa().toString());
        params.put("id_estado", pedidos.getId_estado().toString());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-M-dd");
        String data = df.format(pedidos.getData_pedido());
        params.put("data_pedido", data);
        SimpleDateFormat dfs = new SimpleDateFormat("HH:mm:ss");
        String horario = dfs.format(pedidos.getHora_pedido());
        params.put("hora_pedido", horario);

        System.out.println("---> params: " + params);

        JsonObjectRequest req = new JsonObjectRequest
                (Request.Method.POST, mUrlAPIPedidos, new JSONObject(params), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("---> Resposta pedido: " + response);


                            Pedidos novoPedido = PedidoJsonParser.parserJsonPedidos(response.toString(), context);
                            System.out.println("ID: " +novoPedido.getId());

                            VolleyLog.v("Response:%n %s", response.toString(4));

                            pedidoListener.onUpdateListaPedidosBD(novoPedido, 1);

                        } catch (JSONException e) {
                            System.out.println("---> Erro pedido: " + e);
                        }
                    }

                }, new Response.ErrorListener() {

                    public void onErrorResponse(VolleyError error) {

                        System.out.println("---> Erro pedido 2: " + error);
                        error.printStackTrace();
                    }
                });

        //volleyQueue.add(req);

        /*****************************************************/
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(req);
        /*****************************************************/

    }

    public void atualizarPedidoAPI(final int id, final Pedidos pedidos, final Context context) {

        StringRequest putRequest = new StringRequest(Request.Method.PUT, mUrlAPIPedidos + "/" + id,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);

                        pedidoListener.onUpdateListaPedidosBD(PedidoJsonParser.parserJsonPedidos(response,context),2);
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
                params.put("id", pedidos.getId().toString());
                params.put("id_user", pedidos.getId_user().toString());
                params.put("id_mesa", pedidos.getId_mesa().toString());
                params.put("id_estado", pedidos.getId_estado().toString());
                params.put("data_pedido", pedidos.getData_pedido().toString());
                params.put("hora_pedido", pedidos.getHora_pedido().toString());

                return params;
            }

        };

        //volleyQueue.add(putRequest);

        /*****************************************************/
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(putRequest);
        /*****************************************************/


    }

    public void eliminarPedidoAPI(final Pedidos pedidos,final int id, final Context context) {

        StringRequest dr = new StringRequest(Request.Method.DELETE, mUrlAPIPedidos + "/" + id,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);

                        pedidoListener.onUpdateListaPedidosBD(pedidos,3);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error.
                        Log.d("Error.Response", error.getMessage());
                    }
                }


        );
        //volleyQueue.add(dr);
        /*****************************************************/
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(dr);
        /*****************************************************/

    }


    public void setPedidosListener(PedidoListener pedidosListener)
    {
        this.pedidoListener = pedidosListener;
    }


    @Override
    public void onRefreshListaPedidos(List<Pedidos> listaPedidos) {

    }

    @Override
    public void onUpdateListaPedidosBD(Pedidos pedidos, int operacao) {

        switch (operacao){
            case 1:
                adicionarPedidoBD(pedidos);
                break;

            case 2:
                editarPedidoBD(pedidos);
                break;

            case 3:
                removerPedidoBD(pedidos);
                break;
        }

        pedidoListener.onRefreshListaPedidos(getPedidos());

    }

    public Pedidos pesquisarPedidoID(long id)
    {
        for (Pedidos pd:pedidos) {
            if (pd.getId() == id)
                return pd;
        }
        return null;
    }
}
