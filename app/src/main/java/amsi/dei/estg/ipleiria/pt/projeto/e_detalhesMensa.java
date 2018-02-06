package amsi.dei.estg.ipleiria.pt.projeto;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.w3c.dom.Text;

import java.util.List;

import listeners.ArtigoListener;
import listeners.PedidoListener;
import listeners.ReservaListener;
import modelo.Mesa;
import modelo.Pedidos;
import modelo.Reserva;
import singletons.SingletonPedido;
import singletons.SingletonPedidosEmArtigo;
import singletons.SingletonReserva;


public class e_detalhesMensa extends AppCompatActivity implements PedidoListener, ReservaListener {
    private List<Pedidos> pedidosList;
    private List<Reserva> reservaList;
    private Reserva reservaDetalhe;
    private Pedidos pedidos;
    private Context context;
    private Mesa mesaDetalhe;
    private Bundle extra;
    public static final String btn = "BTN";
    private TextView txtNmesa;
    private TextView textViewNome;
    private TextView textViewQuantidade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_detalhes_mesa);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SingletonPedido.getInstance(this).setPedidosListener(this);
        SingletonReserva.getInstance(this).setReservaListener(this);


        extra = getIntent().getExtras();
        txtNmesa = (TextView) findViewById(R.id.txtNmesa);
        txtNmesa.setText(extra.getString("BTN"));



//         textViewNome = (TextView) findViewById(R.id.textViewNome);
////        Intent detalhes = getIntent();
////        int idReserva = detalhes.getIntExtra(reservaDetalhe.getId().toString(), -1);
//
////        reservaDetalhe = SingletonReserva.getInstance(this).pesquisarReservaID(idReserva);
//
//        textViewNome.setText(reservaDetalhe.getNome());



     /*   //ficha
        mesaDetalhe = SingletonPedido.getInstance(this).pesquisarPedidoID();

        Titulo.setText(livroDetalhe.getTitulo());
        Serie.setText(livroDetalhe.getSerie());
        Autor.setText(livroDetalhe.getAutor());
        Ano.setText(livroDetalhe.getAno().toString());
        // Imagem.setImageResource(livroDetalhe.getCapa());



        btnAcao.setImageResource(R.drawable.ic_action_guardar); */
    }

    @Override
    public void onRefreshListaPedidos(List<Pedidos> listaPedidos) {

    }

    @Override
    public void onUpdateListaPedidosBD(Pedidos pedidos, int operacao) {

    }

    @Override
    public void onRefreshListaReserva(List<Reserva> listaReserva) {

    }

    @Override
    public void onUpdateListaReservaBD(Reserva reserva, int operacao) {

    }
}
//jbv