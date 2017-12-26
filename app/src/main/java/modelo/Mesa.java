package modelo;

/**
 * Created by Utilizador on 26/12/2017.
 */

public class Mesa {

    /* ATRIBUTOS */
    private int id;
    private int numero;
    private String condicao;
    private int quantidade_pessoas;

    /* MÉTODOS */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    public int getQuantidade_pessoas() {
        return quantidade_pessoas;
    }

    public void setQuantidade_pessoas(int quantidade_pessoas) {
        this.quantidade_pessoas = quantidade_pessoas;
    }

    /* CONSTRUTOR */
    public Mesa(int id, int numero, String condicao, int quantidade_pessoas) {
        this.id = id;
        this.numero = numero;
        this.condicao = condicao;
        this.quantidade_pessoas = quantidade_pessoas;
    }

}
