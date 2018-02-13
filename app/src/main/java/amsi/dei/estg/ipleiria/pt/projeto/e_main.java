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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import listeners.MesaListener;
import singletons.SingletonPedido;


public class e_main extends AppCompatActivity implements MesaListener {

    public static final Integer RC_E_PEDIDOS = 100;

    SharedPreferences preferences;
    SharedPreferences.Editor prefEditor;

    private JSONObject listaCondicoes;

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;

    private List<Button> listabtns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_e_main);
        setTitle("Mesas");

        preferences = getSharedPreferences("APP_SETTINGS",Context.MODE_PRIVATE);
        prefEditor = preferences.edit();

        button1 = findViewById(R.id.btn1M);
        button2 = findViewById(R.id.btn2M);
        button3 = findViewById(R.id.btn3M);
        button4 = findViewById(R.id.btn4M);
        button5 = findViewById(R.id.btn5M);
        button6 = findViewById(R.id.btn6M);
        button7 = findViewById(R.id.btn7M);
        button8 = findViewById(R.id.btn8M);
        button9 = findViewById(R.id.btn9M);

        listabtns = new ArrayList<>();
        listabtns.add(button1);
        listabtns.add(button2);
        listabtns.add(button3);
        listabtns.add(button4);
        listabtns.add(button5);
        listabtns.add(button6);
        listabtns.add(button7);
        listabtns.add(button8);
        listabtns.add(button9);

        SingletonPedido.getInstance(this).setMesaListener(this);
        SingletonPedido.getInstance(this).onbterCOndicaoMesas(this);
    }


    public void onClick(View v) {
        Button btn = (Button) v;
        String texto = btn.getText().toString();

        String estado = "livre";

        try {
            estado = listaCondicoes.getString(texto);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent criarPedido = new Intent(this, e_pedidos.class);
        criarPedido.putExtra(e_pedidos.btn, texto);
        criarPedido.putExtra(e_pedidos.c_estado, estado);
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
                SingletonPedido.getInstance(this).onbterCOndicaoMesas(this);
               // adaptadordaListVP.notifyDataSetChanged();
            }else if (resultCode == 202)
            {
                Snackbar.make(findViewById(R.id.layoutEMAIN), "Fatura emitida com sucesso", Snackbar.LENGTH_SHORT).show();
                SingletonPedido.getInstance(this).onbterCOndicaoMesas(this);
            }else
            {
                //Toast.makeText(this, "Erro ao criar os pedidos", Toast.LENGTH_SHORT).show();
                Snackbar.make(findViewById(R.id.layoutEMAIN), "Erro ao criar o Pedidos", Snackbar.LENGTH_SHORT).show();
            }

    }

    }

    @Override
    public void onRefreshCondicoes(JSONObject listaCondicoes) {
        this.listaCondicoes = listaCondicoes;

        for (Button btn: listabtns) {
            try {
                if (listaCondicoes.getString(btn.getText().toString()) != null)
                {
                    if (listaCondicoes.getString(btn.getText().toString()).equals("ocupada"))
                    {
                        btn.setBackgroundColor(getResources().getColor(R.color.colorBaunilha));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
