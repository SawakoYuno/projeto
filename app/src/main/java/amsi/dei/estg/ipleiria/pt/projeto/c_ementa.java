package amsi.dei.estg.ipleiria.pt.projeto;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import Adaptadores.ListaArtigoAdapter;
import Adaptadores.ListaEmentaAdapter;
import listeners.ArtigoListener;
import modelo.Artigo;
import modelo.SingletonArtigo;

public class c_ementa extends AppCompatActivity implements ArtigoListener{

    private ListaEmentaAdapter adaptadorEmenta;
    private ListView listaEmenta;
    private List<Artigo> EmentaList;
    private Context context;
    //public static final String EXTRA_ID = "Artigo_Id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_ementa);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SingletonArtigo.getInstance(this).setArtigoListener(this);

        EmentaList = SingletonArtigo.getInstance(this).getArtigo();

        listaEmenta = (ListView) findViewById(R.id.ListaEmenta);
        adaptadorEmenta = new ListaEmentaAdapter(this, EmentaList);
        listaEmenta.setAdapter(adaptadorEmenta);

    }

    @Override
    public void onRefreshListaArtigos(List<Artigo> listaArtigo) {
        adaptadorEmenta = new ListaEmentaAdapter(this, listaArtigo);
        listaEmenta.setAdapter(adaptadorEmenta);
        adaptadorEmenta.refresh(listaArtigo);
    }

    @Override
    public void onUpdateListaArtigosBD(Artigo artigo, int operacao) {

    }
}
