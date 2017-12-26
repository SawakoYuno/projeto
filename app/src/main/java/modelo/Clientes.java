package modelo;

/**
 * Created by Utilizador on 26/12/2017.
 */

public class Clientes{

    /* ATRIBUTOS */
    private int id_user;
    private String email;
    private int numeroTelefone;
    private String morada;

    /* MÉTODOS */
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(int numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    /* CONSTRUTOR */
    public Clientes(int id_user, String email, int numeroTelefone, String morada) {
        this.id_user = id_user;
        this.email = email;
        this.numeroTelefone = numeroTelefone;
        this.morada = morada;
    }

}
