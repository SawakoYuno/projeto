package modelo;

/**
 * Created by Utilizador on 26/12/2017.
 */

public class TipoArtigo {
    /* ATRIBUTOS */
    private long id;
    private String nome;
    private String detalhes;


    /* MÉTODOS */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    /* CONSTRUTOR */
    public TipoArtigo(long id, String nome, String detalhes) {
        this.id = id;
        this.nome = nome;
        this.detalhes = detalhes;
    }

}
