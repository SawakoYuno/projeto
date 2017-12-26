package modelo;

/**
 * Created by Utilizador on 26/12/2017.
 */

public class PedidosEmArtigo {

    /* ATRIBUTOS */
    private int id_pedidos_em_artigo;
    private int id_pedidos;
    private String obs;

    /* MÉTODOS */
    public int getId_pedidos_em_artigo() {
        return id_pedidos_em_artigo;
    }

    public void setId_pedidos_em_artigo(int id_pedidos_em_artigo) {
        this.id_pedidos_em_artigo = id_pedidos_em_artigo;
    }

    public long getId_pedidos() {
        return id_pedidos;
    }

    public void setId_pedidos(int id_pedidos) {
        this.id_pedidos = id_pedidos;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    /* CONSTRUTOR */
    public PedidosEmArtigo(int id_pedidos_em_artigo, int id_pedidos, String obs) {
        this.id_pedidos_em_artigo = id_pedidos_em_artigo;
        this.id_pedidos = id_pedidos;
        this.obs = obs;
    }
}
