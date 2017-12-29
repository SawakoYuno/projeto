package utils;

import android.content.Context;
import android.content.PeriodicSync;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import modelo.Pedidos;

/**
 * Created by Utilizador on 29/12/2017.
 */

public class PedidoJsonParser {

    public static ArrayList<Pedidos> parserJsonLivros(JSONArray response, Context context){

        ArrayList<Pedidos> tempListaPedidos = new ArrayList<Pedidos>();

        try {
            for (int i = 0; i < response.length(); i++) {

                JSONObject pedidos = (JSONObject) response.get(i);

                int idPedido = pedidos.getInt("id");
                int idUser = pedidos.getInt("id_user");
                int idMesa = pedidos.getInt("id_mesa");
                int idEstado = pedidos.getInt("id_estado");
                int data_pedido = pedidos.getInt("data_pedido"); //tipo data

                Pedidos auxPedidos = new Pedidos( idPedido, idUser, idMesa, idEstado, data_pedido);
                tempListaPedidos.add(auxPedidos);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tempListaPedidos;

    }

    public static Pedidos parserJsonPedidos(String response, Context context) {


        try {

            JSONObject pedidos = new JSONObject(response);

            int idPedido = pedidos.getInt("id");
            int idUser = pedidos.getInt("id_user");
            int idMesa = pedidos.getInt("id_mesa");
            int idEstado = pedidos.getInt("id_estado");
            int data_pedido = pedidos.getInt("data_pedido"); //tipo data

            Pedidos auxPedidos = new Pedidos( idPedido, idUser, idMesa, idEstado, data_pedido);
            return auxPedidos;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static Pedidos parserJsonLogin(String response, Context context)
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
