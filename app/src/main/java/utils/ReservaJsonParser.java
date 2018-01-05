package utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import modelo.Artigo;
import modelo.Pedidos;
import modelo.Reserva;

/**
 * Created by Utilizador on 05/01/2018.
 */

public class ReservaJsonParser {

    public static ArrayList<Reserva> parserJsonReserva(JSONArray response, Context context) {

        ArrayList<Reserva> tempListaReserva = new ArrayList<Reserva>();

        try {
            for (int i = 0; i < response.length(); i++) {

                JSONObject reserva = (JSONObject) response.get(i);

               /* SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                Date date = null;
                try {
                    date = dateFormat.parse(reserva.getString("horario"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }*/

                int idReserva = reserva.getInt("id");
                String nome = reserva.getString("nome");
                int numero_telefone = reserva.getInt("numeroTelefone");
                int quanti_pessoas = reserva.getInt("quantidade_pessoas");
                //Date horario = date;
                String horario = reserva.getString("horario");
                int id_mesa = reserva.getInt("id_mesa");


                Reserva auxReserva = new Reserva( idReserva, nome, numero_telefone, quanti_pessoas, horario, id_mesa);
                tempListaReserva.add(auxReserva);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tempListaReserva;
    }

    public static Reserva parserJsonReserva(String response, Context context) {


        try {

            JSONObject reserva = new JSONObject(response);

/*            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = dateFormat.parse(reserva.getString("horario"));
            } catch (ParseException e) {
                e.printStackTrace();
            }*/

            int idReserva = reserva.getInt("id");
            String nome = reserva.getString("nome");
            int numero_telefone = reserva.getInt("numeroTelefone");
            int quanti_pessoas = reserva.getInt("quantidade_pessoas");
            //Date horario = date;
            String horario = reserva.getString("horario");
            int id_mesa = reserva.getInt("id_mesa");

            Reserva auxReserva = new Reserva( idReserva, nome, numero_telefone, quanti_pessoas, horario, id_mesa);
            return auxReserva;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static Reserva parserJsonLogin(String response, Context context)
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

