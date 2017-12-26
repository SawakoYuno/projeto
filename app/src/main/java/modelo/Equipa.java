package modelo;

/**
 * Created by Utilizador on 26/12/2017.
 */

public class Equipa {

    /* ATRIBUTOS */
    private int id;
    private String nome;
    private int id_tipo_equipa;

    /* MÉTODOS */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId_tipo_equipa() {
        return id_tipo_equipa;
    }

    public void setId_tipo_equipa(int id_tipo_equipa) {
        this.id_tipo_equipa = id_tipo_equipa;
    }

    /* CONSTRUTOR */
    public Equipa(int id, String nome, int id_tipo_equipa) {
        this.id = id;
        this.nome = nome;
        this.id_tipo_equipa = id_tipo_equipa;
    }


}
