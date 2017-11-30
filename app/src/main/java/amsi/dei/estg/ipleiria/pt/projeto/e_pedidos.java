package amsi.dei.estg.ipleiria.pt.projeto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class e_pedidos extends AppCompatActivity {

    public static final String btn = "BTN";
    private TextView txtNmesa;
    private Bundle extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_pedidos);

        txtNmesa = (TextView) findViewById(R.id.txtNmesa);

       extra = getIntent().getExtras();

       txtNmesa.setText(extra.getString("BTN"));



    }
}
