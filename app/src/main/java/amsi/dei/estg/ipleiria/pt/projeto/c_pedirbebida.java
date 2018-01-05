package amsi.dei.estg.ipleiria.pt.projeto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import java.util.List;

import Adaptadores.ListaBebidasAdapter;
import listeners.ArtigoListener;
import modelo.Artigo;
import modelo.SingletonArtigo;

public class c_pedirbebida extends AppCompatActivity implements ArtigoListener{

    private ListaBebidasAdapter adaptadorBebidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_pedirbebida);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SingletonArtigo.getInstance(this).setArtigoListener(this);
        SingletonArtigo.getInstance(this).getArtigoTipoAPI("Bebidas", this);


    }

    @Override
    public void onRefreshListaArtigos(List<Artigo> listaArtigo) {

        adaptadorBebidas = new ListaBebidasAdapter( this, listaArtigo);
        GridView grelhaBedida = (GridView) findViewById(R.id.grelhaBedida);
        grelhaBedida.setAdapter(adaptadorBebidas);


    }

    @Override
    public void onUpdateListaArtigosBD(Artigo artigo, int operacao) {

    }
}
