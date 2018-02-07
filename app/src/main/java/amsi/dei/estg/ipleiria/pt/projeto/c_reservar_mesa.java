package amsi.dei.estg.ipleiria.pt.projeto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import listeners.ReservaListener;
import modelo.Reserva;
import singletons.SingletonReserva;

public class c_reservar_mesa extends AppCompatActivity implements ReservaListener{

    private Reserva criarReserva;

    private EditText Nome;
    private EditText NumeroTelefone;
    private EditText QuantiPessoas;
    private EditText Horario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_reservar_mesa);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Nome = (EditText) findViewById(R.id.txtNome);
        NumeroTelefone = (EditText) findViewById(R.id.txtNtelefone);
        QuantiPessoas = (EditText) findViewById(R.id.txtQuantiPessoas);
        Horario = (EditText) findViewById(R.id.txtHorario);

        SingletonReserva.getInstance(this).setReservaListener(this);


    }

    public void OnClickReservar(View view) {

        int id_mesa = 1;
        /*SimpleDateFormat df = new SimpleDateFormat("yyyy-M-dd");
        Date horario = null;
        try {
            horario = df.parse(Horario.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        if (criarReserva == null) // Criar Livro
        {
            criarReserva = new Reserva((int) 0,
                    Nome.getText().toString(),
                    Integer.parseInt(NumeroTelefone.getText().toString()),
                    Integer.parseInt(QuantiPessoas.getText().toString()),
                    Horario.getText().toString(),
                    id_mesa);

            SingletonReserva.getInstance(this).adicionarReservaAPI(criarReserva, this);


        }

        }

    @Override
    public void onRefreshListaReserva(List<Reserva> listaReserva) {

    }

    @Override
    public void onUpdateListaReservaBD(Reserva reserva, int operacao) {
        Intent returnIntent = new Intent();
        setResult(200, returnIntent);
        finish();
    }
}
