package listeners;

import java.util.List;

import modelo.Pedidos;

/**
 * Created by Utilizador on 29/12/2017.
 */

public interface PedidoListener {
    void onRefreshListaPedidos(List<Pedidos> listaPedidos);


    void onUpdateListaPedidosBD(Pedidos pedidos, int operacao);
}
