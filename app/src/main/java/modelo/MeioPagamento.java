package modelo;

/**
 * Created by Utilizador on 26/12/2017.
 */

public class MeioPagamento {

    /* ATRIBUTOS */
    private int id;
    private String nome;

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

    /* CONSTRUTOR */
    public MeioPagamento(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
