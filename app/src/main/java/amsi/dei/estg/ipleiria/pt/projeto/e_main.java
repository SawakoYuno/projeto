package amsi.dei.estg.ipleiria.pt.projeto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
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

import java.sql.SQLOutput;


public class e_main extends AppCompatActivity {

    public static final Integer RC_E_PEDIDOS = 100;

    SharedPreferences preferences;
    SharedPreferences.Editor prefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_main);
        setTitle("Mesas");

        preferences = getSharedPreferences("APP_SETTINGS",Context.MODE_PRIVATE);
        prefEditor = preferences.edit();

    }


    public void onClick(View v) {
        Button btn = (Button) v;
        String texto = btn.getText().toString();

        Intent criarPedido = new Intent(this, e_pedidos.class);
        criarPedido.putExtra(e_pedidos.btn, texto);
        startActivityForResult(criarPedido, RC_E_PEDIDOS);


    }
//------------------------------Botão Editar---------------------------
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_e_main, menu);

        MenuItem item_editar = menu.findItem(R.id.itemEditar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item_editar);

        MenuItem logout = menu.findItem(R.id.itemLogout);
        SearchView searchViews = (SearchView) MenuItemCompat.getActionView(logout);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.itemLogout:

                prefEditor.putString("auth", "");
                prefEditor.apply();

                finish();
                Intent verlogout = new Intent(this, Login.class);
                startActivity(verlogout);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == RC_E_PEDIDOS)
        {
            if (resultCode == 200)
            {
                //Toast.makeText(this, "Pedidos criado com sucesso", Toast.LENGTH_SHORT).show();
                Snackbar.make(findViewById(R.id.layoutEMAIN), "Pedidos criado com sucesso", Snackbar.LENGTH_SHORT).show();
               // adaptadordaListVP.notifyDataSetChanged();
            }else
            {
                //Toast.makeText(this, "Erro ao criar os pedidos", Toast.LENGTH_SHORT).show();
                Snackbar.make(findViewById(R.id.layoutEMAIN), "Erro ao criar o Pedidos", Snackbar.LENGTH_SHORT).show();
            }

    }

    }
}
