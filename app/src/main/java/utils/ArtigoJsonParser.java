package utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import modelo.Artigo;

/**
 * Created by Utilizador on 27/12/2017.
 */

public class ArtigoJsonParser {

    public static ArrayList<Artigo> parserJsonArtigo(JSONArray response, Context context) {

        ArrayList<Artigo> tempListaArtigos = new ArrayList<Artigo>();

        try {
            for (int i = 0; i < response.length(); i++) {

                JSONObject artigo = (JSONObject) response.get(i);

                int idArtigo = artigo.getInt("id");
                int id_tipo_ementa = artigo.getInt("id_tipo_artigo");
                String nome = artigo.getString("nome");
                String detalhes = artigo.getString("detalhes");
                int preco = artigo.getInt("preco");
                int quantidade = artigo.getInt("quantidade");
                String imagem = artigo.getString("imagem_artigo");
                //int imagem = 0;

                Artigo auxArtigo = new Artigo( idArtigo, id_tipo_ementa, nome, detalhes, preco, quantidade ,imagem);
                tempListaArtigos.add(auxArtigo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tempListaArtigos;
        }

    public static Artigo parserJsonArtigo(String response, Context context){

        try {

            JSONObject artigo = new JSONObject(response);

            int idArtigo = artigo.getInt("id");
            int id_tipo_ementa = artigo.getInt("id_tipo_ementa");
            String nome = artigo.getString("nome");
            String detalhes = artigo.getString("detalhes");
            int preco = artigo.getInt("preco");
            int quantidade = artigo.getInt("quantidade");
            String imagem = artigo.getString("imagem_artigo");

            Artigo auxArtigo = new Artigo( idArtigo, id_tipo_ementa, nome, detalhes, preco, quantidade, imagem);
            return auxArtigo;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Artigo parserJsonLogin(String response, Context context)
    {
        return null;
    }

    public static boolean isConnectionInternet(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
