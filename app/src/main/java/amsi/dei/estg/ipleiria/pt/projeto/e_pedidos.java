package amsi.dei.estg.ipleiria.pt.projeto;

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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Adaptadores.ListaArtigoAdapter;
import listeners.ArtigoListener;
import modelo.Artigo;
import modelo.SingletonArtigo;

public class e_pedidos extends AppCompatActivity implements ArtigoListener {

    private ListaArtigoAdapter adaptador;
    private GridView listaArtigo;
    private List<Artigo> artigoList;

    public static final String btn = "BTN";
    private TextView txtNmesa;
    private Bundle extra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_pedidos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SingletonArtigo.getInstance(this).setArtigoListener(this);
        SingletonArtigo.getInstance(this).getAllArtigoAPI(this);


        txtNmesa = (TextView) findViewById(R.id.txtNmesa);

        extra = getIntent().getExtras();

        txtNmesa.setText(extra.getString("BTN"));

        artigoList = SingletonArtigo.getInstance(this).getArtigo();
        listaArtigo = (GridView) findViewById(R.id.ListaMenu);
        adaptador = new ListaArtigoAdapter(this, artigoList);
        listaArtigo.setAdapter(adaptador);

        listaArtigo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });



        final SwipeRefreshLayout refreshLayout = findViewById(R.id.swipeRefreshLista);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adaptador.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_e_main, menu);

        MenuItem item = menu.findItem(R.id.itemEditar);

        //?!?!?
        final List<Artigo> tempList = new ArrayList<>();

        listaArtigo.setAdapter(new ListaArtigoAdapter(getApplicationContext(), tempList));

        return true;
    }

    @Override
    protected void onResume() {
        adaptador.refresh(SingletonArtigo.getInstance(this).getArtigo());
        super.onResume();

    }

    @Override
    public void onRefreshListaArtigos(List<Artigo> lista) {
        adaptador = new ListaArtigoAdapter(this, lista);
        listaArtigo.setAdapter(adaptador);
        adaptador.refresh(lista);

    }

    @Override
    public void onUpdateListaArtigosBD(Artigo artigo, int operacao) {

    }

    public void click(View view) {
        String type = ((Button) view).getText().toString();

        switch (type)
        {
            case "Carne":
                adapter.setDataList(stringList1);
                break;
    }
}
