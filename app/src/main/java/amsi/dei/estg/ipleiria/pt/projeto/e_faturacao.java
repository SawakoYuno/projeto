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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Adaptadores.ListaPedidoAdapter;
import listeners.PedidoListener;
import modelo.Artigo;
import modelo.Fatura;
import modelo.Pedidos;
import singletons.SingletonPedido;
import singletons.SingletonPedidosEmArtigo;

public class e_faturacao extends AppCompatActivity implements PedidoListener{
    private String array_spinnerPagamentos[];
    private String array_spinnerIva[];

    public static final String R_MESA = "nmesa";
    private Integer nMesa;
    private ListView listaFatura;
    private ListaPedidoAdapter adaptador;
    private ArrayList<Artigo> listaArtigos;
    private Double subtotal;
    private Double total;
    private Spinner sIva;
    private Spinner sPagamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_faturacao);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SingletonPedido.getInstance(this).setPedidosListener(this);

        total = 0.00;

        /*----------PRENCHER O SPINNER DE TIPO DE PAGAMENTOS---------*/
        array_spinnerPagamentos =new String[3];
        array_spinnerPagamentos[0]="Dinheiro";
        array_spinnerPagamentos[1]="Cartão de crédito";
        array_spinnerPagamentos[2]="Cheque";
        sPagamentos = (Spinner) findViewById(R.id.spinnerTipoPagamento);
        ArrayAdapter adapterPagamentos = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_spinnerPagamentos);
        sPagamentos.setAdapter(adapterPagamentos);

         /*----------PRENCHER O SPINNER DO TIPO DE IVA---------*/
        array_spinnerIva=new String[2];
        array_spinnerIva[0]="26%";
        array_spinnerIva[1]="3%";
        sIva = (Spinner) findViewById(R.id.spinnerIva);
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
                if (valRecebido > total)
                    valTroco = valTroco*-1;
                troco.setText(Double.toString(valTroco));
            }
        }
    }

    public void onClickImprimir(View view)
    {
//        SingletonPedido.getInstance(this).limparArtigoEstado(nMesa);
        SingletonPedidosEmArtigo.getInstance(this).getEstado_MesaAPI(nMesa.toString(), this);

        Integer idPedido = SingletonPedido.getInstance(this).getPedidoEstado(nMesa);

        Integer nif = 000000000;

        Fatura fatura = new Fatura(sPagamentos.getSelectedItemPosition()+1, idPedido, null, "", nif);
        SingletonPedido.getInstance(this).registarFatura(fatura);


    }

    @Override
    public void onRefreshListaPedidos(List<Pedidos> listaPedidos) {

    }

    @Override
    public void onUpdateListaPedidosBD(Pedidos pedidos, int operacao) {
        TextView recebido = findViewById(R.id.recebido);
        TextView troco = findViewById(R.id.troco);

        String f_nMesa = nMesa.toString();
        String f_subtotal = subtotal.toString();
        String f_total = total.toString();
        String f_recebido = recebido.getText().toString();
        String f_troco = troco.getText().toString();
        String f_iva = array_spinnerIva[sIva.getSelectedItemPosition()];
        String f_met = array_spinnerPagamentos[sPagamentos.getSelectedItemPosition()];

        System.out.println("********************************************");
        System.out.println("*****************FATURA*********************");
        System.out.println("N MESA: " + f_nMesa);
        System.out.println("CONSUMIDO: ");
        for (Artigo item:listaArtigos) {
            System.out.println(" ---> "+item.getNome());
        }
        System.out.println("SUBTOTAL: "+f_subtotal);
        System.out.println("IVA: " + f_iva);
        System.out.println("TOTAL: " + f_total);
        System.out.println("METODO PAGAMENTO: " + f_met);
        System.out.println("RECEBIDO: " + f_recebido);
        System.out.println("TROCO: " + f_troco);
        System.out.println("********************************************");

        SingletonPedido.getInstance(this).limparArtigoEstado(nMesa);

        Intent voltarAtras = new Intent();
        setResult(200, voltarAtras);

        finish();

    }
}
