package amsi.dei.estg.ipleiria.pt.projeto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import Adaptadores.ListaPedidoAdapter;
import modelo.Artigo;
import singletons.SingletonPedido;

public class e_faturacao extends AppCompatActivity {
    private String array_spinnerPagamentos[];
    private String array_spinnerIva[];

    public static final String R_MESA = "nmesa";
    private Integer nMesa;
    private ListView listaFatura;
    private ListaPedidoAdapter adaptador;
    private ArrayList<Artigo> listaArtigos;
    private Double subtotal;
    private Double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_faturacao);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        total = 0.00;

        /*----------PRENCHER O SPINNER DE TIPO DE PAGAMENTOS---------*/
        array_spinnerPagamentos =new String[3];
        array_spinnerPagamentos[0]="Dinheiro";
        array_spinnerPagamentos[1]="Cartão de crédito";
        array_spinnerPagamentos[2]="Cheque";
        Spinner sPagamentos = (Spinner) findViewById(R.id.spinnerPagamentos);
        ArrayAdapter adapterPagamentos = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_spinnerPagamentos);
        sPagamentos.setAdapter(adapterPagamentos);

         /*----------PRENCHER O SPINNER DO TIPO DE IVA---------*/
        array_spinnerIva=new String[2];
        array_spinnerIva[0]="26%";
        array_spinnerIva[1]="3%";
        Spinner sIva = (Spinner) findViewById(R.id.spinnerIva);
        ArrayAdapter adapterIva = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_spinnerIva);
        sIva.setAdapter(adapterIva);

        sIva.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0)
                {
                    total = subtotal * 1.26;
                }else{
                    total = subtotal * 1.03;
                }
                TextView txtT = findViewById(R.id.total);
                TextView recebido = findViewById(R.id.recebido);
                TextView troco = findViewById(R.id.troco);

                txtT.setText(total.toString());
                recebido.setText("00");
                troco.setText("00");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //------------------------------------------------------
        listaFatura = findViewById(R.id.listaPedidosFatura);
        TextView txtNMesa = findViewById(R.id.txtMesaFatura);

        Intent pedido = getIntent();
        nMesa = pedido.getIntExtra(R_MESA, -1);


        if (nMesa > -1)//Verifica se recebeu bem o nº de mesa
        {
            txtNMesa.setText(nMesa.toString());
            TextView txtSubTotal = findViewById(R.id.txtSubtotal);
            TextView txtTotal = findViewById(R.id.total);
            subtotal = 0.00;

            listaArtigos = SingletonPedido.getInstance(this).getArtigosEstado(nMesa);

            adaptador = new ListaPedidoAdapter(this, listaArtigos);
            listaFatura.setAdapter(adaptador);

            for (Artigo item:listaArtigos) {
                subtotal += item.getPreco();
            }

            total = subtotal * 1.26;
            txtSubTotal.setText(subtotal.toString());
            txtTotal.setText(total.toString());
        }

    }

    public void onClick(View view)
    {
        String texto = ((Button)view).getText().toString();

        TextView recebido = findViewById(R.id.recebido);
        TextView troco = findViewById(R.id.troco);

        if (texto.equals("C"))
        {
            recebido.setText("00");
            troco.setText("00");
        }else{
            if (recebido.getText().toString().equals("00"))
            {
                recebido.setText(texto);
            }else{
                recebido.setText(recebido.getText()+texto);
            }


            if (!texto.equals("."))
            {
                Double valRecebido = Double.parseDouble(recebido.getText().toString());
                Double valTroco = total - valRecebido;

                valTroco = Math.round( valTroco * 100.0 ) / 100.0;
                troco.setText(Double.toString(valTroco));
            }
        }
    }
}
