package amsi.dei.estg.ipleiria.pt.projeto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import listeners.ArtigoListener;
import modelo.Artigo;

public class e_pedidos extends AppCompatActivity implements ArtigoListener {

    public static final String btn = "BTN";
    private TextView txtNmesa;
    private Bundle extra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_pedidos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        txtNmesa = (TextView) findViewById(R.id.txtNmesa);

       extra = getIntent().getExtras();

       txtNmesa.setText(extra.getString("BTN"));


    }

    @Override
    public void onRefreshListaArtigos(List<Artigo> listaArtigo) {
        adaptador = new ListaAAdapter(getApplication(), lista);
        listaLivros.setAdapter(adaptador);
        adaptador.refresh(lista);
    }

    @Override
    public void onUpdateListaArtigosBD(Artigo artigo, int operacao) {

    }
}
