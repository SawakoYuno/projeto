package utils;

import android.content.Context;
import android.content.PeriodicSync;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import modelo.Pedidos;

/**
 * Created by Utilizador on 29/12/2017.
 */

public class PedidoJsonParser {

    public static ArrayList<Pedidos> parserJsonPedidos(JSONArray response, Context context){

        ArrayList<Pedidos> tempListaPedidos = new ArrayList<Pedidos>();

        try {
            for (int i = 0; i < response.length(); i++) {

                JSONObject pedidos = (JSONObject) response.get(i);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                Date date = null;
                try {
                    date = dateFormat.parse(pedidos.getString("data_pedido"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                int idPedido = pedidos.getInt("id");
                int idUser = pedidos.getInt("id_user");
                int idMesa = pedidos.getInt("id_mesa");
                int idEstado = pedidos.getInt("id_estado");
                Date data_pedido = date; //tipo data

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

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            Date date = null;
            try {
                date = dateFormat.parse(pedidos.getString("data_pedido"));
            } catch (ParseException e) {
                e.printStackTrace();
            }


            int idPedido = pedidos.getInt("id");
            int idUser = pedidos.getInt("id_user");
            int idMesa = pedidos.getInt("id_mesa");
            int idEstado = pedidos.getInt("id_estado");
            Date data_pedido = date;

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
