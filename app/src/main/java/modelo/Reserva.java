package modelo;

import java.util.Date;

/**
 * Created by Utilizador on 05/01/2018.
 */

public class Reserva {

    private Integer id;
    private String nome;
    private Integer numeroTelefone;
    private Integer quantidade_pessoas;
    private String horario;
    private Integer id_mesa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(Integer numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public Integer getQuantidade_pessoas() {
        return quantidade_pessoas;
    }

    public void setQuantidade_pessoas(Integer quantidade_pessoas) {
        this.quantidade_pessoas = quantidade_pessoas;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Integer getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(Integer id_mesa) {
        this.id_mesa = id_mesa;
    }

    public Reserva(Integer id, String nome, Integer numeroTelefone, Integer quantidade_pessoas, String horario, Integer id_mesa) {
        this.id = id;
        this.nome = nome;
        this.numeroTelefone = numeroTelefone;
        this.quantidade_pessoas = quantidade_pessoas;
        this.horario = horario;
        this.id_mesa = id_mesa;
    }
}
