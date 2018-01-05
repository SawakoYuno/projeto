package amsi.dei.estg.ipleiria.pt.projeto;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.List;

import android.widget.AdapterView;

import Adaptadores.ListaArtigoAdapter;
import Adaptadores.ListaBebidasAdapter;
import listeners.ArtigoListener;
import modelo.Artigo;
import modelo.SingletonArtigo;


public class c_pedirbebida extends AppCompatActivity implements ArtigoListener{

    private ListaBebidasAdapter adaptadorBebidas;
    private Context context;
    private GridView grelhaBebida;
    private ListView listaBebida;
    private List<Artigo> ListBebida;
    public static final String btn = "BTN";
    public static final String EXTRA_ID_Artigo = "artigo_id";
    private ListaArtigoAdapter adaptadorDaList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_pedirbebida);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SingletonArtigo.getInstance(this).setArtigoListener(this);
        SingletonArtigo.getInstance(this).getArtigoTipoAPI("Bebidas", this);

        grelhaBebida = (GridView) findViewById(R.id.ListaMenu);

        adaptadorDaList = new ListaArtigoAdapter(this, SingletonArtigo.getInstance(this).getArtigo());

        grelhaBebida.setAdapter(adaptadorDaList);


        grelhaBebida.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Artigo idArtigoSelect = SingletonArtigo.getInstance(c_pedirbebida.this).pesquisarArtigoPosition(i);
                //atualizaBebidas(idArtigoSelect);

            }
        });
    }


    public void atualizaBebidas(Artigo bebida)
            {
                ListBebida.add(bebida);
                adaptadorBebidas.refresh(ListBebida);
            }


    @Override
    public void onRefreshListaArtigos(List<Artigo> ListBebida) {

        adaptadorBebidas = new ListaBebidasAdapter( context, ListBebida);
        GridView grelhaBedida = (GridView) findViewById(R.id.grelhaBedida);
        grelhaBedida.setAdapter(adaptadorBebidas);

    }


    @Override
    public void onUpdateListaArtigosBD(Artigo artigo, int operacao) {

    }


}
