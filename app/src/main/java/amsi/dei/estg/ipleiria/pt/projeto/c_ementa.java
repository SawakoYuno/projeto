package amsi.dei.estg.ipleiria.pt.projeto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import Adaptadores.ListaArtigoAdapter;
import modelo.Artigo;

public class c_ementa extends AppCompatActivity {

    private ListaArtigoAdapter adaptador;
    private ListView listaEmenta;
    private List<Artigo> artigoList;

    public static final String EXTRA_ID = "Artigo_Id";
    public static final Integer RC_Detalhes_Livro = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_ementa);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listaEmenta = (ListView) findViewById(R.id.ListaEmenta);
        adaptador = new ListaArtigoAdapter(this, artigoList);
        listaEmenta.setAdapter(adaptador);


    }
}
