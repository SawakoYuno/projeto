package modelo;

import java.util.Date;

/**
 * Created by Utilizador on 26/12/2017.
 */

public class Empregado {

    /* ATRIBUTOS */
    private int id_user;
    private int id_equipa;
    private int n_empregado;
    private int salario;
    private Date horas;
    private String horario;

    /* MÉTODOS */
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_equipa() {
        return id_equipa;
    }

    public void setId_equipa(int id_equipa) {
        this.id_equipa = id_equipa;
    }

    public int getN_empregado() {
        return n_empregado;
    }

    public void setN_empregado(int n_empregado) {
        this.n_empregado = n_empregado;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public Date getHoras() {
        return horas;
    }

    public void setHoras(Date horas) {
        this.horas = horas;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    /* CONSTRUTOR */
    public Empregado(int id_user, int id_equipa, int n_empregado, int salario, Date horas, String horario) {
        this.id_user = id_user;
        this.id_equipa = id_equipa;
        this.n_empregado = n_empregado;
        this.salario = salario;
        this.horas = horas;
        this.horario = horario;
    }

}
