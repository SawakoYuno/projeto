package amsi.dei.estg.ipleiria.pt.projeto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import modelo.SingletonArtigo;

public class c_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_main);
        setTitle("Menu Principal");


    }

    public void OnClickClientMain(View view) {

        String type = ((Button) view).getText().toString();

        switch (type) {
            case "Reservar":
                Intent intentR = new Intent(this, c_reservar_mesa.class);
                startActivity(intentR);
                break;
            case "Ementa":
                Intent intentE = new Intent(this, c_ementa.class);
                startActivity(intentE);
                break;
            case "Contactos":
                Intent intentC = new Intent(this, c_contactos.class);
                startActivity(intentC);
                break;
            case "Funcionalidades":
                Intent intentF = new Intent(this, c_funcionalidades.class);
                startActivity(intentF);
                break;

        }


    }
}
