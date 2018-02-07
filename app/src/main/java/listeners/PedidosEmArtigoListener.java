package listeners;

import java.util.List;

import modelo.PedidosEmArtigo;

/**
 * Created by Utilizador on 02/01/2018.
 */

public interface PedidosEmArtigoListener {

    void onRefreshListaPedidosEmArtigo(List<PedidosEmArtigo> pedidosEmArtigoList);


    void onUpdateListaPedidosEmArtigoBD(PedidosEmArtigo pedidosEmArtigo, int operacao, String estado_mesa);


}
