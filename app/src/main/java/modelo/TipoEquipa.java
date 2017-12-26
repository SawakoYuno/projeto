package modelo;

/**
 * Created by Utilizador on 26/12/2017.
 */

public class TipoEquipa {

    /* ATRIBUTOS */
    private int id;
    private String tipo;
    private String detalhes;

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

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    /* CONSTRUTOR */
    public TipoEquipa(int id, String tipo, String detalhes) {
        this.id = id;
        this.tipo = tipo;
        this.detalhes = detalhes;
    }



}
