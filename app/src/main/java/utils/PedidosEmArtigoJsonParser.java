package utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import modelo.Artigo;
import modelo.PedidosEmArtigo;

/**
 * Created by Utilizador on 02/01/2018.
 */

public class PedidosEmArtigoJsonParser {
    public static ArrayList<PedidosEmArtigo> parserJsonPedidosEmArtigo(JSONArray response, Context context) {

        ArrayList<PedidosEmArtigo> tempListaPedidosEmArtigo = new ArrayList<PedidosEmArtigo>();

        try {
            for (int i = 0; i < response.length(); i++) {

                JSONObject pedidosEmArtigo = (JSONObject) response.get(i);


                int idArtigo = pedidosEmArtigo.getInt("id_artigo");
                int idPedido = pedidosEmArtigo.getInt("id_pedidos");
                String obs = pedidosEmArtigo.getString("obs");

                PedidosEmArtigo auxPedidosEmArtigo = new PedidosEmArtigo(idArtigo, idPedido, obs);
                tempListaPedidosEmArtigo.add(auxPedidosEmArtigo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tempListaPedidosEmArtigo;

    }

    public static PedidosEmArtigo parserJsonPedidosEmArtigo(String response, Context context) {


        try {

            JSONObject pedidosEmArtigo = new JSONObject(response);


            int idArtigo = pedidosEmArtigo.getInt("id_artigo");
            int idPedido = pedidosEmArtigo.getInt("id_pedidos");
            String obs = pedidosEmArtigo.getString("obs");

            PedidosEmArtigo auxPedidosEmArtigo = new PedidosEmArtigo( idArtigo, idPedido, obs);
            return auxPedidosEmArtigo;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static PedidosEmArtigo parserJsonLogin(String response, Context context)
    {
        return null;
    }


    public static boolean isConnectionInternet(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
