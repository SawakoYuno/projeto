package modelo;

/**
 * Created by Utilizador on 26/12/2017.
 */

public class PedidosEmArtigo {

    /* ATRIBUTOS */
    private Integer id_artigo;
    private Integer id_pedidos;
    private String obs;

    /* MÉTODOS */
    public Integer getId_artigo() {
        return id_artigo;
    }

    public void getId_artigo(Integer id_artigo) {
        this.id_artigo = id_artigo;
    }

    public Integer getId_pedidos() {
        return id_pedidos;
    }

    public void setId_pedidos(Integer id_pedidos) {
        this.id_pedidos = id_pedidos;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    /* CONSTRUTOR */
    public PedidosEmArtigo(Integer id_artigo, Integer id_pedidos, String obs) {
        this.id_artigo = id_artigo;
        this.id_pedidos = id_pedidos;
        this.obs = obs;
    }
}
