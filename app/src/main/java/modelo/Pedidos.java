package modelo;

import java.util.Date;

/**
 * Created by Utilizador on 26/12/2017.
 */

public class Pedidos {

    /* ATRIBUTOS */
    private int id;
    private int id_user;
    private int id_mesa;
    private int id_estado;
    private Date data_pedido;

    /* MÉTODOS */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public Date getData_pedido() {
        return data_pedido;
    }

    public void setData_pedido(Date data_pedido) {
        this.data_pedido = data_pedido;
    }


    /* CONSTRUTOR */
    public Pedidos(int id, int id_user, int id_mesa, int id_estado, Date data_pedido) {
        this.id = id;
        this.id_user = id_user;
        this.id_mesa = id_mesa;
        this.id_estado = id_estado;
        this.data_pedido = data_pedido;
    }

}
