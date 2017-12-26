package modelo;

/**
 * Created by Utilizador on 26/12/2017.
 */

public class Artigo {

    /* ATRIBUTOS */
    private int id;
    private int id_tipo_ementa;
    private String nome;
    private String detalhes;
    private int preco;
    private int quantidade;
    private String imagem;


    /* MÉTODOS */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getId_tipo_ementa() {
        return id_tipo_ementa;
    }

    public void setId_tipo_ementa(int id_tipo_ementa) {
        this.id_tipo_ementa = id_tipo_ementa;
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

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    /* CONSTRUTOR */

    public Artigo(int id, int id_tipo_ementa, String nome, String detalhes, int preco, int quantidade, String imagem) {
        this.id = id;
        this.id_tipo_ementa = id_tipo_ementa;
        this.nome = nome;
        this.detalhes = detalhes;
        this.preco = preco;
        this.quantidade = quantidade;
        this.imagem = imagem;
    }
}
