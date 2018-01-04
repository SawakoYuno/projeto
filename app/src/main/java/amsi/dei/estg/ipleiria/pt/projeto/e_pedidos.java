package amsi.dei.estg.ipleiria.pt.projeto;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import Adaptadores.ListaArtigoAdapter;
import Adaptadores.ListaEmentaAdapter;
import Adaptadores.ListaPedidoAdapter;
import listeners.ArtigoListener;
import listeners.PedidoListener;
import listeners.PedidosEmArtigoListener;
import modelo.Artigo;
import modelo.ArtigoDBHelper;
import modelo.Mesa;
import modelo.Pedidos;
import modelo.PedidosEmArtigo;
import modelo.SingletonArtigo;
import modelo.SingletonPedido;
import modelo.SingletonPedidosEmArtigo;

public class e_pedidos extends AppCompatActivity implements ArtigoListener, PedidoListener, PedidosEmArtigoListener {

    private ListaArtigoAdapter adaptadorDaList;
    private ListaPedidoAdapter adaptadordaListVP;
    private GridView listaviewArtigos;
    private ListView listviewPedidos;
    private List<Artigo> artigoList;
    private List<Pedidos> pedidosList;
    private List<PedidosEmArtigo> pedidosEmArtigosList;
    private Pedidos pedidos;
    private Context context;
    public static final String btn = "BTN";
    public static final String EXTRA_ID_Artigo = "artigo_id";
    private TextView txtNmesa;
    private Bundle extra;
    private int i = 0;
    private Integer idPedido;
    private List<Artigo> listaPedidos = new ArrayList<>();
    private String mUrlAPIPedidos = "http://10.0.2.2:8888/pedidos";




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_pedidos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SingletonArtigo.getInstance(this).setArtigoListener(this);
        SingletonPedido.getInstance(this).setPedidosListener(this);
        SingletonPedidosEmArtigo.getInstance(this).setPedidosEmArtigoListener(this);


        SingletonArtigo.getInstance(this).getAllArtigoAPI(this);

        //final List<Artigo> listaPedidos = new ArrayList<>();


        txtNmesa = (TextView) findViewById(R.id.txtNmesa);

        extra = getIntent().getExtras();

        txtNmesa.setText(extra.getString("BTN"));

        SingletonPedido.getInstance(this).getAllPedidosAPI(this);

        artigoList = SingletonArtigo.getInstance(this).getArtigo();
        pedidosList = SingletonPedido.getInstance(this).getPedidos();
        pedidosEmArtigosList = SingletonPedidosEmArtigo.getInstance(this).getPedidosEmArtigoList();

        listaviewArtigos = (GridView) findViewById(R.id.ListaMenu);
        listviewPedidos = (ListView) findViewById(R.id.ListaPedidos);

        adaptadorDaList = new ListaArtigoAdapter(this, SingletonArtigo.getInstance(this).getArtigo());
        adaptadordaListVP = new ListaPedidoAdapter(this, listaPedidos);

        listaviewArtigos.setAdapter(adaptadorDaList);
        listviewPedidos.setAdapter(adaptadordaListVP);


        listaviewArtigos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Artigo idArtigoSelect = SingletonArtigo.getInstance(e_pedidos.this).pesquisarArtigoPosition(i);
                atualizaPedido(idArtigoSelect);

                /*listaPedidos.add(idArtigoSelect);

                ListaPedidoAdapter ListaPedidoAdapterv2 = new ListaPedidoAdapter(context, listaPedidos);
                listviewPedidos.setAdapter(ListaPedidoAdapterv2);*/


            }
        });


        final SwipeRefreshLayout refreshLayout = findViewById(R.id.swipeRefreshLista);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adaptadorDaList.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        });

    }

    public void atualizaPedido(Artigo artigo)
    {
        listaPedidos.add(artigo);
        adaptadordaListVP.refresh(listaPedidos);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_e_main, menu);

        MenuItem item = menu.findItem(R.id.itemEditar);

        //?!?!?
        final List<Artigo> tempList = new ArrayList<>();

        listaviewArtigos.setAdapter(new ListaArtigoAdapter(getApplicationContext(), tempList));



       return true;
    }

    @Override
    protected void onResume() {
        adaptadorDaList.refresh(SingletonArtigo.getInstance(this).getArtigo());
        super.onResume();

    }

    @Override
    public void onRefreshListaArtigos(List<Artigo> lista) {
        adaptadorDaList = new ListaArtigoAdapter(this, lista);
        listaviewArtigos.setAdapter(adaptadorDaList);
        adaptadorDaList.refresh(lista);

    }



    @Override
    public void onUpdateListaArtigosBD(Artigo artigo, int operacao) {

    }

    public void click(View view) {
        String type = ((Button) view).getText().toString();

        switch (type) {
            case "Entradas":
                SingletonArtigo.getInstance(this).getArtigoTipoAPI("entradas", this);
                break;
            case "Carne":
                SingletonArtigo.getInstance(this).getArtigoTipoAPI("carne", this);
                break;
            case "Peixe":
                SingletonArtigo.getInstance(this).getArtigoTipoAPI("peixe", this);
                break;
            case "Sobremesa":
                SingletonArtigo.getInstance(this).getArtigoTipoAPI("sobremesa", this);
                break;
            case "Bebidas":
                SingletonArtigo.getInstance(this).getArtigoTipoAPI("bebidas", this);
                break;
        }
    }

    @Override
    public void onRefreshListaPedidos(List<Pedidos> listaPedidos) {

    }

    @Override
    public void onUpdateListaPedidosBD(Pedidos pedidos, int operacao) {
        idPedido = pedidos.getId();

        for(Artigo meuPedido:listaPedidos)
        {
            SingletonPedidosEmArtigo.getInstance(this).adicionarPedidoEmArtigoAPI(meuPedido, idPedido, this);

        }
    // JOAQUIM FAZ O SWITCH!!!
    }


    public void OnClickEnviar(View view) {
        int id_user = 4;
        int id_mesa;
        int id_estado;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        Date data_pedido = new Date();
        String dateTime = dateFormat.format(data_pedido);
        System.out.println("Current Date Time : " + dateTime);

        final int id = listaPedidos.get(i).getId();
        int id_tipo_ementa = listaPedidos.get(i).getId_tipo_ementa();
        String nome = listaPedidos.get(i).getNome();
        String detalhes = listaPedidos.get(i).getDetalhes();
        int preco = listaPedidos.get(i).getPreco();
        int quantidade = listaPedidos.get(i).getQuantidade();
        String imagem = listaPedidos.get(i).getImagem();
//        int id_pedidos = pedidosEmArtigosList.get(listaPedidos.get(id));
        ArrayList<Artigo>ListaArtigos;

        id_mesa = Integer.parseInt(txtNmesa.getText().toString());
        id_estado = 2;

               pedidos = new Pedidos(
                id_user,
                id_mesa,
                id_estado,
                data_pedido);

               SingletonPedido.getInstance(this).adicionarPedidoAPI(pedidos, this);



        ListaArtigos = pedidos.getArtigos();
        ListaArtigos.add(new Artigo(id, id_tipo_ementa, nome, detalhes, preco, quantidade, imagem));


 //foreach
    }

    @Override
    public void onRefreshListaPedidosEmArtigo(List<PedidosEmArtigo> pedidosEmArtigoList) {

    }

    @Override
    public void onUpdateListaPedidosEmArtigoBD(PedidosEmArtigo pedidosEmArtigo, int operacao) {

    }
}
