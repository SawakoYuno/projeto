package singletons;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import listeners.ReservaListener;
import modelo.Reserva;
import dbhelper.ReservaDBHelper;
import utils.ReservaJsonParser;

/**
 * Created by Utilizador on 05/01/2018.
 */

public class SingletonReserva implements ReservaListener{

    private static SingletonReserva INSTANCE = null;
    private static ReservaDBHelper dbHelper = null;

    //---------URL API RESERVA-----------
    private String mUrlAPIReserva = "http://192.168.1.66:8888/reservas";
    //http://10.0.2.2:8888/
    //----------------------------------

    private static RequestQueue volleyQueue = null;

    private ReservaListener reservaListener;

    private List<Reserva> reservas;

    private String auth;
    private SharedPreferences preferences;


    public static synchronized SingletonReserva getInstance(Context context) {
        if( INSTANCE == null)
        {
            INSTANCE = new SingletonReserva(context);
            volleyQueue = Volley.newRequestQueue(context);
        }

        return INSTANCE;
    }

    private SingletonReserva(Context context) {

        reservas = new ArrayList<>();

        dbHelper = new ReservaDBHelper(context);
        reservas = dbHelper.getAllReservasDB();

        preferences = context.getSharedPreferences("APP_SETTINGS", Context.MODE_PRIVATE);
        auth = preferences.getString("auth", "");

    }

    public void lerDB()
    {
        reservas = dbHelper.getAllReservasDB();
    }

    public List<Reserva> getReservas() {
        return dbHelper.getAllReservasDB();
    }

    public boolean adicionarReservaBD(Reserva reserva)
    {
        long result = dbHelper.adicionarReservaBD(reserva);

        return result > 0;
    }

    public boolean removerReservaBD(Reserva reserva)
    {
        //return dbHelper.removerReservaDB(reserva.getId()) && reserva.remove(reserva);
        return true;
    }

    public void adicionarReservaBD(List<Reserva> listaReserva)
    {
        for (Reserva reserva: listaReserva)
        {
            dbHelper.adicionarReservaBD(reserva);
        }

        //return true;
    }

    public static Reserva paraObjeto(JSONObject object, Context context)
    {
        GsonBuilder gson = new GsonBuilder();
        return gson.create().fromJson(object.toString(), Reserva.class);
    }

    public boolean editarReservaBD(Reserva reserva)
    {
        /*if (dbHelper.guardarReservaDB(reserva))
        {
            Reserva novaReserva = null;

            for (Pedidos lv:reserva) {
                if (lv.getId() == reserva.getId())
                    novaReserva = reserva.set(reserva.indexOf(lv), reserva);
            }

            return reserva.contains(novaReserva);
        }
*/
        return false;
    }

    public void getAllReservasAPI(final Context context)
    {
        //, boolean isConnected
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, mUrlAPIReserva, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        reservas = ReservaJsonParser.parserJsonReserva(response,context);

                        adicionarReservaBD(reservas);

                        reservaListener.onRefreshListaReserva(reservas);
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

    public void adicionarReservaAPI(final Reserva reserva, final Context context) {


        HashMap<String, String> params = new HashMap<String,String>();
        params.put("nome", reserva.getNome());
        params.put("numeroTelefone", reserva.getNumeroTelefone().toString());
        params.put("quantidade_pessoas", reserva.getQuantidade_pessoas().toString());
        params.put("horario", reserva.getHorario());
        params.put("id_mesa", "1");

        System.out.println("---> params: " + params);

        JsonObjectRequest req = new JsonObjectRequest
                (Request.Method.POST, mUrlAPIReserva, new JSONObject(params), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("---> Resposta Reserva: " + response);

                            VolleyLog.v("Response:%n %s", response.toString(4));

                        } catch (JSONException e) {
                            System.out.println("---> Erro Reserva: " + e);
                        }
                    }

                }, new Response.ErrorListener() {

                    public void onErrorResponse(VolleyError error) {

                        System.out.println("---> Erro Reserva 2: " + error);
                        error.printStackTrace();
                    }
                });

        volleyQueue.add(req);
    }

    public void atualizarReservaAPI(final int id, final Reserva reserva, final Context context) {

        HashMap<String, String> params = new HashMap<String,String>();
        params.put("nome", reserva.getNome());
        params.put("numeroTelefone", reserva.getNumeroTelefone().toString());
        params.put("quantidade_pessoas", reserva.getQuantidade_pessoas().toString());
        params.put("horario", reserva.getHorario());
        params.put("id_mesa", "1");


        System.out.println("---> params: " + params);

        JsonObjectRequest req = new JsonObjectRequest
                (Request.Method.PUT, mUrlAPIReserva, new JSONObject(params), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("---> Resposta Reserva: " + response);

                            VolleyLog.v("Response:%n %s", response.toString(4));

                        } catch (JSONException e) {
                            System.out.println("---> Erro Reserva: " + e);
                        }
                    }

                }, new Response.ErrorListener() {

                    public void onErrorResponse(VolleyError error) {

                        System.out.println("---> Erro Reserva 2: " + error);
                        error.printStackTrace();
                    }
                });

        volleyQueue.add(req);
    }

    public void eliminarReservaAPI(final Reserva reserva,final int id, final Context context) {

        StringRequest dr = new StringRequest(Request.Method.DELETE, mUrlAPIReserva + "/" + id,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);

                        reservaListener.onUpdateListaReservaBD(reserva,3);
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
        volleyQueue.add(dr);
    }


    public void setReservaListener(ReservaListener reservaListener)
    {
        this.reservaListener = reservaListener;
    }


    @Override
    public void onRefreshListaReserva(List<Reserva> listaReserva) {

    }

    @Override
    public void onUpdateListaReservaBD(Reserva reserva, int operacao) {
        switch (operacao){
            case 1:
                adicionarReservaBD(reserva);
                break;

            case 2:
                editarReservaBD(reserva);
                break;

            case 3:
                removerReservaBD(reserva);
                break;
        }

        reservaListener.onRefreshListaReserva(getReservas());

    }

    public Reserva pesquisarReservaID(int id)
    {
        for (Reserva pd:reservas) {
            if (pd.getId() == id)
                return pd;
        }
        return null;
    }
}
