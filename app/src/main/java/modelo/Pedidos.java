package modelo;

import java.util.Date;

/**
 * Created by Utilizador on 26/12/2017.
 */

public class Pedidos {

    /* ATRIBUTOS */
    private Integer id;
    private Integer id_user;
    private Integer id_mesa;
    private Integer id_estado;
    private Date data_pedido;

    /* MÉTODOS */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(Integer id_mesa) {
        this.id_mesa = id_mesa;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public Date getData_pedido() {
        return data_pedido;
    }

    public void setData_pedido(Date data_pedido) {
        this.data_pedido = data_pedido;
    }


    /* CONSTRUTOR */
    public Pedidos(Integer id, Integer id_user, Integer id_mesa, Integer id_estado, Date data_pedido) {
        this.id = id;
        this.id_user = id_user;
        this.id_mesa = id_mesa;
        this.id_estado = id_estado;
        this.data_pedido = data_pedido;
    }

}
