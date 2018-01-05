package amsi.dei.estg.ipleiria.pt.projeto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class c_lista_pedidos extends AppCompatActivity {
    private Bundle extra;
    String idmesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_lista_pedidos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        extra = getIntent().getExtras();

        idmesa = (extra.getString("BTN"));




    }
}
