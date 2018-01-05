package modelo;

/**
 * Created by Utilizador on 26/12/2017.
 */

public class Artigo {

    /* ATRIBUTOS */
    private Integer id;
    private Integer id_tipo_ementa;
    private String nome;
    private String detalhes;
    private Integer preco;
    private Integer quantidade;
    private String imagem;


    /* MÉTODOS */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_tipo_ementa() {
        return id_tipo_ementa;
    }

    public void setId_tipo_ementa(Integer id_tipo_ementa) {
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

    public Integer getPreco() {
        return preco;
    }

    public void setPreco(Integer preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    /* CONSTRUTOR */

    public Artigo(Integer id, Integer id_tipo_ementa, String nome, String detalhes, Integer preco, Integer quantidade ,String imagem) {

        this.id = id;
        this.id_tipo_ementa = id_tipo_ementa;
        this.nome = nome;
        this.detalhes = detalhes;
        this.preco = preco;
        this.quantidade = quantidade;
        this.imagem = imagem;
    }
}
