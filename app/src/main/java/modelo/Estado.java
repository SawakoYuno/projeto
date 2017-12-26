package modelo;

/**
 * Created by Utilizador on 26/12/2017.
 */

public class Estado {

    /* ATRIBUTOS */
    private int id;
    private String tipo;

    /* MÉTODOS */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /* CONSTRUTOR */
    public Estado(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }



}
