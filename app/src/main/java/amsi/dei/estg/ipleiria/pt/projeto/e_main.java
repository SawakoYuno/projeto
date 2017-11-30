package amsi.dei.estg.ipleiria.pt.projeto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class e_main extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_main);
        setTitle("Mesas");
    }


    public void onClick(View v) {
        Button btn = (Button) v;
        String texto = btn.getText().toString(); //importante
        // System.out.println("OnClick para o botão:" + btn);

        Intent intent = new Intent(getApplicationContext(), e_pedidos.class);
        intent.putExtra(e_pedidos.btn, texto);
        startActivity(intent);

    }
}
//helllooooo
