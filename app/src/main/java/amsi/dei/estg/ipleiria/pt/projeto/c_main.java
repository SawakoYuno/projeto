package amsi.dei.estg.ipleiria.pt.projeto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class c_main extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor prefEditor;

    private static final int RC_RESERVA = 297;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_main);
        setTitle("Menu Principal");

        preferences = getSharedPreferences("APP_SETTINGS", Context.MODE_PRIVATE);
        prefEditor = preferences.edit();


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_e_main, menu);

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

    public void OnClickClientMain(View view) {

        String type = ((Button) view).getText().toString();

        switch (type) {
            case "Reservar":
                Intent intentR = new Intent(this, c_reservar_mesa.class);
                startActivityForResult(intentR, RC_RESERVA);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_RESERVA)
        {
            if (resultCode == 200)
            {
                Toast.makeText(this, "Reservado com sucesso", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(this, "Erro ao reservar", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
