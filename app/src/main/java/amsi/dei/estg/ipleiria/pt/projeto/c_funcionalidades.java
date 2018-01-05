package amsi.dei.estg.ipleiria.pt.projeto;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class c_funcionalidades extends AppCompatActivity {
    private Bundle extra;
    String idmesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_funcionalidades);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public void OnclickFuncionalidades(View view) {
        Intent intentR = new Intent(this, c_pedirbebida.class);
        startActivity(intentR);
    }

    public void onClickFuncionalidades(View view) {
        String type = ((Button) view).getText().toString();

        switch (type) {
            case "Chamar Empregado":
                Toast.makeText(this, "Chamar o empregado com sucesso", Toast.LENGTH_SHORT).show();
               break;
            case "Pedir Bebida":
                Intent intentBebida = new Intent(this, c_pedirbebida.class);
                startActivity(intentBebida);
                break;
            case "Pedir Fatura":
                Toast.makeText(this, "Pedir fatura com sucesso", Toast.LENGTH_SHORT).show();
                break;
            case "Ver Lista de Pedidos":
                Intent intentPedidos = new Intent(this, e_pedidos.class);
                startActivity(intentPedidos);

        }
    }
}
