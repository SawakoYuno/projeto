package modelo;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import listeners.PedidoListener;

/**
 * Created by Utilizador on 29/12/2017.
 */

public class SingletonPedido implements PedidoListener{
    private static SingletonArtigo INSTANCE = null;
    private static ArtigoDBHelper dbHelper = null;

    //---------URL API ARTIGO-----------
    private String mUrlAPIPedidos = "http://10.0.2.2:8888/pedidos";
    //----------------------------------

    private static RequestQueue volleyQueue = null;

    private PedidoListener pedidoListener;

    private List<Pedidos> pedidos;


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
        pedidos = dbHelper.get();

    }

    @Override
    public void onRefreshListaPedidos(List<Pedidos> listaPedidos) {

    }

    @Override
    public void onUpdateListaPedidosBD(Pedidos pedidos, int operacao) {

    }
}
