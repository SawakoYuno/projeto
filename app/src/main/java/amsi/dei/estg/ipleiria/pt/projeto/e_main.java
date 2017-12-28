package amsi.dei.estg.ipleiria.pt.projeto;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
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
        String texto = btn.getText().toString();

        Intent intent = new Intent(getApplicationContext(), e_pedidos.class);
        intent.putExtra(e_pedidos.btn, texto);
        startActivity(intent);


    }
//------------------------------Botão Editar---------------------------
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_e_main, menu);

        MenuItem item_editar = menu.findItem(R.id.itemEditar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item_editar);

        return super.onCreateOptionsMenu(menu);

    }

}
