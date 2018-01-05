package amsi.dei.estg.ipleiria.pt.projeto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class c_funcionalidades extends AppCompatActivity {

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
}
